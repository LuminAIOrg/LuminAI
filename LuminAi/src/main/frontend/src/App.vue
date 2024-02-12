<template>
  <div id="app">
    <main>
      <div style="display: flex">
        <div v-for="sensor in sensors" :key="sensor.id">
          <TempComponent


              device_name="{{sensor.name}}"
              device_unit="{{sensor.unit}}"
              border_color="#ff00ff"
          ></TempComponent>
        </div>
      </div>
    </main>
  </div>
</template>

<script async>
import {getHistoryData, startSocketClient} from "@/services/PowerService";
import {store} from "@/store/Store";
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
#app {
  text-align: center;
  margin-top: 60px;
}
main {
  padding: 20px;
}
</style>