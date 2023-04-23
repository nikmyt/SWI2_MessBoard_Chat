<script setup>
import MainPagePost from "@/pages/mainpage/MainPagePost.vue";
//import PostPage from "@/pages/post/PostPage.vue"; //backup postpage that doesn't work in script
</script>

<template>
  <link rel="stylesheet" href="src/assets/mainpage.css"> <!-- what do you mean 'head'? -->

  <div>
    <header>
      <!-- Logo and search bar -->
      <div class="logo">
        <img src="/src/assets/piggers.svg" alt="Logo">
        <!--TODO make it clickable to get back to main page and everywhere-->
      </div>
      <h1 class="title">MessBoard</h1>
      <div class="search">
        <input type="text" placeholder="Search for boards, users, terms...">
      </div>

      <!-- Hamburger menu. doens't show up lol. TODO -->
      <div class="hamburger-menu">
        <span>Item 1</span>
        <span>Item 2</span>
        <span>Item 3</span>
      </div>
    </header>

    <div class="container">
      <aside class="sidebar-left">
        <!-- Sidebar on the left. not really. TODO -->
        <h2>Other pages</h2>
        <ul>
          <li><a href="#">Page 1</a></li>
          <li><a href="#">Page 2</a></li>
          <li><a href="#">Page 3</a></li>
          <p>Some description</p>
        </ul>
      </aside>
      <main class="main-content">
        <!-- Main content in the center -->
        <h1>b/all</h1>
        <div class="posts">
          <!-- Load posts from database using Axios. TODO post pagination -->
          <div v-for="post in posts" :key="post.id" @click="goToPostPage(post)">
            <MainPagePost :post="post" />
          </div>
          <!--post v-for="post in posts" :post="post" :key="post.id"></post-->
        </div>
        <!-- OR we put the single post here. <PostPage :post="post" and then load comments for it. -->
      </main>
      <aside class="sidebar-right">
        <h2>Board Description</h2>
        <p>Short description of board here.</p>
      </aside>
    </div>

    <footer class="footer">
      <p>Copyright us two dumdums.</p>
    </footer>


  </div>
</template>

<script>
import axios from 'axios'
import PostPage from "@/pages/post/PostPage.vue";

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
      // Make an API call to fetch the posts
      //mounted();
    },
    goToPostPage(post) {
      this.$router.push({ name: "PostPage", params: { postId: post.id } });
    }
  },
  created() {
    this.fetchPosts();
  },
  mounted() {
    axios.get('http://localhost:8080/posts')
        .then(response =>
        {
          this.posts = response.data //for single post [response.data]
        })
        .catch(error => {
          console.log(error)
        })
  }
}
</script>

<style scoped>

</style>