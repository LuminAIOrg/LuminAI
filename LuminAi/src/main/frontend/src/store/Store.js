import { reactive } from 'vue'

export const store = reactive({
    deviceData: [
        {
            "name":"Solar",
            "timestamp":1701715050,
            "value":21.52
        },
        {
            "name":"Solar",
            "timestamp":1701715051,
            "value":25
        },
        {
            "name":"Solar",
            "timestamp":1701715052,
            "value":15
        }
    ],
})