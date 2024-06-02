import {fetchBackend, postBackend} from "@/utils";
import {DriverInstance} from "@/types/DriverInstance";
import {store} from "@/store/Store";

export async function getDriverInstances() {
    return (await fetchBackend<DriverInstance[]>("api/datacollection/serviceInstances"));
}

function updateDriverInstanceInStore(id: number, updatedInstance: DriverInstance) {
    const index = store.driverInstances.findIndex(driver => driver.id === id);
    if (index !== -1) {
        store.driverInstances[index] = updatedInstance;
    }
}

function addDriverInstane(driverInstance: DriverInstance) {
    store.driverInstances.push(driverInstance)
}

function removeDriverInstanceFromStore(id: number) {
    const index = store.driverInstances.findIndex(driver => driver.id === id);
    if (index !== -1) {
        store.driverInstances.splice(index, 1);
    }
}

async function extractDriverInstance(response: DriverInstance | Response): Promise<DriverInstance> {
    if (response instanceof Response) {
        const data = await response.json();
        return data as DriverInstance;
    }
    return response;
}

export async function newDriverInstane(dataCollectionMethod: string) {
    const response = await postBackend<DriverInstance | Response, { serviceName:string }> ("api/datacollection/load", {serviceName:dataCollectionMethod}, true)
    addDriverInstane(await extractDriverInstance(response))
}

export async function stopDriverInstance(id: number) {
    const response = await postBackend<DriverInstance | Response, string>(`api/datacollection/disable/` + id, "");
    const driverInstance = await extractDriverInstance(response);
    updateDriverInstanceInStore(id, driverInstance);
}

export async function startDriverInstance(id: number) {
    const response = await postBackend<DriverInstance | Response, string>(`api/datacollection/enable/` + id, "");
    const driverInstance = await extractDriverInstance(response);
    updateDriverInstanceInStore(id, driverInstance);
}

export async function deleteDriverInstance(id: number) {
    const response = await postBackend<boolean | Response, string>(`api/datacollection/delete/` + id, "");
    if (response instanceof Response) {
        const success = await response.json();
        if (success) {
            removeDriverInstanceFromStore(id);
        }
    } else if (response) {
        removeDriverInstanceFromStore(id);
    }
}
