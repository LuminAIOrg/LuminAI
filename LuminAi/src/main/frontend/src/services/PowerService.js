import {store} from "@/store/Store";
import {io} from "socket.io-client";

const socket = io();

socket.on('connect', () =>{
    console.log(socket);
});

socket.on('connect_error', (error) => {
    console.error('Connection error:', error);
});
