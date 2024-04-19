<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

<template>
  <div class="w-full inline-grid">
    <div class="opacity-0 w-0">{{ device_name }}</div>

    <!-- Filter Button -->
    <div class="relative bg-white drop-shadow-xl rounded-lg p-4">
      <Button @click="toggleDropdown" class="flex bg-blue-500 text-white p-0.5 px-1 rounded-lg opacity-100 hover:drop-shadow-lg duration-300">
        <span class="material-symbols-outlined">filter_list</span>
        <span v-if="!isDropdownOpen" @click="rotateIcons()" class="material-symbols-outlined">expand_more</span>
        <span v-if="isDropdownOpen" class="material-symbols-outlined">expand_less</span>
      </Button>

      <!-- Timeframe settings -->
      <div v-if="isDropdownOpen" class="w-3/6 p-2 px-3 mt-3 absolute z-10 bg-white drop-shadow-2xl rounded-lg animate-popUp">
        <div class="grid grid-cols-1 gap-3">
          <h1 class="text-gray-400">Select a Timeframe!</h1>

          <div class="w-full grid grid-cols-1 gap-3 mb-2">
            <div class="flex justify-between items-center mx-3">
              <h1 class="text-gray-400 text-center">Start:</h1>
              <input type="datetime-local" id="start-date" class="border-solid border-2 rounded-lg" v-model="selectedStartDate">
            </div>
            <div class="flex justify-between items-center mx-3">
              <h1 class="text-gray-400 text-center">End:</h1>
              <input type="datetime-local" id="end-date" class="border-solid border-2 rounded-lg" v-model="selectedEndDate">
            </div>
            <div class="w-full mt-1 flex justify-center">
              <button class="bg-gray-500 text-white hover:text-gray-200 font-bold px-2 rounded-lg" @click="resetFilter">Reset</button>
            </div>
          </div>
        </div>

      </div>

      <!-- Chart -->
      <LineChart v-if="currentChartData" :chart-data="currentChartData" :options="chartOptions"></LineChart>

      <div class="flex justify-evenly">
        <button class="cursor-pointer pt-3 text-2xl hover:text-blue-600 duration-150 ease-in-out" type="button" @click="moveBackward" :disabled="isFirstPage">←</button>
        <button class="cursor-pointer pt-3 text-2xl hover:text-blue-600 duration-150 ease-in-out" type="button" @click="moveForward" :disabled="isLastPage">→</button>
      </div>

      <div class="w-full flex mr-2 text-gray-400">
        <h1>{{ currentPage + 1}} of {{ totalPages }}</h1>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref, computed, watch } from "vue";
import { LineChart } from "vue-chart-3";
import { Chart, LineController, CategoryScale, LinearScale, PointElement, LineElement } from "chart.js/auto";

const props = defineProps(["device_name", "device_unit", "border_color", "chart_data"]);

Chart.register(LineController, CategoryScale, LinearScale, PointElement, LineElement);

// Dropdown State
const isDropdownOpen = ref(false);
const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value;
};


const chartData = ref([]);
let pageSize = 10;
let currentPage = ref(0);
const selectedStartDate = ref('');
const selectedEndDate = ref('');
let initialData = [];


watch(
    () => props.chart_data,
    (newData) => {
        chartData.value = newData.map((item) => ({
          timestamp: new Date(item.timestamp).getTime(),
          value: item.value,
        }));
        initialData = [...chartData.value];
        applyFilter();
    }
);

// Arrow Buttons
const moveForward = () => {
  if (!isLastPage.value) {
    currentPage.value--;
  }
};

const moveBackward = () => {
  if (!isFirstPage.value) {
    currentPage.value++;
  }
};


const isFirstPage = computed(() => (currentPage.value + 1) * pageSize >= filteredChartData.value.length);
const isLastPage = computed(() => currentPage.value === 0);


// Filter for Timeframe
const applyFilter = () => {
  if (selectedStartDate.value && selectedEndDate.value) {
    pageSize = 80;
  } else {
    pageSize = 10;
  }

  if (selectedStartDate.value && selectedEndDate.value) {
    const start = selectedStartDate.value ? new Date(selectedStartDate.value).getTime() : 0;
    const end = selectedEndDate.value ? new Date(selectedEndDate.value).getTime() : Infinity;
    filteredChartData.value = initialData.filter(item => {
      const timestamp = item.timestamp;
      return timestamp >= start && timestamp <= end;
    });
  } else {
    filteredChartData.value = [...initialData];
  }
  currentPage.value = 0;
};


// Reset timeframe filter
const resetFilter = () => {
  selectedStartDate.value = '';
  selectedEndDate.value = '';
  applyFilter();
};

const filteredChartData = ref([]);

watch([selectedStartDate, selectedEndDate], applyFilter);

const totalPages = computed(() => Math.ceil(filteredChartData.value.length / pageSize));


// Current Chart data
const currentChartData = computed(() => {
  const startIndex = currentPage.value * pageSize;
  const endIndex = Math.min(startIndex + pageSize, filteredChartData.value.length);
  const data = filteredChartData.value.slice(startIndex, endIndex).reverse();
  return {
    labels: data.map((item) => new Date(item.timestamp).toLocaleTimeString()),
    datasets: [
      {
        label: props.device_name,
        data: data.map((item) => item.value),
        borderColor: props.border_color,
      },
    ],
  };
});


// Setting options of Chart
const chartOptions = computed(() => {
  return {
    type: "line",
    data: currentChartData.value,
    responsive: true,
    tension: 0.2,
    scales: {
      x: {
        type: "category",
        position: "bottom",
        title: {
          display: true,
          text: "Date",
        },
      },
      y: {
        beginAtZero: true,
        title: {
          display: true,
          text: props.device_unit,
        },
      },
    },
  };
});

// Functions

  function rotateIcons() {

  }
</script>
