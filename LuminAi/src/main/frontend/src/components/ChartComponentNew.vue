
<template>
  <canvas id="sensorChart" width="400" height="400"></canvas>

</template>

<script>
import Chart from 'chart.js/auto'
export default {
  mounted() {
    const ctx = document.getElementById("sensorChart");

    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
          label: '# of Votes',
          data: [12, 19, 3, 5, 2, 3],
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
}



</script>

<style scoped>

</style>