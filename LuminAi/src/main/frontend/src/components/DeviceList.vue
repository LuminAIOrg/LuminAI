  <template>
  <li v-for="device in devices" v-bind:key="device.name">
    <Device :name=device.name :value=device.value></Device>
  </li>
</template>

<script setup>
import Device from "@/components/DeviceComponent.vue";
import {getUpdatedEnergyDevices} from "@/services/PowerService";
import {store} from "@/store/Store";
import {computed} from "vue";

getUpdatedEnergyDevices()
setInterval(() => {
  getUpdatedEnergyDevices()
}, 5000)

const devices = computed(() => {
  if (store.deviceData.devices === null) {
    console.log("no devices")
    return [{name: "no devices",value:"-1"}]
  }
  return store.deviceData.devices
})

</script>