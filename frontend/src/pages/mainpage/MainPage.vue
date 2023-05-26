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
  <link rel="stylesheet" href="src/assets/mainpage.css">

  <div>
    <header>
      <TopBar />
    </header>

    <div class="container">
      <LeftMenu />

      <main class="main-content">
        <!-- Main content in the center -->
        <div>
          <h1>The Board:</h1>
          <p>Filter by: </p>
          <select @change="filterSelected" v-model="selectedFilter"> <!-- v-model="post.tag" -->
            <option v-for="filter in filters" :key="filter" :value="filter">{{ filter }}</option>
          </select>
        </div>
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
      posts: [],
      filters: ["Newest", "Oldest", "Tags A-Z", "Tags Z-A", "Title A-Z", "Title Z-A"],
      selectedFilter: '',
    }
  },
  methods: {
    async fetchPosts() {
      this.posts = await ApiClient.getPosts();
    },
    async filterSelected() {
      switch (this.selectedFilter) {
        case "Newest":
          this.posts = await ApiClient.getPostsByNewest();
          break;
        case "Oldest":
          this.posts = await ApiClient.getPostsByOldest();
          break;
        case "Tags A-Z":
          this.posts = await ApiClient.getPostsByTagsAZ();
          break;
        case "Tags Z-A":
          this.posts = await ApiClient.getPostsByTagsZA();
          break;
        case "Title A-Z":
          this.posts = await ApiClient.getPostsByTitleAZ();
          break;
        case "Title Z-A":
          this.posts = await ApiClient.getPostsByTitleZA();
          break;
        default:
          this.posts = await ApiClient.getPostsByNewest();
          break;
      }
    }


  },
  computed: {
  },
  mounted() {
    this.fetchPosts();
  }
}
</script>

<style scoped>

</style>