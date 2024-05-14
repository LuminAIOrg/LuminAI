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
public class Charger extends EaseeDriver implements ActorInterface {
    //TODO: vllt noch anders überlegen WIE MAN AUTOMATISCHEN LADE EINBAUT sollte ja unabhängig sein
    //TODO:// - WENN eine Wallbox implementiert ist und automatisch laden an im STORE dannn führe die
    //TODO:// Automatic Charger Class aus welche auf den CHARGING Act des implementierten Wallbox Service geht
    // JA SO GEHT GUT :)

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

    @Override
    public void enableAct() {

    }

    @Override
    public void endAct() {

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

}
