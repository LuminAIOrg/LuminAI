import { store } from "@/store/Store";

const socket = new WebSocket('ws://localhost:8080/subscribeUpdates');

export function startSocketClient() {
    socket.onopen = function (event) {
        console.log("WebSocket connection opened:", event);
    };

    socket.onmessage = function (event) {
        console.log(event.data);
        const data = JSON.parse(event.data);
        store.deviceData = data;
    };

    socket.onclose = function (event) {
        console.log("WebSocket connection closed:", event);
    };

    socket.onerror = function (error) {
        console.error("WebSocket error:", error);
    };
}
