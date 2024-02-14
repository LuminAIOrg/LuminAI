
<template>
  <div>
    <div v-for="(sensor, index) in sensors" :key="index">
      <div class="w-screen relative top-12">
        <ChartComponent
            :device_name="sensor.name"
            :device_unit="sensor.unit"
            :border_color="borderColors[index % borderColors.length]"
            :chart_data="sensor.data"
        ></ChartComponent>
      </div>
      <p> {{sensor.name}}: {{sensor.data[0].value}}</p>
    </div>
  </div>

</template>

<script async>
import {getHistoryData, startSocketClient} from "@/services/PowerService";
import {store} from "@/store/Store";

//import TempComponent from "@/components/TempComponent.vue";
import ChartComponent from "@/components/ChartComponent.vue";
import {defineComponent} from "vue";


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
