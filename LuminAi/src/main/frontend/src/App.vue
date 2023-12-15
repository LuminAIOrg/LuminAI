<template>
  <div id="app">
    <main>
      <!-- Your main content goes here -->
      <DeviceDataChart />
    </main>
  </div>
</template>

<script>

import DeviceDataChart from "@/testingChartJs/DeviceDataChart.vue";
import {getUpdatedEnergyDevices} from "@/services/PowerService";

getUpdatedEnergyDevices()
setInterval(getUpdatedEnergyDevices, 5000);
export default {
  name: 'App',
  components: {
    DeviceDataChart
  },
}
</script>

<style>
#app {
  text-align: center;
  margin-top: 60px;
}

main {
  padding: 20px;
}
</style>
