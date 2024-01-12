package com.data.fetcher.driver;

import com.data.fetcher.DataFetcher;
import com.data.model.SensorData;
import com.data.repository.SensorDataRepository;
import com.data.session.DataSocket;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

public abstract class Driver implements DataFetcher {
    @Inject
    SensorDataRepository dataRepository;

    @Inject
    DataSocket dataSocket;

    @ConfigProperty(name = "apiUrl")
    public static String apiUrl;

    @Override
    public void invoke() {
        try {
            List<SensorData> data = runDriver();
            // dataRepository.addData(data);
            data.forEach(d -> dataSocket.publish(d));
        } catch (Exception e) {
            Log.error("Error while invoking driver");
            throw new RuntimeException(e);
        }
    }

    public abstract List<SensorData> runDriver() throws Exception;
}
