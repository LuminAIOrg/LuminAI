package com.data.spi;

import com.data.utils.Store;
import java.util.concurrent.CompletableFuture;

public interface ServiceInterface {
    FetcherType getType();

    void setStore(Store store);

    void setProperties();

    CompletableFuture<Void> invoke();
}
