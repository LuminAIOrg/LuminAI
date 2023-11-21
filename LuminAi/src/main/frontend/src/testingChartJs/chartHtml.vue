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

export default {
  mounted() {
    this.updateChart();
  },
  methods: {
    async fetchData() {
      const fetchUrl = 'http://localhost:8080/api/devices/getData.json';
      const res = await fetch(fetchUrl);
      const datapoints = await res.json();
      return datapoints;
    },
    updateChart() {
      this.fetchData().then(datapoints => {
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
