import { createRouter, createWebHistory } from 'vue-router'
import List from '@/vue/List.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/List',
      component:List,
    },
  ],
})

export default router
