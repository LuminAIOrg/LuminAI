

import { reactive } from 'vue'
import {Sensor} from "@/types/Sensor";

export const store = reactive<{
    sensors: Sensor[]
}>({
    "sensors": [
        /* {
            "id": 1,
            "name": "noise",
            "unit": null,
            "data": [
                {
                    "timestamp": 1707392649,
                    "value": 331
                },
                {
                    "timestamp": 17073956649,
                    "value": 204
                },
                {
                    "timestamp": 1707398649,
                    "value": 502
                },
                {
                    "timestamp": 1707392649,
                    "value": 89
                },
                {
                    "timestamp": 17073956649,
                    "value": 482
                },
                {
                    "timestamp": 1707398649,
                    "value": 142
                },
                {
                    "timestamp": 1707392649,
                    "value": 74
                },
                {
                    "timestamp": 17073956649,
                    "value": 11
                },
                {
                    "timestamp": 1707398649,
                    "value": 205
                },
                {
                    "timestamp": 1707392649,
                    "value": 12
                },
                {
                    "timestamp": 17073956649,
                    "value": 538
                },
                {
                    "timestamp": 1707398649,
                    "value": 295
                },
                {
                    "timestamp": 1707392649,
                    "value": 842
                },
                {
                    "timestamp": 17073956649,
                    "value": 569
                },
                {
                    "timestamp": 1707398649,
                    "value": 427
                }
            ]
        },
        {
            "id": 2,
            "name": "co2",
            "unit": null,
            "data": [
                {
                    "timestamp": 1707392647,
                    "value": 736.17
                }
            ]
        },
        {
            "id": 3,
            "name": "pressure",
            "unit": null,
            "data": [
                {
                    "timestamp": 1707392338,
                    "value": 971.69
                }
            ]
        },
        {
            "id": 4,
            "name": "humidity",
            "unit": null,
            "data": [
                {
                    "timestamp": 1707392647,
                    "value": 44.68
                }
            ]
        },
        {
            "id": 5,
            "name": "neopixel",
            "unit": null,
            "data": [
                {
                    "timestamp": 1707390664,
                    "value": 1
                }
            ]
        },
        {
            "id": 6,
            "name": "temperature",
            "unit": null,
            "data": [
                {
                    "timestamp": 1707392233,
                    "value": 23.29
                }
            ]
        },
        {
            "id": 7,
            "name": "motion",
            "unit": null,
            "data": [
                {
                    "timestamp": 1707392361,
                    "value": 0
                }
            ]
        },
*/
    ]
})