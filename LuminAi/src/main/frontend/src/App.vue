<template>
  <div id="app">
    <main>
      <div style="display: flex">
        <TemperatureChart />
        <WPTestChart/>
      </div>
    </main>
  </div>
</template>

<script>

import TemperatureChart from "@/Charts/TemperatureChart.vue";
import WPTestChart from "@/Charts/WPTestChart.vue";
import {startSocketClient} from "@/services/PowerService";

startSocketClient()
export default {
  name: 'App',
  components: {
    TemperatureChart,
    WPTestChart
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
