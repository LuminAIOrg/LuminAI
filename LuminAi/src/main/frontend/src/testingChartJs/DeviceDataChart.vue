<template>
  <div style="width: 100%; display: flex; justify-content: center">
    <div style="height: 50vh;width: 30vw">
      <LineChart
        :chart-data="data"
        :options="options"
      ></LineChart>
    </div>
  </div>
</template>


<script setup>
import { store } from "../store/Store"
import { ref, computed } from "vue"
import { LineChart } from "vue-chart-3"
import { Chart, LineController, CategoryScale, LinearScale, PointElement, LineElement} from "chart.js";

Chart.register(
    LineController,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement
)

const dataValues = computed(() =>store.deviceData.map(entry => entry.value))
const timestamp = computed(() => store.deviceData.map(entry => entry.timestamp))

const data = computed(() => ({
  labels: timestamp.value,

  datasets: [
    {
      label: store.deviceData.length > 0 ? store.deviceData[0].name : "",
      data: dataValues.value,
      borderColor: "#dc322f",
    }
  ]
}))

const options = ref({
  responsive: true,
  plugins: {
    title: {
      text: "Device Data"
    },
  },
  scales: {
    x: {
      type: 'linear',
      position: 'bottom'
    },
    y: {
      beginAtZero: true
    }
  },
})
</script>
