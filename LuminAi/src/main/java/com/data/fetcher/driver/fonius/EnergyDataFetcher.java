package com.data.fetcher.driver.fonius;

import com.data.fetcher.driver.Driver;
import com.data.model.SensorData;
import com.google.gson.Gson;

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

    public List<SensorData> runDriver() throws IOException {
        System.out.println("Dummy driver");
        return new ArrayList<>();
    }
}
