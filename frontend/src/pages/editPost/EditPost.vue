<template>
  <header>
    <link rel="stylesheet" href="src/assets/mainpage.css">
    <TopBar />
  </header>

  <div class="container">
    <LeftMenu />

    <main class="main-content">
      <h1>Create a edit post</h1>
      <div class="post-form">
        <form @submit.prevent="editPost">
          <label for="title">Title:</label>
          <input type="text" id="title" v-model="editTitle">
          <label for="text">Text:</label>
          <input type="text" id="text" v-model="editText">
          <label for="tag">Tag:</label>
          <input type="text" id="tag" v-model="editTag">
          <!--select v-model="editTaskCategory">
            <option v-for="category in categories" :key="category">{{ category }}</option>
          </select-->
          <button v-if="isLoggedIn" type="submit">Submit edited post</button>
          <button v-else disabled>Log in to edit posts, cheeky!</button>
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
      editTitle: "",
      editText: "",
      editTag: "",
      editUser: "",
      const: localStorage.getItem('token'),
      createdAt: Date
    }
  },methods: {

    async editPost() {

      if (this.editTitle, this.editText, this.editTag === ""){
        console.log("Cannot edit a post to be empty!")
      } else {
        const post = {
          title: this.editTitle,
          text: this.editText,
          tag: this.editTag,
          userId: this.const,
          createdAt: new Date()

        };
        await ApiClient.updatePost(post);
        //TODO: check if response good, then u can return. else throw error.
        this.$router.push('/');
      }
    },
  }
}
</script>

<style scoped>

</style>