
<template>
  <div style="width: 100%; display: flex; justify-content: center">
    <div style="height: 90vh;width: 30vw">
      <div style="margin-bottom: 20px; font-size: 20px;"></div>
      <div style="margin-bottom: 20px; font-size: 20px; font-family: 'Montreux Branding',sans-serif;">{{ deviceName }}</div>
      <hr>
      <LineChart
        :chart-data="data"
        :options="options"
      ></LineChart>
    </div>
  </div>
</template>


<script setup>
import { store } from "@/store/Store"
import {ref, computed } from "vue"
import { LineChart } from "vue-chart-3"
import { Chart, LineController, CategoryScale, LinearScale, PointElement, LineElement} from "chart.js"

Chart.register(
    LineController,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement
)

const filteredData = computed(() => store.deviceData.filter(entry => entry.name === "Solar"))
const dataValues = computed(() => filteredData.value.map(entry => entry.value))
const timestamp = computed(() => filteredData.value.map(entry => new Date(entry.timestamp * 1000)))
//TODO: make a nicer code and not a div box please! :,)
const deviceName = computed(() => filteredData.value.length > 0 ? filteredData.value[0].name : '')

const data = computed(() => ({
  labels: timestamp.value.map(date => date.toLocaleTimeString()),

  datasets: [
    {
      label: deviceName.value,
      data: dataValues.value,
      borderColor: "rgba(6,158,253,0.86)",
    }
  ]
}))

const options = ref({
  type: 'line',
  data: data,
  title: {text: deviceName.value},
  responsive: true,
  tension: 0.2,
  plugins: {
    title: {
      display: true,
      text: 'Solar',
    },
  },
  scales: {
    responsive: true,
    x: {
      type: 'category',
      position: 'bottom',
      title: {
        display: true,
        text: 'Time'
      }
      },
    y: {
      beginAtZero: true,
      max: Math.max(...dataValues.value) + 10,
      title: {
        display: true,
        text: 'Temperature in °C'
      }
    },
  },
})


</script>
