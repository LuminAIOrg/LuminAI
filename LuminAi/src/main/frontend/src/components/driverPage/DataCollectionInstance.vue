<script setup lang="ts">
import {defineProps} from "vue";
import {deleteDriverInstance, startDriverInstance, stopDriverInstance} from "@/services/DriverInstanceService";


const props = defineProps(["dataCollectionInstance"]);

</script>

<template>
  <div class="border-r-4 mt-3 mb-3" :class="props.dataCollectionInstance.disabled ? 'bg-blue-700' : 'bg-white'">
    <h1>{{ props.dataCollectionInstance.name }}</h1>
    <h2>{{ props.dataCollectionInstance.serviceName }}</h2>
    <button v-if="!props.dataCollectionInstance.disabled" @click="stopDriverInstance(props.dataCollectionInstance.id)">
      Stop
    </button>
    <button v-else @click="startDriverInstance(props.dataCollectionInstance.id)">Start</button>
    <button @click="deleteDriverInstance(props.dataCollectionInstance.id)">Delete</button>
  </div>
</template>

<style scoped>

</style>