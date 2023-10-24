function getUpdatedEnergyDevices() {
    fetch('http://localhost:5000/api/energy_data')
        .then(response => response.json())
        .then(data => {

            const energyDataContainer = document.getElementById('energy-data');
            energyDataContainer.innerHTML = '';

            // Display energy data
            data.devices.forEach(device => {
                const deviceElement = document.createElement('div');
                deviceElement.innerHTML = `${device.name}: ${device.value}`;
                energyDataContainer.appendChild(deviceElement);
            });

            // Display timestamp
            const timestampElement = document.createElement('div');
            timestampElement.innerHTML = `Timestamp: ${data.timestamp}`;
            energyDataContainer.appendChild(timestampElement);

        });
}
setInterval(getUpdatedEnergyDevices, 5000);
getUpdatedEnergyDevices()