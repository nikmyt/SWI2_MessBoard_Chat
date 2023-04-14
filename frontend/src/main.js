import { createApp } from 'vue'
import { createRouter, createWebHashHistory } from "vue-router"

import App from './App.vue'
import MainPage from "@/components/MainPage.vue";
import error from "@/components/error.vue";
import SearchResults from "@/components/SearchResults.vue";
import PostPage from "@/components/elements/PostPage.vue";

import './assets/main.css'


const routes = [
    { path: '/', component: MainPage },
    { path: '/about', component: MainPage },
    { path: '/*', component: error },
    { path: '/search/:query', name: 'SearchResults', component: SearchResults },
    { path: "/posts/:postId", name: "PostPage", component: PostPage,
        props: route => ({
            post: {
                id: route.params.postId,
                // You can pass any other post data you have as props here
            }
        })
    }
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