import {store} from "@/store/Store";
import {io} from "socket.io-client";

const socket = io();

socket.on('connect', () =>{
    console.log(socket);
});

socket.on('/subscribeUpdates', (data) =>{
    store.deviceData = data
});