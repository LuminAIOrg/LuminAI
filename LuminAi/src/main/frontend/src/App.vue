<template>
  <ChartHtml></ChartHtml>
  <DeviceList/>
</template>

<script setup>
import ChartHtml from "@/testingChartJs/ChartHtml.vue";
import DeviceList from './components/DeviceList.vue'
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
