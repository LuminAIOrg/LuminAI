<template>
  <div style="width: 100%; display: flex; justify-content: center">
    <div style="height: 90vh;width: 70vw">
      <LineChart
        :chart-data="data"
        :options="options"
      ></LineChart>
    </div>
  </div>
</template>


<script setup>
import { store } from "@/store/Store"
import { ref, computed } from "vue"
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

const data = computed(() => ({
  labels: timestamp.value.map(date => date.toLocaleTimeString()),

  datasets: [
    {
      label: store.deviceData.length > 0 ? store.deviceData[0].name : "",
      data: dataValues.value,
      borderColor: "rgba(6,158,253,0.86)",
    }
  ]
}))

const options = ref({
  type: 'line',
  data: data,
  responsive: true,
  tension: 0.2,
  plugins: {
    title: {
      text: "Device Data"
    },
  },
  scales: {
    x: {
      type: 'category',
      position: 'bottom',
      },
    y: {
      beginAtZero: false,
      max: Math.max(...dataValues.value) + 10,
    },
  },
})
</script>
