package com.data.spi;


public record ServiceInstanceDto (
    int id,
    String name,
    String serviceName,
    boolean disabled
){}
