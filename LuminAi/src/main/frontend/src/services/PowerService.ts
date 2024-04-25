import {fetchBackend, fetchBackendText, postBackend} from "@/utils";
import {Page} from "@/types/Page";
import {store} from "@/store/Store";
import {Data} from "@/types/Data";

const socket = new WebSocket(`ws://localhost:8080/subscribeUpdates`);

export function startSocketClient() {
    socket.onopen = function (event) {
        console.log("WebSocket connection opened:", event);
    };

    socket.onmessage = function (event) {
        const updateData = JSON.parse(event.data) as SocketDataUpdate
        const newData: Data = {
            timestamp: updateData.timestamp,
            value: updateData.value
        }

        const sensorToUpdate = store.sensors.find(sensor => sensor.id == updateData.sensorId)

        if(sensorToUpdate != undefined) {
            sensorToUpdate.data = [newData, ...sensorToUpdate.data]
            console.log(`Received new Dataset for sensor "${sensorToUpdate.name}" (Id: ${sensorToUpdate.id}`, {
                time: new Date(newData.timestamp),
                value: newData.value
            })
        }
    };

    socket.onclose = function (event) {
        console.log("WebSocket connection closed:", event);
    };

    socket.onerror = function (error) {
        console.error("WebSocket error:", error);
    };
}

export async function getHistoryData() {
    return (await fetchBackend<Page>("api/data/page")).sensors;
}

export async function getDataCollectionMethod() {
    return (await fetchBackend<string[]>("api/datacollection/list/"))
}

export async function postDataCollectionMethod(dataCollectionMethod: string) {
    const res = await postBackend<never, { serviceName:string }>("api/datacollection/load", {serviceName:dataCollectionMethod}, true)
    return res.status == 201
}

export async function getUserName() {
    return (await fetchBackendText("api/user/username"))
}

