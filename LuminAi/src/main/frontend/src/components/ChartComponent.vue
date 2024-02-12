
<template>
  <div style="width: 100%; display: flex; justify-content: center">
    <div style="height: 90vh;width: 30vw">
      <div style="margin-bottom: 20px; font-size: 20px;"></div>
      <div style="margin-bottom: 20px; font-size: 20px; font-family: 'Montreux Branding',sans-serif;"> {{device_name}}</div>
      <LineChart
          :chart-data="data"
          :options="options"
      ></LineChart>
    </div>
  </div>
</template>


<script setup>
import {store} from "@/store/Store";
import {defineProps, ref, computed} from "vue"
import {LineChart} from "vue-chart-3"
import {Chart, LineController, CategoryScale, LinearScale, PointElement, LineElement} from "chart.js"

const props = defineProps(['device_name', 'device_unit', 'border_color'])

Chart.register(
    LineController,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement
)

console
const filteredData = computed(() => store.deviceData.filter(entry => entry.name === props.device_name))
const dataValues = computed(() => filteredData.value.map(entry => entry.value))
const timestamp = computed(() => filteredData.value.map(entry => new Date(entry.timestamp * 1000)))
const deviceName = computed(() => filteredData.value.length > 0 ? filteredData.value[0].name : '')

const data = computed(() => ({
  labels: timestamp.value.map(date => date.toLocaleTimeString()),

  datasets: [
    {
      label: deviceName.value,
      data: dataValues.value,
      borderColor: props.border_color,
    }
  ]
}))

const options = ref({
  type: 'line',
  data: data,
  title: props.device_name,
  responsive: true,
  tension: 0.2,
  plugins: {
    title: {
      display: true,
      text: props.device_name,
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
        text: props.device_unit
      }
    },
  },
})

</script>
