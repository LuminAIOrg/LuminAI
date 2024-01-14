<template>
  <div id="app">
    <main>
      <!-- Your main content goes here -->
      <TemperatureChart />
    </main>
  </div>
</template>

<script>

import TemperatureChart from "@/Charts/TemperatureChart.vue";
import {startSocketClient} from "@/services/PowerService";

startSocketClient()
export default {
  name: 'App',
  components: {
    TemperatureChart
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
