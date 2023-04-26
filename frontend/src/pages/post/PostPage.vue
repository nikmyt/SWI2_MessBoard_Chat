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
        <div class="post-page">
          <div class="post-page__content">
            <h1>{{ post.title }}</h1>
            <p>Posted by {{ post.user.username }} on {{ post.createdAt }}</p>
            <p>{{ post.text }}</p>
          </div>
          <div class="post-page__comments">
            <h2>Comments:</h2>
            <div v-for="comment in comments" :key="comment.id">
              <p>{{ comment.text }}</p>
              <p>Commented by {{ comment.user.username }} on {{ comment.createdAt }}</p>
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

export default {
  name: "PostPage",
  components: {RightMenu, LeftMenu, TopBar},
  props: {
    post: {
      type: Object, //as PostModel, //sadly not PostModel. but post? sure. no?
      required: true
    }
  },
  data() {
    return {
      comments: []
    };
  },
  mounted() {
    this.fetchComments();
  },
  methods: {
    async fetchComments() {
      const response = await fetch(`/api/posts/${this.post.postId}/comments`);
      const data = await response.json();
      this.comments = data.comments;
    }
  }
};
</script>

<style scoped>

</style>
