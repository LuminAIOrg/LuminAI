
<template>
  <div class="flex mt-14">
    <div class="w-2/6">
      <Suspense>
        <NavBarComponent></NavBarComponent>
      </Suspense>
    </div>

    <div class="absolute -z-10 w-fit ml-80 -top-20">
      <svg width="1540" height="569" viewBox="0 0 1440 569" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd" clip-rule="evenodd" d="M1704.94 -195.603L1646.49 -141.559C1589.43 -78.7913 1475.31 46.7448 1355.63 137.381C1241.89 227.071 1116.65 282.808 980.29 268.745C843.929 254.683 696.448 170.822 573.986 244.008C457.47 316.248 360.027 546.483 229.226 567.32C98.4245 588.158 -65.7349 399.597 -213.215 315.736C-354.75 230.927 -485.551 251.765 -550.952 262.183L-616.353 272.602L-665 -32.7694L-599.599 -43.1881C-534.199 -53.6068 -403.397 -74.4441 -278.542 -94.3343C-147.74 -115.172 -16.939 -136.009 113.862 -156.846C244.664 -177.684 375.465 -198.521 500.321 -218.411C631.122 -239.249 761.923 -260.086 892.724 -280.923C1023.53 -301.761 1154.33 -322.598 1279.18 -342.488C1409.98 -363.325 1540.79 -384.163 1606.19 -394.581L1671.59 -405L1704.94 -195.603Z" fill="#FF4F4F" fill-opacity="0.75"/>
      </svg>
    </div>

    <div class="w-screen relative mx-52">
      <div class="w-full -ml-24 pb-10 z-50">
        <SettingsComponent></SettingsComponent>
      </div>

      <div>

        <div class="w-full">
          <h1 class="text-5xl p-3 -ml-28 font-bold">Overview</h1>
        </div>
        <div class="w-full flex justify-center">

          <div class="columns-2 gap-x-20 w-full">
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
    },
  },

})
</script>

<style>

</style>
