package com.data.fetcher.driver.easee;

import com.data.spi.ActorInterface;
import com.data.spi.FetcherType;
import com.data.utils.PropLoader;
import com.data.utils.Store;
import com.google.gson.Gson;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class Authenticator extends EaseeDriver implements ActorInterface {

    public void authentication(){
        String apiUrl = "https://api.easee.com/api/accounts/login";
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");
        String postString = "{\"userName\":\"" + userName + "\",\"password\":\"" + password + "\"}";
        Map<String, Object> data = postData(apiUrl, accessToken, postString);
/*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.easee.com/api/accounts/login"))
                .header("accept", "application/json")
                .header("content-type", "application/*+json")
                .header("Authorization", "Bearer " + this.accessToken)
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"userName\":\"" + userName + "\",\"password\":\"" + password + "\"}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String dataJson = response.body();
        System.out.println(dataJson);

        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(dataJson, Map.class);
  */

        int status = (int) data.get("status");
        if(status == 400){
            Log.error("Wrong Login Credentials please check!");
        }
        accessToken = (String) data.get("accessToken");
        refreshToken = (String) data.get("refreshToken");
    }

    public void refreshTokens(){
        String apiUrl = "https://api.easee.com/api/accounts/refresh_token";
        String postString = "{\"accessToken\":\"" + accessToken + "\",\"refreshToken\":\"" + refreshToken + "\"}";
        Map<String, Object> data = postData(apiUrl, accessToken, postString);
        /*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.easee.com/api/accounts/refresh_token"))
                .header("accept", "application/json")
                .header("content-type", "application/*+json")
                .header("Authorization", "Bearer " + this.accessToken)
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"accessToken\":\"" + this.accessToken + "\",\"refreshToken\":\"" + this.refreshToken + "\"}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String dataJson = response.body();
        System.out.println(dataJson);

        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(dataJson, Map.class);
        */
        int status = (int) data.get("status");
        if(status == 400){
            Log.error("Refreshing Token did not work! Now trying to get Token via Login");
            authentication();
            return;
        }
        accessToken = (String) data.get("accessToken");
        refreshToken = (String) data.get("refreshToken");
        System.out.println(accessToken);
        System.out.println(refreshToken);
    }

    public void toggleCharge(){
        int chargerId = 5; //TODO: how do i get charger id :) or hardcode to test :))
        String apiUrl = "https://api.easee.com/api/chargers/" + chargerId + "/commands/toggle_charging";
        String postString = "";//TODO: in postData()!
        Map<String, Object> data = postData(apiUrl, accessToken, postString);
        /*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.easee.com/api/chargers/ID/commands/toggle_charging"))
                .header("Authorization", "Bearer " + this.accessToken)
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.body());
        */
        //TODO: implementaion or respones handling so wos sog i. hod geworked?

    }


    //TODO: implement those requests:
            /*

    function getChargerState() {
                url: `https://api.easee.cloud/api/chargers/${id}/state`,
    async function getPowerUsage(from, to) {
                url: `https://api.easee.cloud/api/chargers/${id}/usage/hourly/${from}/${to}`,
    function setDynamicCurrent(dynamicChargerCurrent){//, enabled) {
                url: `https://api.easee.cloud/api/chargers/${id}/settings`,
         */

    //TODO: utilize this info:
        // console.log("if: " + (chargerOpMode == 2 && PVState <= minPower && !charging || chargerOpMode == 6 && PVState <= minPower  && !charging) + "   else: "+ (chargerOpMode == 3 && PVState < 0 || charging && PVState < 0));

    @Override
    public void enableAct() {

    }

    @Override
    public void endAct() {

    }
}
