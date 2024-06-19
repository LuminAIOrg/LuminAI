<script setup lang="ts">
import SettingsComponent from "@/components/SettingsComponent.vue";
import {onMounted} from "vue";
import {fetchDriverInstances, store} from "@/store/Store";
import DataCollectionInstance from "@/components/driverPage/DataCollectionInstance.vue";


onMounted(() => {
  fetchDriverInstances();
});
</script>


<template>
  <SettingsComponent></SettingsComponent>
  <DataCollectionInstance v-for="instance in store.driverInstances" :key="instance.id" :dataCollectionInstance="instance" ></DataCollectionInstance>
</template>

<style scoped>
</style>
