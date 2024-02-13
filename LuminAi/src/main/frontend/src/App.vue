
<template>
  <div>
  </div>
  <div class="w-screen flex justify-evenly relative top-12">
    <ChartComponent device_name="Solar" border_color="rgb(255, 0, 0, 1)" device_unit="Temperature in °C"></ChartComponent>
    <ChartComponent device_name="Wärmepumpe" border_color="rgb(0, 0, 255, 1)" device_unit="kW"></ChartComponent>
  </div>
</template>

<script async>
import {getHistoryData, startSocketClient} from "@/services/PowerService";
import {store} from "@/store/Store";

import {startSocketClient} from "@/services/PowerService";
import TempComponent from "@/components/TempComponent.vue";


startSocketClient()

let _sensors = await getHistoryData()
store.sensors.push(_sensors)

export default {
  name: 'App',
  computed: {
    sensors: _sensors
  },
  components: {
    TempComponent,
  },
}
</script>

<style>

</style>
