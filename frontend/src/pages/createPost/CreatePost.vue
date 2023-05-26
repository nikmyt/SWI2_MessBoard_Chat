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

          <label for="tag">Tag:</label>
          <input type="text" id="tag" v-model="newTag"> <br>

          <label for="text">Text:</label>
          <textarea id="text" v-model="newText" style="width: 375px; height: 200px; max-height: 800px; overflow-y: auto; resize: none;"></textarea>



          <!--select v-model="newTaskCategory">
            <option v-for="category in categories" :key="category">{{ category }}</option>
          </select-->
          <br>
          <button v-if="isLoggedIn" type="submit">Submit post</button>
          <button v-else disabled>Log in to submit posts, cheeky!</button>
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
import Footer from "@/pages/pageElements/Footer.vue";

export default {
  name: "CreatePost",
  components: {LeftMenu, RightMenu, TopBar, ApiClient, Footer},
  data() {
    return {
      isLoggedIn: localStorage.getItem('token') !== null,
      newTitle: "",
      newText: "",
      newTag: "",
      newUser: "",
      const: localStorage.getItem('token'),
      createdAt: Date
    }
  },methods: {

    async createPost() {

      if (this.newTitle, this.newText, this.newTag === ""){
        console.log("Cannot create empty post!")
        return;
      } else {
        const post = {
          title: this.newTitle,
          text: this.newText,
          tag: this.newTag,
          userId: this.const,
          createdAt: new Date()

        };
        await ApiClient.createPost(post);
        //TODO: check if response good, then u can return. else throw error.
        this.$router.push('/');
      }
    },
  }
}
</script>

<style scoped>

</style>