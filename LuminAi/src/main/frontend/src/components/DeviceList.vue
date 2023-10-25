<template>

  <li v-for="line in data" v-bind:key="line">
    {{line}}
  </li>
    <Device name="hello" value=-1></Device>

</template>

<script setup>
  //import {getUpdatedEnergyDevices} from "@/services/PowerService";
  //import {defineComponent} from "vue";
  //import Device from "@/components/DeviceComponent.vue";
//
  //export default defineComponent({
  //  components: {Device}
  //})


  import Device from "@/components/DeviceComponent.vue";

  let data = [1,2]

</script>