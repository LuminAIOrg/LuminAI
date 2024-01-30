<template>
  <div id="app">
    <main>
      <div style="display: flex">
        <ChartComponent device_name="Solar"></ChartComponent>
        <ChartComponent device_name="Wärmepumpe"></ChartComponent>
      </div>
    </main>
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
#app {
  text-align: center;
  margin-top: 60px;
}

main {
  padding: 20px;
}
</style>
