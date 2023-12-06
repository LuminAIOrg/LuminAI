import {store} from "@/store/Store";

export async function getUpdatedEnergyDevices() {
    try {
        const response = await fetch('http://localhost:8080/api/devices/getData');
        if (!response.ok) {
            throw new Error(`Failed to fetch data. Status: ${response.status}`);
        }
        store.deviceData = await response.json()
        console.log(store.deviceData)
    } catch (error) {
        console.error('Error fetching data:', error.message);
    }
}