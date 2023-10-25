import {store} from "@/store/Store";

export async function getUpdatedEnergyDevices(){
    fetch('http://localhost:8080/api/devices/getData')
        .then(response => response.json())
        .then(data => {
            store.deviceData= data
        });
}