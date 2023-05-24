import { createApp } from 'vue'
import { createRouter, createWebHistory, createWebHashHistory, RouterLink } from "vue-router"


import App from './App.vue'
import MainPage from "@/pages/mainpage/MainPage.vue";
import error from "@/pages/error/error.vue";
import SearchResults from "@/pages/search/SearchResults.vue";
import PostPage from "@/pages/post/PostPage.vue";
import CreatePost from "@/pages/createPost/CreatePost.vue";

import './assets/main.css'
import LoginPage from "@/pages/login/LoginPage.vue";


const routes = [
    { path: '/', name: "MainPage" || "Main", component: MainPage },
    { path: '/about', component: error },
    { path: '/*', component: error },
    { path: '/search/:query', name: 'SearchResults', component: SearchResults },
    { path: '/login', name: 'LoginPage', component: LoginPage, props: (route) => ({ credentials: route.query.credentials }) },
    { path: '/createPost', name: 'CreatePost', component: CreatePost},
    { path: "/posts/:postId", name: "PostPage", component: PostPage,
        props: route => ({
            post: {
                postId: route.params.postId,


                title: route.params.title,
                text: route.params.text,
                createdAt: route.params.createdAt,
                username: route.params.username


                //due to bad entries, doesn't show nulls, commented for demo purposes
            }
        })
    }
]

const router = createRouter({
    history: createWebHistory('/'),
    mode: 'history',
    routes // short for `routes: routes`
})

const app = createApp(App) //app?
app.use(router)
app.mount('#app')

//createApp(App).mount('#app') //original