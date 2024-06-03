import { reactive } from 'vue'
import {Sensor} from "@/types/Sensor";
import {DriverInstance} from "@/types/DriverInstance";
import {getDriverInstances} from "@/services/DriverInstanceService";

const store = reactive<{
    sensors: Sensor[],
    driverInstances: DriverInstance[]
}>({
    sensors: [],
    driverInstances: []
});

async function fetchDriverInstances() {
    try {
        const instances = await getDriverInstances();
        console.log(instances)
        instances.forEach((it) => {
            if (!store.driverInstances.some(existingInstance => existingInstance.id === it.id)) {
                store.driverInstances.push(it);
            }
        });
    } catch (error) {
        console.error('Failed to fetch driver instances:', error);
    }
}


export { store, fetchDriverInstances };
