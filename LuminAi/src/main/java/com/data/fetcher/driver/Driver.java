package com.data.fetcher.driver;

import com.data.fetcher.DataFetcher;
import com.data.model.Data;
import com.data.repository.DataRepository;
import com.data.session.DataSocket;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

public abstract class Driver implements DataFetcher {
    @Inject
    DataRepository dataRepository;

    @Inject
    DataSocket dataSocket;

    @ConfigProperty(name = "apiUrl")
    public static String apiUrl;

    @Override
    public void invoke() {
        try {
            List<Data> data = runDriver();
            //6 dataRepository.addData(data);
            data.forEach(d -> dataSocket.publish(d));
        } catch (Exception e) {
            Log.error("Error while invoking driver");
            throw new RuntimeException(e);
        }
    }

    public abstract List<Data> runDriver() throws Exception;
}
