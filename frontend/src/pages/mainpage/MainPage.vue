<script setup>
import MainPagePost from "@/pages/mainpage/MainPagePost.vue";
import { RouterLink } from "vue-router";
import TopBar from "@/pages/pageElements/TopBar.vue";
import LeftMenu from "@/pages/pageElements/LeftMenu.vue";
import Footer from "@/pages/pageElements/Footer.vue";
import RightMenu from "@/pages/pageElements/RightMenu.vue";
//import PostPage from "@/pages/post/PostPage.vue"; //backup postpage that doesn't work in script
</script>

<template>
  <link rel="stylesheet" href="src/assets/mainpage.css"> <!-- what do you mean 'head'? -->

  <div>
    <header>
      <TopBar />
    </header>

    <div class="container">
      <LeftMenu />

      <main class="main-content">
        <!-- Main content in the center -->
        <h1>b/all</h1>
        <div class="posts">
          <!-- Load posts from database using Axios. TODO post pagination -->
          <div v-for="post in posts" :key="post.postId">
              <MainPagePost :post="post" />
          </div>
          <!--post v-for="post in posts" :post="post" :key="post.id"></post-->
        </div>
        <!-- OR we put the single post here. <PostPage :post="post" and then load comments for it. -->
      </main>
      <RightMenu />
    </div>

    <Footer />


  </div>
</template>

<script>
import axios from 'axios'
import PostPage from "@/pages/post/PostPage.vue";
import {ApiClient} from "@/client/ApiClient";

export default {
  name: "MainPage",
  components: {
    PostPage
  },
  data() {
    return {
      posts: []
    }
  },
  methods: {
    async fetchPosts() {
      this.posts = await ApiClient.getPosts();
    }
  },
  mounted() {
    this.fetchPosts();
  }
}
</script>

<style scoped>

</style>