<template>
    <div class="w-2/5 inline-grid relative">
      <h3 class="w-full text-center relative text-2xl font-bold">{{device_name}}</h3>
      <div class="relative bg-white drop-shadow-xl rounded-lg p-4 top-4">
        <LineChart
            :chart-data="data"
            :options="options"
        ></LineChart>
      </div>
      <div class="w-full h-56 relative top-8">
        <div class="flex justify-evenly">
          <button class="bg-white pt-3 pb-3 pl-14 pr-14 rounded-lg drop-shadow-lg hover:drop-shadow-xl duration-150 ease-in-out" type="button" @click="moveForward">←</button>
          <button class="bg-white pt-3 pb-3 pl-14 pr-14 rounded-lg drop-shadow-lg hover:drop-shadow-xl duration-150 ease-in-out" type="button" @click="moveBackward">→</button>
        </div>
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


function moveForward() {

}

function moveBackward() {

}
</script>
