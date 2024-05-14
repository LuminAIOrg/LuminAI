package com.data.spi;

import java.util.List;

public interface ServiceWithActorsInterface extends ServiceInterface{
    List<ActorInterface> getActors();
}
