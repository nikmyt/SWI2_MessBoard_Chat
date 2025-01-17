import { createApp } from 'vue'
import { createRouter, createWebHistory, createWebHashHistory, RouterLink } from "vue-router"
//import VueStomp from "vue-stomp";
//import SockJS from 'sockjs-client';

import App from './App.vue'
import MainPage from "@/pages/mainpage/MainPage.vue";
import error from "@/pages/error/error.vue";
import SearchResults from "@/pages/search/SearchResults.vue";
import PostPage from "@/pages/post/PostPage.vue";

import CreatePost from "@/pages/createPost/CreatePost.vue";
import EditPost from "@/pages/editPost/EditPost.vue";

import LoginPage from "@/pages/login/LoginPage.vue";
import { ApiClient } from "@/client/ApiClient";
import RegisterPage from "@/pages/register/RegisterPage.vue";
import History from "@/pages/post/History.vue";

import ChatPageStomp from "@/pages/chat/ChatPageStomp.vue";
import ChatPageSockJS from "@/pages/chat/ChatPageSockJS.vue";

const routes = [
    { path: '/', name: "MainPage" || "Main", component: MainPage },
    { path: '/about', component: error },
    { path: '/:catchAll(.*)', component: error },
    { path: '/search/:searchQuery', name: 'SearchResults', component: SearchResults, props: true },
    { path: '/viewHistory', name: 'History', component: History, props: true },
    { path: '/login', name: 'LoginPage', component: LoginPage, props: (route) => ({ credentials: route.query.credentials }) },
    { path: '/createPost', name: 'CreatePost', component: CreatePost},
    { path: '/register', name: 'RegisterPage', component: RegisterPage},
    { path: '/chatpageStomp', name: 'ChatPageStomp', component: ChatPageStomp}, //not set up, maybe try later
    { path: '/chatpageSock', name: 'ChatPageSockJs', component: ChatPageSockJS},
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
    },

    { path: "/edit/:postId", name: "EditPost", component: EditPost, beforeEnter: (to, from, next) => {
            const token = localStorage.getItem('token');
            const postId = to.params.postId;

            ApiClient.getPost(postId).then((post) => {
                const userId =JSON.stringify(post.user.userId);
                    if (userId === token)
                    {
                        //TRY: == vs ===

                        next();
                    } else {
                        //next('/'); //just throw them back to the mainpage
                        return false;
                    }
                })
                .catch((error) => {
                    console.error(error);
                    console.log("Unprecedented edit error! Post doesn't exist, user doesn't exist, etc.")
                    next('/error');
                });
        }
    },
]

const router = createRouter({
    history: createWebHistory('/'),
    mode: 'history',
    routes // short for `routes: routes`
})

const app = createApp(App)


app.use(router)
/*
try {
    app.use(VueStomp, "http://localhost:8080/api", {SockJS});} // /api/message/send  i dont know
catch (error) {
    console.log("Can't connect through stomp/websockets. Is server down?")
}

try {
    export const EventBus = new Vue();
} //i may have put this in the wrong place
catch (error) {
    console.log("Could not create Event Bus.")
}
// send json: {id, name}
 */
app.mount('#app')