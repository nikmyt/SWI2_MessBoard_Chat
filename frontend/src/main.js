import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from "vue-router"

import App from './App.vue'
import HelloWorld from './components/HelloWorld.vue'
import MainPage from "@/components/MainPage.vue";
import error from "@/components/error.vue";
import SearchResults from "@/components/SearchResults.vue";

import './assets/main.css'


const routes = [
    { path: '/', component: HelloWorld },
    { path: '/about', component: MainPage },
    { path: '/*', component: error },
    { path: '/search/:query', name: 'SearchResults', component: SearchResults },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes, // short for `routes: routes`
    //mode: history
})

const app = createApp(App) //app?
app.use(router)
app.mount('#app')

//createApp(App).mount('#app') //original