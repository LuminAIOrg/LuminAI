<template>
  <div id="app">
    <h2>Energy Overview</h2>
    <div style="position: relative; margin: auto; height: 45vh; width: 45vw">
      <canvas id="energyOverviewChart"></canvas>
    </div>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import {getUpdatedEnergyDevices} from "@/services/PowerService";

export default {
  mounted() {
    this.updateChart();
  },
  methods: {

    async updateChart() {
      console.log("kasdkkasdjonasmdjansdkjhasnd")
      console.log(await getUpdatedEnergyDevices())
      getUpdatedEnergyDevices().then(datapoints => {
        const name = datapoints.devices[0].map(element => element.name);
        const value = datapoints.devices[0].map(element => element.value);
        const data = {
          labels: name,
          datasets: [{
            label: 'Weekly Energy Overview',
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
      });
    }
  }
};
</script>
