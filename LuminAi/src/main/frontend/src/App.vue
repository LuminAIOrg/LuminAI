<template>
  <DeviceList/>
  <chartHTML/>
</template>

<script setup>
import DeviceList from './components/DeviceList.vue'
import chartHTML from "@/testChartJs/chartHTML.vue";
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
