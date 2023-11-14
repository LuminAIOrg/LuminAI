package utils.com;


import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.function.Consumer;

import static com.energydata.EnergyDataFetcher.getEnergyData;

public class DeviceUtils {
    public static String runDriver() {
        try {
            Map<String, Object> energyData = getEnergyData();
            return (new Gson().toJson(energyData));
        } catch (IOException e) {
            throw new RuntimeException("the driver failed:" + e);
        }
    }
}

