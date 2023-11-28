package com.energydata;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

public class EnergyDataFetcher {

    public static String fetchDataFromApi(String apiUrl) throws IOException {
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

    public static Map<String, Object> getEnergyData(String apiUrl) throws IOException {
        String dataJson = fetchDataFromApi(apiUrl);

        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(dataJson, Map.class);

        Map<String, Object> body = (Map<String, Object>) data.get("Body");
        Map<String, Object> dataProperty = (Map<String, Object>) body.get("Data");
        Map<String, Object> site = (Map<String, Object>) dataProperty.get("Site");

        Map<String, Object> energyData = new HashMap<>();
        energyData.put("devices", new ArrayList<>());
        List<Map<String, Object>> devices = (List<Map<String, Object>>) energyData.get("devices");

        // PV
        Map<String, Object> pvData = new HashMap<>();
        pvData.put("name", "PV");
        Double pvValue = (Double) site.get("P_PV");
        pvData.put("value", pvValue);
        devices.add(pvData);

        // Grid
        Map<String, Object> gridData = new HashMap<>();
        gridData.put("name", "Grid");
        Double gridValue = (Double) site.get("P_Grid");
        gridData.put("value",gridValue );
        devices.add(gridData);

        Map<String, Object> akkuData = new HashMap<>();
        akkuData.put("name", "Akku");
        Double akkuValue = (Double) site.get("P_Akku");
        akkuData.put("value",akkuValue );
        devices.add(akkuData);


        Map<String, Object> secondaryMeters = (Map<String, Object>) (dataProperty.get("SecondaryMeters"));
        for (Map.Entry<String, Object> entry : secondaryMeters.entrySet()) {
            Map<String, Object> entryData = (Map<String, Object>) entry.getValue();
            Map<String, Object> deviceData = new HashMap<>();
            deviceData.put("name", entryData.get("Label"));
            deviceData.put("value", entryData.get("P"));
            devices.add(deviceData);
        }

        energyData.put("timestamp", ((Map<String, Object>) data.get("Head")).get("Timestamp"));

        return energyData;
    }
}
