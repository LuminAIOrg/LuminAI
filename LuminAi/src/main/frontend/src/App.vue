
<template>
  <div class="w-screen h-screen flex ">
    <div class="w-2/6 pr-6">
      <Suspense>
        <NavBarComponent></NavBarComponent>
      </Suspense>
    </div>
    <BackgroundSwooshComponent></BackgroundSwooshComponent>

    <div class="w-screen relative mx-52 mt-14">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script async>
import {getHistoryData, startSocketClient} from "@/services/PowerService";
import {store} from "@/store/Store";
import NavBarComponent from "@/components/NavBarComponent.vue";
import {defineComponent, reactive} from "vue";
import BackgroundSwooshComponent from "@/components/BackgroundSwooshComponent.vue";

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
    BackgroundSwooshComponent,
    NavBarComponent
  }
})
</script>

<style>

</style>
