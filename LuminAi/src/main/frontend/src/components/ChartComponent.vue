
<template>
  <div class="w-full inline-grid relative pb-10">
    <div class="relative bg-white drop-shadow-xl rounded-lg p-4">
      <LineChart
          :chart-data="currentChartData"
          :options="chartOptions"
      ></LineChart>

        <div class=" flex justify-evenly">
          <button class="cursor-pointer pt-3 text-2xl hover:text-blue-600 duration-150 ease-in-out" type="button" @click="moveBackward" :disabled="isFirstPage">←</button>
          <button class=" cursor-pointer pt-3 text-2xl hover:text-blue-600 duration-150 ease-in-out" type="button" @click="moveForward" :disabled="isLastPage">→</button>
        </div>
      </div>
    </div>

</template>


<script setup>
import {defineProps, ref, computed} from "vue";
import { LineChart } from "vue-chart-3";
import { Chart, LineController, CategoryScale, LinearScale, PointElement, LineElement } from "chart.js/auto";


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
    timestamp: (new Date(item.timestamp)).toLocaleTimeString(),
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
  return {
    type: 'line',
    data: chartData.value,
    responsive: true,
    tension: 0.2,

    scales: {
      x: {
        type: 'category',
        position: 'bottom',
        title: {
          display: true,
          text: 'Date'
        }
      },
      y: {
        beginAtZero: true,
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
    timestamp: new Date(item.timestamp).toLocaleTimeString(),
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


