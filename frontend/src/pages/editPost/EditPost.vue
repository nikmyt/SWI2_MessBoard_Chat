<template>
  <header>
    <link rel="stylesheet" href="/src/assets/mainpage.css">
    <TopBar />
  </header>

  <div class="container">
    <LeftMenu />

    <main class="main-content">
      <h1>Edit post</h1>
      <div class="post-form">
        <form @submit.prevent="editPost">
          <label for="title">Title:</label>
          <input type="text" id="title" v-model="editTitle">
          <label for="text">Text</label>
          <textarea id="text" v-model="editText" style="width: 375px; height: 200px; max-height: 800px; overflow-y: auto; resize: none;"></textarea>

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

  name: "EditPost",
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
  },
  async mounted() {
    // Assuming you have the post data available
    const postId = this.$route.params.postId;
    try {
      const post = await ApiClient.getPost(postId);
      this.post = post;
    } catch (error) {
      console.error(error);
    }
    this.editText = this.post.text;
    this.editTag = this.post.tag;
    this.editTitle = this.post.title;
  },
  methods: {

    async editPost() {

      if (this.editTitle, this.editText, this.editTag === ""){
        console.log("Cannot edit a post to be empty!")
      } else {
        const post = {
          postId: Number(this.postId),
          title: this.editTitle,
          text: this.editText,
          tag: this.editTag,
          user: Number(this.const),
          createdAt: new Date()

        };
        await ApiClient.updatePost(this.post.postId, post);
        //TODO: check if response good, then u can return. else throw error.
        this.$router.push('/posts/'+this.post.postId);
      }
    },
  }
}
</script>

<style scoped>

</style>