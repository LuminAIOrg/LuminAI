import { store } from "@/store/Store";

const socket = new WebSocket('ws://localhost');

export function startSocketClient() {
    socket.addEventListener('open', () => {
        console.log('Connected to server');

        socket.send(JSON.stringify({
            event: 'subscribeUpdates'
        }));
    });
}
