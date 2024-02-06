
<template>
  <div class="w-full flex justify-center">
    <div class="h-full w-96 inline-grid">
      <h3 class="text-center relative text-2xl font-montreux-branding">{{device_name}}</h3>
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
