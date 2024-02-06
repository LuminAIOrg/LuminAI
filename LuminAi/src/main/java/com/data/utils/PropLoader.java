package com.data.utils;

import com.data.spi.FetcherType;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.config.ConfigProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropLoader     {

    private FetcherType type;
    private Properties properties;
    public PropLoader() {

    }
    private void findProperties(){
        String resourceName = type.toString().toLowerCase() + ".properties";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
        String absolutePath = file.getAbsolutePath();


        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(absolutePath));

        }catch (IOException e){
            Log.warn("no file found or can't be loaded: " + e.getMessage());
        }
        this.properties = properties;
    }

    public FetcherType getType() {
        return type;
    }

    public void setType(FetcherType type) {
        this.type = type;
    }

    public Properties getProperties() {
        if (this.properties == null){
            findProperties();
        }
        return properties;
    }
}
