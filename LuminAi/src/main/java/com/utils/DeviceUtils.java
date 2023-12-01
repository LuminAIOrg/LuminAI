package com.utils;


import com.google.gson.Gson;

import java.io.*;
import java.util.Map;

import static com.energydata.EnergyDataFetcher.getEnergyData;

public class DeviceUtils {
    public static String runDriver(String apiUrl) {
        try {
            Map<String, Object> energyData = getEnergyData(apiUrl);
            return (new Gson().toJson(energyData));
        } catch (IOException e) {
            throw new RuntimeException("the driver failed:" + e);
        }
    }

}

