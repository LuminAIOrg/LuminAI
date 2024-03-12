
<template>
  <div class="w-screen relative flex justify-center">
    <div class="columns-2 w-4/5">
      <div v-for="(sensor, index) in store.sensors" :key="index">
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
import {defineComponent, reactive} from "vue";


export default defineComponent({
  name: 'App',
  async created() {
    startSocketClient()
    let _sensors = await getHistoryData()
    _sensors.forEach(sensor => {
      sensor.data = reactive(sensor.data);
    });
    store.sensors = _sensors
  },
  components: {

    ChartComponent,
    //TempComponent,
  },
  computed: {
    store() {
      return store
    },
    borderColors() {
      return ['#FF0000', '#00FF00', '#0000FF', '#FFFF00', '#FF00FF', '#00FFFF']
    }
  }
})
</script>

<style>

</style>
