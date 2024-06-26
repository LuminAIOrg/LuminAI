  <script setup lang="ts">
import {defineProps} from "vue";
import {deleteDriverInstance, startDriverInstance, stopDriverInstance} from "@/services/DriverInstanceService";


const props = defineProps(["dataCollectionInstance"]);

</script>
  <!--  -->
<template>
  <div class="rounded-lg drop-shadow-lg mt-3 mb-3 bg-white">
    <div class="flex py-6 min-w-fit mx-10">
      <div class="w-3/12">
        <h1 class="text-gray-400">Status</h1>
        <h2 class="text-xl font-bold text-red-500" v-if="props.dataCollectionInstance.disabled">Stopped</h2>
        <h2 class="text-xl font-bold text-green-500" v-else>Running</h2>
      </div>

      <div class="w-3/12">
        <h1 class="text-gray-400">#</h1>
        <h2>{{ props.dataCollectionInstance.name }}</h2>
      </div>

      <div class="w-full">
        <h1 class="text-gray-400 ">Type</h1>
        <h2>{{ props.dataCollectionInstance.serviceName }}</h2>
      </div>

      <div class="w-2/12 ">
        <div>
          <h1 class="text-gray-400">Controls</h1>
        </div>

        <div class="flex gap-x-2">
          <button class="bg-orange-400 rounded-lg px-2 py-1 text-white" v-if="!props.dataCollectionInstance.disabled" @click="stopDriverInstance(props.dataCollectionInstance.id)">
            Stop
          </button>
          <button class="bg-green-500 rounded-lg px-2 py-1 text-white" v-else @click="startDriverInstance(props.dataCollectionInstance.id)">Start</button>
          <button class="bg-red-500 rounded-lg px-2 py-1 text-white" @click="deleteDriverInstance(props.dataCollectionInstance.id)">Delete</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>