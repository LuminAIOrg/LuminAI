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


export default defineComponent({
  name: 'DeviceDataChart',
  components: {
    Chart,
    Grid,
    Line,
  },
  setup() {
    const data = computed(() => store.deviceData.map(entry => ({
      timestamp: entry.timestamp,
      value: entry.value,
    })))

    const margin = { top: 20, right: 20, bottom: 20, left: 20 }
    const direction = 'horizontal'

    return { data, margin, direction }
  },
});
</script>
