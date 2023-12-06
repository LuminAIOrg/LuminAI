<template>
  <Chart
      :size="{ width: 500, height: 400 }"
      :data="data"
      :margin="margin"
      :direction="'horizontal'"
  >
    <template #layers>
      <Grid strokeDasharray="2,2" />
      <Line :dataKeys="['timestamp', 'value']" />
    </template>
  </Chart>
</template>


<script>
import { defineComponent } from 'vue'
import { Chart, Grid, Line } from 'vue3-charts'
import { computed } from 'vue'
import {store} from "@/store/Store";

/*
import Chart from 'chart.js/auto'
export default {
  updateChart() {
    console.log("bsihadbfojabsdb")
    const name = computed(() => store.deviceData.map(entry => entry.name))
    const timestamp = computed(() => store.deviceData.map(entry => entry.timestamp))
    const value = computed(() => store.deviceData.map(entry => entry.value))

    const data = {
      labels: timestamp,
      datasets: [{
        label: name,
        borderColor: [
          'rgba(255, 102, 102)',
          'rgba(255, 178, 102)',
          'rgba(178, 255, 102)',
          'rgba(102, 255, 255)',
          'rgba(102, 102, 255)',
          'rgba(255, 102, 255)',
          'rgba(255, 102, 178)'
        ],
        data: value,
        borderWidth: 1
      }]
    };
    const config = {
      type: 'line',
      data,
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    };
    const energyOverviewChart = new Chart(
        document.getElementById('energyOverviewChart'),
        config
    );
    energyOverviewChart;
  }
};

updateChart();
*/

export default defineComponent({
  name: 'DeviceDataChart',
  components: {
    Chart,
    Grid,
    Line,
  },
  setup() {
    const data = computed(() => store.deviceData.map(entry => ({
      //date: new Date(parseInt(entry.timestamp) * 1000),
      //formattedDate: date.toLocaleDateString('de-DE'),
      timestamp: entry.timestamp,
      value: entry.value,
    })))

    const margin = { top: 20, right: 20, bottom: 20, left: 20 }
    const direction = 'horizontal'

    return { data, margin, direction }
  },
});

</script>
