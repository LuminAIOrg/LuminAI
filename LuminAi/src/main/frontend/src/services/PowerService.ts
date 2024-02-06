import { store } from "@/store/Store.ts";

const socket = new WebSocket(`ws://localhost:8080/subscribeUpdates`);

export function startSocketClient() {
    socket.onopen = function (event) {
        console.log("WebSocket connection opened:", event);
    };

    socket.onmessage = function (event) {
        store.deviceData.push(JSON.parse(event.data));
    };

    socket.onclose = function (event) {
        console.log("WebSocket connection closed:", event);
    };

    socket.onerror = function (error) {
        console.error("WebSocket error:", error);
    };
}
