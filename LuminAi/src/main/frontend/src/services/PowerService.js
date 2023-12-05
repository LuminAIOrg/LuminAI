import {store} from "@/store/Store";
import {io} from "socket.io-client";

const socket = io();

socket.on('connect', () => {
    console.log('Connected to server');

    socket.on('/subscribeUpdates', (data) => {
        store.deviceData = data;
    });
});

socket.on('disconnect', () => {
    console.log('Disconnected from server');
});

socket.on('connect_error', (error) => {
    console.error('Connection error:', error);
});
