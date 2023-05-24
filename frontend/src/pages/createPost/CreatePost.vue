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
        <form @submit.prevent="createPost">
          <label for="title">Title:</label>
          <input type="text" id="title" v-model="newTitle">
          <label for="text">Text:</label>
          <input type="text" id="text" v-model="newText">
          <label for="tag">Tag:</label>
          <input type="text" id="tag" v-model="newTag">
          <!--select v-model="newTaskCategory">
            <option v-for="category in categories" :key="category">{{ category }}</option>
          </select-->
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
import {ApiClient} from "@/client/ApiClient";

export default {
  name: "CreatePost",
  components: {LeftMenu, RightMenu, TopBar, ApiClient},
  data() {
    return {
      isLoggedIn: localStorage.getItem("user") !== null,
      newTitle: "",
      newText: "",
      newTag: "",
    }
  },methods: {
    async createPost() {
      const post = {
        title: this.newTitle,
        text: this.newText,
        tag: this.newTag,
      };
      await ApiClient.createPost(post);
      //TODO: check if response good, then u can return. else throw error.
      //this.$router.push('/');
    },
  }
}
</script>

<style scoped>

</style>