<template>

  <link rel="stylesheet" href="/src/assets/mainpage.css"> <!-- what do you mean 'head'? -->

  <div>
    <header>
      <TopBar />
    </header>
    <div class="container">
      <LeftMenu />
      <main class="main-content">
        <!-- Main content in the center -->
        <div class="post-page">
          <div class="post-page__content">
            <h2>{{ post.title || "" }}</h2>
            <h6>Posted by {{ post.user?.username || "" }} on {{ post.createdAt || "" }}</h6>
            <p>{{ post.text || "" }}</p>
          </div>
          <div class="post-page__comments">
            <h3>Comments:</h3>
            <div v-for="comment in comments" :key="comment.id">
              <h6>Commented by {{ comment.user?.username || "" }} on {{ comment.createdAt || "" }}</h6>
              <p>{{ comment.text }}</p>
            </div>
          </div>
        </div>
      </main>
      <RightMenu />
    </div>

    <Footer />
  </div>
</template>

<script>

import TopBar from "@/pages/pageElements/TopBar.vue";
import LeftMenu from "@/pages/pageElements/LeftMenu.vue";
import RightMenu from "@/pages/pageElements/RightMenu.vue";
import Footer from "@/pages/pageElements/Footer.vue";
import {ApiClient} from "@/client/ApiClient";

export default {
  name: "PostPage",
  components: {RightMenu, LeftMenu, TopBar, Footer},
  props: {
  },
  data() {
    return {
      post: {},
      comments: []
    };
  },
  async mounted() {
    const postId = this.$route.params.postId;
    try {
      let post;
      post = await ApiClient.getPost(postId);
      this.post = post;
      await this.fetchComments();
    } catch (error) {
      console.error(error);
    }
  },
  methods: {
    async fetchComments() {
      try {
        let comments;
        comments = await ApiClient.getComments(this.post.postId);
        this.comments = comments;
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>

<style scoped>

</style>
