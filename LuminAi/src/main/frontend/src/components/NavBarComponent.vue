
<template>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
  <div class="min-h-screen w-2/5 xl:w-1/5 fixed top-0 shadow-lg">
    <div class="h-screen flex flex-col justify-between bg-blue-500">

      <RouterLink class="w-full flex gap-3" to="/">
        <div class="w-full flex justify-center">
          <div class="w-3/5 h-full mt-10 flex justify-evenly items-center">
            <button class="text-white" style="font-size: 4.5rem !important;">
              <img class="h-16 w-16 text-white"  style="filter: invert(100%) sepia(0%) saturate(7489%) hue-rotate(295deg) brightness(109%) contrast(99%);" src="../assets/Logo.svg" alt="luminai logo">
            </button>
            <h1 class="text-3xl text-white font-bold">LuminAi</h1>
          </div>
        </div>
      </RouterLink>

      <div class="w-full flex justify-center">
        <div class="h-full  gap-6 relative items-center text-xl text-white left-2">
          <div class="flex flex-col gap-4 cursor-pointer">

            <RouterLink class="w-full flex gap-3" to="/">
              <div class="flex p-2 rounded-lg duration-150 hover:bg-blue-600">
                  <span class="material-symbols-outlined" style="font-size: 2rem; !important;">home</span>
                  <h1 class="text-2xl">Home</h1>
              </div>
            </RouterLink>

            <RouterLink class="w-full flex gap-3" to="/driver">
              <div class="flex p-2 rounded-lg duration-150 hover:bg-blue-600">
                  <span class="material-symbols-outlined  " style="font-size: 2rem !important;">stacks</span>
                  <h1 class="text-2xl">Devices</h1>
              </div>
            </RouterLink>
          </div>

          <div class="grid gap-4 cursor-pointer text-2xl relative right-5">
          </div>
        </div>
      </div>

      <div class="w-full flex justify-center">
        <div class=" h-1/6 text-white mb-10">
          <div class="text-xl flex justify-center">
            <span class="material-symbols-outlined" style="font-size: 2rem !important;">account_circle</span>
          </div>
          <div class="text-2xl">
            <h1>{{ username }}</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<!--
<style scoped>

.flex:hover > .material-symbols-outlined {
  @apply text-red-400 duration-150 ease-in-out; /* change the icon color to blue-200 on hover */
}


</style>
-->

<script async setup lang="ts">
import {getUserName} from "@/services/PowerService";
const username = await getUserName();

</script>
