
<template>
  <div>
  </div>
  <div class="w-2/5 inline-grid relative">
    <h3 class="w-full text-center relative text-2xl font-bold">{{device_name}}</h3>
    <div class="relative bg-white drop-shadow-xl rounded-lg p-4 top-4">
      <LineChart
          :chart-data="chartData"
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
import {defineProps, ref, computed} from "vue"
import {LineChart} from "vue-chart-3"
import {Chart, LineController, CategoryScale, LinearScale, PointElement, LineElement} from "chart.js"

const props = defineProps(['device_name', 'device_unit', 'border_color', 'chart_data'])

Chart.register(
    LineController,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement
)

/*
const dataValues = props.chart_data.data.value
const timestamp = new Date(props.chart_data.data.timestamp * 1000)
const deviceName = props.device_name
*/

const chartData = computed(() => {
  const data = props.chart_data.map(item => ({
    timestamp: new Date(item.timestamp * 1000).toLocaleTimeString(),
    value: item.value
  }));

  return {
    labels: data.map(item => item.timestamp),
    datasets: [
      {
        label: props.device_name,
        data: data.map(item => item.value),
        borderColor: props.border_color,
      }
    ]
  }
});

const options = ref({
  type: 'line',
  data: chartData,
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
      max: Math.max(...chartData.value.datasets[0].data) + 10,
      title: {
        display: true,
        text: props.device_unit
      }
    },
  },
})

/*
function moveForward() {

}

function moveBackward() {

}

 */
</script>

