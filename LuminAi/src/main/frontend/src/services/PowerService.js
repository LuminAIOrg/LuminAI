import {store} from "@/store/Store";

export async function getUpdatedEnergyDevices()
{
    console.log()
    fetch(process.env.VUE_APP_BASE_URL+'/api/devices/getData')
        .then(response => response.json())
        .then(data => {
            store.deviceData= data
        });
}