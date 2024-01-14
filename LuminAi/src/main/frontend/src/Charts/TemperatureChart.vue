
<template>
  <div style="width: 100%; display: flex; justify-content: center">
    <div style="height: 90vh;width: 35vw">
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
import {ref, computed, watch} from "vue"
import { LineChart } from "vue-chart-3"
import { Chart, LineController, CategoryScale, LinearScale, PointElement, LineElement} from "chart.js"

Chart.register(
    LineController,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement
)


const dataValues = computed(() =>store.deviceData.map(entry => entry.value))
const timestamp = computed(() => store.deviceData.map(entry => new Date(entry.timestamp * 1000)))
//TODO: make a nicer code and not a div box please! :,)
const deviceName = computed(() => store.deviceData.length > 0 ? store.deviceData[0].name : '')

const data = computed(() => ({
  labels: timestamp.value.map(date => date.toLocaleTimeString()),

  datasets: [
    {
      label: 'Solar',//deviceName.value,
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

watch(() => store.deviceData, () => {
  // Update chart data when store.deviceData changes
  data.value.labels = timestamp.value.map(date => date.toLocaleTimeString());
  data.value.datasets[0].data = dataValues.value;
  options.value.scales.y.max = Math.max(...dataValues.value) + 10;
})

</script>
