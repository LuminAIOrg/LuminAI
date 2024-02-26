
<template>
  <!-- Do not remove ... under construction -->
  <div class="opacity-0 hidden">
    <ChartComponentNew></ChartComponentNew>
  </div>
  <div class="w-screen relative flex justify-center">
    <div class="columns-2 w-4/5">
      <div v-for="(sensor, index) in sensors" :key="index">
        <div class="relative top-16">
          <ChartComponent
              :device_name="sensor.name"
              :device_unit="sensor.unit"
              :border_color="borderColors[index % borderColors.length]"
              :chart_data="sensor.data"
          ></ChartComponent>
        </div>
      </div>
    </div>
  </div>

</template>

<script async>
import {getHistoryData, startSocketClient} from "@/services/PowerService";
import {store} from "@/store/Store";
import ChartComponent from "@/components/ChartComponent.vue";
import {defineComponent} from "vue";

import ChartComponentNew from "@/components/ChartComponentNew.vue";

export default defineComponent({
  name: 'App',
  data() {
    return {
      sensors: store.sensors
    };
  },
  async created() {
    startSocketClient()
    let _sensors = await getHistoryData()
    store.sensors.push(_sensors)
    this.sensors = store.sensors;
  },
  components: {
    ChartComponentNew,
    ChartComponent,
    //TempComponent,
  },
  computed: {
    borderColors() {
      return ['#FF0000', '#00FF00', '#0000FF', '#FFFF00', '#FF00FF', '#00FFFF']
    }
  }
})
</script>

<style>

</style>
