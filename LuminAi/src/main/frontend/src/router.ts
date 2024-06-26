import DriverView from "@/components/driverPage/DriverView.vue";
import HomePage from "@/components/HomePage.vue";
import { createMemoryHistory, createRouter } from 'vue-router'


const routes = [
    { path: '/', component: HomePage },
    { path: '/driver', component: DriverView },
]

const router = createRouter({
    history: createMemoryHistory(),
    routes,
})

export default router