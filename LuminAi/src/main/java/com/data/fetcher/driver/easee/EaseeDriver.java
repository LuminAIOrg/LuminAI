package com.data.fetcher.driver.easee;

import com.data.model.Sensor;
import com.data.spi.ActorInterface;
import com.data.spi.FetcherType;
import com.data.spi.ServiceInterface;
import com.data.spi.ServiceWithActorsInterface;
import com.data.utils.PropLoader;
import com.data.utils.Store;
import com.google.gson.Gson;
import io.quarkus.logging.Log;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EaseeDriver implements ServiceWithActorsInterface {

    protected Store store;
    protected Properties properties;
    protected final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    protected static int expiresIn = 86400;
    protected static String accessToken;
    protected static String refreshToken;

    private Authenticator authenticator;
    private Charger charger;
    private final List<ActorInterface> actors = new ArrayList<>();

    protected List<String> chargerIds = new ArrayList<>();

    public EaseeDriver() {
    }

    protected static String getData(String apiUrl){
        //TODO: implement!
        return "dei mama";
    }

    protected static Map<String, Object> postData(String apiUrl, String accessToken, String postString){
        //TODO: what if it is nobody postRequest :c - test if it works with the same thing and just a empty string "" :) (toggle charge has this)
        HttpRequest request;
        HttpResponse<String> response;
        if(accessToken == null || accessToken.isEmpty()){
            request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.easee.com/api/accounts/login"))
                    .header("accept", "application/json")
                    .header("content-type", "application/*+json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(postString))
                    .build();

        } else {
            request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.easee.com/api/accounts/login"))
                    .header("accept", "application/json")
                    .header("content-type", "application/*+json")
                    .header("Authorization", "Bearer " + accessToken)
                    .method("POST", HttpRequest.BodyPublishers.ofString(postString))
                    .build();
        }
        response = null;
        return apiRequest(request);
    }

    private static Map<String, Object> apiRequest(HttpRequest request) {
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String dataJson = response.body();
        System.out.println(dataJson);

        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(dataJson, Map.class);

        return data;
    }

    public void getAllChargers(){
        String apiUrl = "https://api.easee.com/api/chargers";
        String postString = "";
        Map<String, Object> data = postData(apiUrl, accessToken, postString);

        int status = (int) data.get("status");
        if(status == 400){
            Log.error("Something went wrong please check!");
        }

    }
    //TODO: implement those requests:
            /*
    async function getPowerUsage(from, to) {
                url: `https://api.easee.cloud/api/chargers/${id}/usage/hourly/${from}/${to}`,
    function setDynamicCurrent(dynamicChargerCurrent){//, enabled) {
                url: `https://api.easee.cloud/api/chargers/${id}/settings`,
         */

    //TODO: utilize this info:
    // console.log("if: " + (chargerOpMode == 2 && PVState <= minPower && !charging || chargerOpMode == 6 && PVState <= minPower  && !charging) + "   else: "+ (chargerOpMode == 3 && PVState < 0 || charging && PVState < 0));

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
        this.authenticator = new Authenticator();
        this.charger = new Charger();
        actors.add(authenticator);
        actors.add(charger);
        if (properties != null) {
            return CompletableFuture.runAsync(() -> {
                try {
                    scheduler.scheduleAtFixedRate(this.authenticator::refreshTokens, 0, expiresIn, TimeUnit.SECONDS);
                    //scheduler.scheduleAtFixedRate(this::getAllChargers, 0, 5, TimeUnit.SECONDS);
                } catch (RuntimeException e) {
                    Log.info("OIS OASCH");
                    Log.info(e);
                    Log.info(e.getMessage());
                    throw new RuntimeException(e);
                }
            });
        }
        return null;
    }

    @Override
    public FetcherType getType() {
        return FetcherType.DRIVER;
    }

    @Override
    public List<ActorInterface> getActors() {
        return this.actors;
    }
}
