<script setup lang="ts">
let borderColors = ['#FF0000', '#00FF00', '#0000FF', '#FFFF00', '#FF00FF', '#00FFFF']

import {store} from "@/store/Store";
import ChartComponent from "@/components/ChartComponent.vue";
</script>

<template>

  <div>
    <div class="w-full">
      <h1 class="text-5xl p-3 -ml-28 font-bold">Overview</h1>
    </div>
    <div class="w-full flex justify-center">

      <div class="columns-2 gap-x-20 w-full pb-5">
        <div v-for="(sensor, index) in store.sensors" :key="index">
          <div>
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
</template>

<style scoped>

</style>