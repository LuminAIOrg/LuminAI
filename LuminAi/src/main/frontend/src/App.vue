
<template>
  <div class="flex mt-14">
    <div class="w-2/6">
      <NavBarComponent></NavBarComponent>
    </div>

    <div class="w-screen relative mx-52">
      <!--
      <div class="absolute z-0">
        <img class="bg-cover" src="./assets/shwoosh.png">
      </div>
      -->
      <div class="w-full -ml-24 pb-10 z-50">
        <SettingsComponent></SettingsComponent>
      </div>

      <div>

        <div class="w-full">
          <h1 class="text-5xl p-3 -ml-28 font-bold">Overview</h1>
        </div>
        <div class="w-full flex justify-center">

          <div class="columns-2 w-full">
            <div v-for="(sensor, index) in store.sensors" :key="index">
              <div class="">
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
      </div>
    </div>
  </div>

</template>

<script async>
import {getHistoryData, startSocketClient} from "@/services/PowerService";
import {store} from "@/store/Store";
import ChartComponent from "@/components/ChartComponent.vue";
import NavBarComponent from "@/components/NavBarComponent.vue";
import {defineComponent, reactive} from "vue";
import SettingsComponent from "@/components/SettingsComponent.vue";


export default defineComponent({
  name: 'App',
  async created() {
    startSocketClient()
    let _sensors = await getHistoryData()
    _sensors.forEach(sensor => {
      sensor.data = reactive(sensor.data);
    });
    console.log(_sensors)
    store.sensors = _sensors
  },
  components: {
    SettingsComponent,
    ChartComponent,
    NavBarComponent
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
