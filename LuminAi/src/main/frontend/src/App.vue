<template>
  <div>
  </div>
  <div class="w-screen flex justify-evenly relative top-12">
    <ChartComponent device_name="Solar" border_color="rgb(255, 0, 0, 1)" device_unit="Temperature in °C"></ChartComponent>
    <ChartComponent device_name="Wärmepumpe" border_color="rgb(0, 0, 255, 1)" device_unit="kW"></ChartComponent>
  </div>
</template>

<script>


import {startSocketClient} from "@/services/PowerService";
import ChartComponent from "@/components/ChartComponent.vue";


startSocketClient()
export default {
  name: 'App',
  components: {
    ChartComponent,
  },
}
</script>

<style>

</style>
