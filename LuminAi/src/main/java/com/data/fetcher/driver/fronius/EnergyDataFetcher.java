package com.data.fetcher.driver.fronius;

import com.data.fetcher.driver.Driver;
import com.google.gson.Gson;
import com.model.Group;
import com.model.Sensor;
import com.model.SensorData;
import io.quarkus.logging.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class EnergyDataFetcher extends Driver {
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

    public SensorData runDriver() throws IOException {
        String dataJson = fetchDataFromApi();

        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(dataJson, Map.class);

        Map<String, Object> body = (Map<String, Object>) data.get("Body");
        Map<String, Object> dataProperty = (Map<String, Object>) body.get("Data");
        Map<String, Object> site = (Map<String, Object>) dataProperty.get("Site");

        // innit Group
        //TODO: check if group already exists (i need repository :c)
        Group fronius = new Group();
        fronius.setName("Fronius");

        //Sensors
        Sensor pvSensor = new Sensor();
        pvSensor.setName("PV");

        Sensor gridSensor = new Sensor();
        gridSensor.setName("Grid");

        Sensor akkuSensor = new Sensor();
        akkuSensor.setName("Akku");

        //TODO: i still need timestamp but there is no timestamp in

        // PV
        SensorData pvData = new SensorData();
        try{
            pvData.setValue((Double) site.get("P_PV"));
        }catch (Exception exception){
            Log.info("There is no PV-Data in this Fronius API!");
        }

        // Grid
        SensorData gridData = new SensorData();
        try{
            gridData.setValue((Double) site.get("P_Grid"));
        }catch (Exception exception){
            Log.info("There is no Grid-Data in this Fronius API!");
        }

        //Akku
        SensorData akkuData = new SensorData();
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

                //TODO: check if sensor already exists (i need repository :c)
                Sensor sensor = new Sensor();
                sensor.setName(String.valueOf(entryData.get("Label")));

                SensorData sensorData = new SensorData();
                sensorData.setValue((Double) entryData.get("P"));

                fronius.addSensor(sensor);
                sensor.addValue(sensorData);
        }

        //TODO Impliment Driver to return Data object
        //TODO: maybe return an feichtn schaß
        return new SensorData();
    }
}
