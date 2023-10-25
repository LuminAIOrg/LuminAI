export function getUpdatedEnergyDevices() {
    fetch('http://localhost:8080/api/devices/getData')
        .then(response => response.json())
        .then(data => {
            return data;
        });

}
setInterval(getUpdatedEnergyDevices, 5000);
//getUpdatedEnergyDevices()