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
            <h2>{{ post.title || 'Invalid title' }}</h2>
            <h6>Posted by {{ post.user?.username || 'Invalid user' }} on {{ post.createdAt || 'Invalid time' }}</h6>
            <h4>{{ post.text || 'Invalid content' }}</h4>
          </div>
          <div class="post-page__comments">
            <p>Comments:</p>
            <div v-for="comment in comments" :key="comment.id">
              <div class="comment">
              <h6>Commented by {{ comment.user?.username || 'Invalid user' }} on {{ comment.createdAt || 'Invalid time' }}</h6>
              <p>{{ comment.text }}</p>
              </div>
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
      var post;
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
        var comments;
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

.comment {
  width: 500pt; /*TODO make this work on other displays apart from 1080p*/
  display: -ms-flexbox;
  align-items: stretch;
  margin: 10px 0;
  padding: 10px;
  border: 1px solid gray;
}

</style>
