<template>
  <header>
    <link rel="stylesheet" href="src/assets/mainpage.css">
    <TopBar />
  </header>

  <div class="container">
    <LeftMenu />

    <main class="main-content">
      <h1>Create a new post</h1>
      <div class="post-form">
        <form @submit.prevent="login">
          <label for="title">Title:</label>
          <input type="text" id="title" v-model="username">
          <label for="text">Text:</label>
          <input type="text" id="text" v-model="password">
          <button @click="createPost" v-if=isLoggedIn type="submit"> Submit post </button>
          <button v-else disabled> Log in to submit posts, ya cheeky bastard</button>
        </form>
      </div>
    </main>
    <RightMenu />
  </div>

  <Footer />

</template>

<script>
import RightMenu from "@/pages/pageElements/RightMenu.vue";
import LeftMenu from "@/pages/pageElements/LeftMenu.vue";
import TopBar from "@/pages/pageElements/TopBar.vue";

export default {
  name: "CreatePost",
  components: {LeftMenu, RightMenu, TopBar},
  data() {
    return {
      isLoggedIn: localStorage.getItem("user") !== null,
    }
  },
}
</script>

<style scoped>

</style>