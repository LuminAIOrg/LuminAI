package com.data.fetcher.driver;

import com.data.spi.FetcherType;
import com.data.spi.ServiceInterface;
import com.data.utils.PropLoader;
import com.data.utils.Store;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

public class DriverImpl implements ServiceInterface {

    private Properties properties;

    private Store store;

    @Override
    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public void setProperties() {
        PropLoader propLoader = new PropLoader();
        propLoader.setType(this.getType());
        this.properties = propLoader.getProperties();
    }

    @Override
    public CompletableFuture<Void> invoke() {
        System.out.println("Driver got Invoked");
        if (properties != null){
            System.out.println("properties set");
        }
        return null;
    }
    @Override
    public FetcherType getType() {
        return FetcherType.DRIVER;
    }
}
