package com.data.boundary;

import com.data.repository.WallboxRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/charger")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WallboxResource {

    @Inject
    WallboxRepository wallboxRepository;

    @GET
    @Path("/getState")
    public boolean getChargerState() {
        return false;
    }

    @GET
    @Path("/toggleCharging")
    public boolean toggleCharging() {
        boolean result = false;
        return result;
    }

    @GET
    @Path("/automateCharge")
    public Boolean toggleAutoCharge() {
        return false;
    }

    @POST
    @Path("/getPowerUsage")
    public double getPowerUsage() {
        //getPowerUsage(from, to);
        return -1;
    }

    @GET
    @Path("/startCharge")
    @Produces(MediaType.APPLICATION_JSON)
    public void start() {
        // Implement start logic
    }

    @GET
    @Path("/stopCharge")
    @Produces(MediaType.APPLICATION_JSON)
    public void stop() {
        // Implement stop logic
    }


    @GET
    @Path("/authenticate")
    public Boolean authenticate() {
        return false;
    }

    /*

app.get('/getChargerState', async (req, res) => {
        //const result = await getChargerState();
        const result = chargerState;
//console.log("sent ChargerState")
    res.send(JSON.stringify(result));
        });

        app.get('/toggleCharging', async (req, res) => {
        const result = await toggleCharging();
    res.send(JSON.stringify(result));
        });

        app.get('/automateCharge', async (req, res) => {
        if(auto) auto = false;
        else auto = true;
        const result = auto//await automateCharge();
automateCharge()
    res.send(JSON.stringify(result));
        });

        app.postRequest('/getPowerUsage', async (req, res) => {
let from = req.body.from.replace(" ", "+").replace(":", "%3A").replace("+", "%2B");
let to = req.body.to.replace(" ", "+").replace(":", "%3A").replace("+", "%2B");
//console.log(from);
//console.log(to);
    const result = await getPowerUsage(from, to);
    res.send('' + result);
});

        app.listen(port, () => {
        //console.log(`Server gestartet`);
        //console.log(`Erreichbar unter http://localhost:${port}`);
        });

     */
}
