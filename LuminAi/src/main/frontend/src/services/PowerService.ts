import {fetchBackend} from "@/utils";
import {Page} from "@/types/Page";

const socket = new WebSocket(`ws://localhost:8080/subscribeUpdates`);

export function startSocketClient() {
    socket.onopen = function (event) {
        console.log("WebSocket connection opened:", event);
    };

    socket.onmessage = function (event) {
        console.log(event.data)
        //TODO: update websocket in backend
        // store.deviceData.push(JSON.parse(event.data));
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
