
<template>
  <div class="">
    <div class="w-full -ml-24">
      <h1 class="text-5xl py-3 relative font-bold">Settings</h1>
    </div>

    <div class="p-5 -ml-5">
      <h1 class="text-lg font-bold pb-2 ">Change data Collection</h1>
      <div class="flex gap-10">
        <form @submit.prevent="handleSubmit">
          <select name="collectionMethod" class=" py-1.5">
            <option v-for="method in collectionMethods" :key="method">{{ method }}</option>
          </select>
          <button type="submit" class="px-3 py-1.5 bg-blue-500 rounded-lg font-bold text-white hover:text-gray-100 duration-150 ml-5">Apply</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {getDataCollectionMethod} from "@/services/PowerService";
import { ref, onMounted } from "vue";
import {newDriverInstane} from "@/services/DriverInstanceService";


export default {
  setup() {
    const collectionMethods = ref<string[]>([]);

    onMounted(async () => {
      collectionMethods.value = await getDataCollectionMethod();
    });

    return { collectionMethods };
  },
  methods: {
    async handleSubmit(event: any) {
      console.log(event.target.elements.collectionMethod.value)
      await newDriverInstane(event.target.elements.collectionMethod.value)
    }
  }
}

</script>

<style scoped>

</style>