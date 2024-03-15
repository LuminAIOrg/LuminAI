
<template>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
  <div class="min-h-screen w-1/5 fixed top-0">
    <div class="h-screen flex flex-col justify-between  w-full bg-blue-500">
      <div class="w-full flex justify-center">
        <div class="w-3/5 h-full relative top-5 flex justify-evenly items-center">
          <button class="p-2 text-2xl text-white" style="font-size: 4rem !important;">☼</button>
          <h1 class="text-3xl text-white font-bold">LuminAi</h1>
        </div>
      </div>

      <div class="w-full flex justify-center">
        <div class="h-full w-2/5 grid grid-cols-2 gap-6 relative items-center text-xl text-white left-2 ">
          <div class="grid gap-4 cursor-pointer">

            <div class="flex p-2 rounded-lg duration-150 gap-x-3 hover:bg-blue-600">
              <a>
                <span class="material-symbols-outlined" style="font-size: 2rem !important;">home</span>
                <h1 class="text-2xl">Home</h1>
              </a>
            </div>

            <div class="flex p-2 rounded-lg duration-150 gap-x-3 hover:bg-blue-600">
              <a>
                <span class="material-symbols-outlined" style="font-size: 2rem !important;">stacks</span>
                <h1 class="text-2xl">Devices</h1>
              </a>
            </div>

            <div class="flex p-2 rounded-lg duration-150 gap-x-3 hover:bg-blue-600">
              <a href="../pages/SettingsPage.vue">
                <span class="material-symbols-outlined" style="font-size: 2rem !important;">settings</span>
                <h1 class="text-2xl">Settings</h1>
              </a>
            </div>
          </div>

          <div class="grid gap-4 cursor-pointer text-2xl relative right-5">
          </div>
        </div>
      </div>

      <div class="w-full flex justify-center">
        <div class=" h-1/6 text-white relative -top-5">
          <div class="text-xl flex justify-center">
            <span class="material-symbols-outlined" style="font-size: 2rem !important;">account_circle</span>
          </div>
          <div class="text-2xl">
            <h1>Profile</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>

<script setup lang="ts">

</script>
