

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from '@/vue/App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'


const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(ElementPlus)
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)


app.mount('#app')

