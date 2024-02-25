
<template>
  <div>
  </div>
  <div class="w-2/5 inline-grid relative">
    <h3 class="w-full text-center relative text-2xl font-bold">{{device_name}}</h3>
    <div class="relative bg-white drop-shadow-xl rounded-lg p-4 top-4">
      <LineChart
          :chart-data="currentChartData"
          :options="chartOptions"
      ></LineChart>
    </div>
    <div class="w-full h-56 relative top-8">
      <div class="flex justify-evenly">
        <button class="cursor-pointer bg-white pt-3 pb-3 pl-14 pr-14 rounded-lg drop-shadow-lg hover:drop-shadow-xl duration-150 ease-in-out" type="button" @click="moveBackward" :disabled="isFirstPage">←</button>
        <button class=" cursor-pointer bg-white pt-3 pb-3 pl-14 pr-14 rounded-lg drop-shadow-lg hover:drop-shadow-xl duration-150 ease-in-out" type="button" @click="moveForward" :disabled="isLastPage">→</button>
      </div>
    </div>
  </div>
</template>


<script setup>
import {defineProps, ref, computed} from "vue";
import { LineChart } from "vue-chart-3";
import { Chart, LineController, CategoryScale, LinearScale, PointElement, LineElement } from "chart.js";

const props = defineProps(['device_name', 'device_unit', 'border_color', 'chart_data']);

Chart.register(
    LineController,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement
);

const currentPage = ref(0);
const pageSize = 10;

const chartData = computed(() => {
  const startIndex = currentPage.value * pageSize;
  const data = props.chart_data.slice(startIndex, startIndex + pageSize).map(item => ({
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
  };
});

const chartOptions = computed(() => {
  const maxY = Math.max(...chartData.value.datasets[0].data);
  const maxYRounded = Math.ceil(maxY / 100) * 100;
  return {
    type: 'line',
    data: chartData.value,
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
        max: maxYRounded + 100,
        title: {
          display: true,
          text: props.device_unit
        }
      },
    },
  };
});

const moveForward = () => {
  currentPage.value++;
};

const moveBackward = () => {
  currentPage.value--;
};

const isFirstPage = computed(() => currentPage.value === 0);
const isLastPage = computed(() => (currentPage.value + 1) * pageSize >= props.chart_data.length);

const currentChartData = computed(() => {
  const startIndex = currentPage.value * pageSize;
  const data = props.chart_data.slice(startIndex, startIndex + pageSize).map(item => ({
    timestamp: new Date(item.timestamp * 1000).toLocaleTimeString(),
    value: item.value
  }));

  return {
    ...chartData.value,
    labels: data.map(item => item.timestamp),
    datasets: [
      {
        ...chartData.value.datasets[0],
        data: data.map(item => item.value),
      }
    ]
  };
});
</script>


