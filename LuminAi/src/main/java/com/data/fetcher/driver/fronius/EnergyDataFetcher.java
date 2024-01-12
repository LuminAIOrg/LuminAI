package com.data.fetcher.driver.fronius;

import com.data.fetcher.driver.Driver;
import com.data.model.Group;
import com.data.model.Sensor;
import com.data.repository.GroupRepository;
import com.data.repository.SensorRepository;
import com.google.gson.Gson;
import com.data.model.SensorData;
import com.data.repository.SensorDataRepository;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class EnergyDataFetcher extends Driver {

    @Inject
    GroupRepository groupRepository;

    @Inject
    SensorRepository sensorRepository;

    List<SensorData> dataList;


    private static String fetchDataFromApi() throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            return response.toString();
        } else {
            throw new IOException("Failed to fetch data from API. Response code: " + responseCode);
        }
    }

    public List<SensorData> runDriver() throws IOException {
        String dataJson = fetchDataFromApi();

        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(dataJson, Map.class);

        Map<String, Object> body = (Map<String, Object>) data.get("Body");
        Map<String, Object> dataProperty = (Map<String, Object>) body.get("Data");
        Map<String, Object> site = (Map<String, Object>) dataProperty.get("Site");

        // innit Group
        Group fronius = groupRepository.createOrGetGroup("Group");

        // Create or Get ALL Sensors and set Group
        Sensor pvSensor = sensorRepository.createOrGetSensor("PV");
        Sensor gridSensor = sensorRepository.createOrGetSensor("Grid");
        Sensor akkuSensor = sensorRepository.createOrGetSensor("Akku");

        pvSensor.setGroup(fronius);
        gridSensor.setGroup(fronius);
        akkuSensor.setGroup(fronius);

        // PV
        SensorData pvData = new SensorData();
        pvData.setDevice(pvSensor);
        try{
            pvData.setValue((Double) site.get("P_PV"));
            dataList.add(pvData);
        }catch (Exception exception){
            Log.info("There is no PV-Data in this Fronius API!");
        }

        // Grid
        SensorData gridData = new SensorData();
        gridData.setDevice(gridSensor);
        try{
            gridData.setValue((Double) site.get("P_Grid"));
            dataList.add(gridData);
        }catch (Exception exception){
            Log.info("There is no Grid-Data in this Fronius API!");
        }

        //Akku
        SensorData akkuData = new SensorData();
        akkuData.setDevice(akkuSensor);
        try{
            akkuData.setValue((Double) site.get("P_Akku"));
        }catch (Exception exception){
            Log.info("There is no Akku-Data in this Fronius API!");
        }

        //TODO: try catch in loop
        Map<String, Object> secondaryMeters = (Map<String, Object>) (dataProperty.get("SecondaryMeters"));
        for (Map.Entry<String, Object> entry : secondaryMeters.entrySet()) {
                Map<String, Object> entryData = (Map<String, Object>) entry.getValue();
                Map<String, Object> deviceData = new HashMap<>();

                //Create or Get Sensor
                Sensor sensor = sensorRepository.createOrGetSensor(String.valueOf(entryData.get("Label")));
                sensor.setGroup(fronius);

                SensorData sensorData = new SensorData();
                sensorData.setDevice(sensor);
                sensorData.setValue((Double) entryData.get("P"));
                dataList.add(sensorData);
        }

        return dataList;
    }
}
