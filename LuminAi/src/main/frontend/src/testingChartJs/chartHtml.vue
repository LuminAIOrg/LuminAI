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

  // OLD CODE -- Working BUT hardcoded
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
          label: 'PV',
          data: [2273, 1536, 2650, 200, 350, 204, 546],
          borderColor: 'rgb(255, 153, 51)',
        },
          {
            label: 'Grid',
            data: [1000, 899, 150, 2300, 390, -50, 222],
            borderColor: 'rgb(250,74,74)',
          },
          {
            label: 'Akku',
            data: [1000, 899, 150, 2300, 390, -50, 222],
            borderColor: 'rgb(153, 255, 255)',
          },
          {
            label: 'Wärmepumpe',
            data: [-600, -250, 20, 450, 2050, 800, 999],
            borderColor: 'rgb(153, 255, 153)',
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

  // NEW CODE -- NOT WORKING - can't test because url (http://localhost:8080/api/devices/getData) doesn't work
  /*
  props: {
    msg: String,
  },

  data() {
    return {
      energyOverviewChart: null,
    };
  },

  mounted() {
    console.log('Component mounted.');
    fetch('http://localhost:8080/api/devices/getData')
        .then((response) => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json();
        })
        .then((data) => {
          this.updateChart(data);
        })
        .catch((error) => {
          console.error('Error fetching data:', error);
        });
  },

  methods: {
    updateChart(data) {
      var options = {
        maintainAspectRatio: false,
        scales: {
          y: {
            stacked: true,
            grid: {
              display: true,
            },
          },
          x: {
            grid: {
              display: false,
            },
          },
        },
      };

      const labels = data.devices.map((device) => device.name);

      // Assuming each device has a timestamp associated with the values
      const latestValues = {};

      data.devices.forEach((device) => {
        const name = device.name;
        const values = device.values;

        // Keep only the latest 10 values
        latestValues[name] = values.slice(-10);
      });

      // Create a new chart instance
      const canvas = document.getElementById('energyOverviewChart');

      if (!this.energyOverviewChart) {
        this.energyOverviewChart = new Chart(canvas, {
          type: 'line',
          options: options,
          data: {
            labels: labels,
            datasets: Object.keys(latestValues).map((name) => ({
              label: name,
              data: latestValues[name],
              borderColor: this.getRandomColor(),
              fill: false,
            })),
          },
        });
      }

      else {
        // Update existing chart with the latest values
        labels.forEach((label, index) => {
          this.energyOverviewChart.data.labels[index] = label;
        });

        this.energyOverviewChart.data.datasets.forEach((dataset) => {
          const name = dataset.label;
          dataset.data = latestValues[name];
        });

        this.energyOverviewChart.update();
      }
    },

    getRandomColor() {
      // Function to generate a random color
      return `rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(
          Math.random() * 256
      )}, ${Math.floor(Math.random() * 256)})`;
    },
  },

};

   */
</script>
