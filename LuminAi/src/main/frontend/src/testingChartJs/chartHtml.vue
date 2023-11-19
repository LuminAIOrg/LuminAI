<template>
  <div id="app">
    <h2>Energy Overview</h2>
    <div style="position: relative; margin: auto; height: 45vh; width: 45vw">
      <canvas id="energyOverviewChart"></canvas>
    </div>
  </div>
</template>

<script >
  import Chart from 'chart.js/auto';

  export default {
    props: {
      msg: String
    },

    mounted() {
      console.log('Component mounted.')
      var options = {
        maintainAspectRatio: false,
        scales: {
          y: {
            stacked: true,
            grid: {
              display: true
            }
          },
          x: {
            grid: {
              display: false
            }
          }
        }
      }
      const energyOverviewChart = new Chart('energyOverviewChart', {
        type: 'line',
        options: options,
        data: {
          labels: ["Januar", "Februar", "März", "April", "Mai", "Juni", "Juli"],
          datasets: [{
            label: 'pv',
            data: [2273, 1536, 2650, 200, 350, 204, 546],
            borderColor: 'rgb(255, 153, 51)',
            tension: 0.1
            },
            {
            label: 'Akku',
            data: [1000, 899, 150, 2300, 390, -50, 222],
            borderColor: 'rgb(153, 255, 255)',
            tension: 0.1
            },
            {
              label: 'Wärmepumpe',
              data: [-600, -250, 20, 450, 2050, 800, 999],
              borderColor: 'rgb(153, 255, 153)',
              tension: 0.1
            },
            {
              label: 'Balkonkraftwerk',
              data: [300, 405, 1000, 2650, 200, -300, 50],
              borderColor: 'rgb(255, 153, 204)',
              tension: 0.1
            }]
        }

      });
      energyOverviewChart;
    }
  }
</script>