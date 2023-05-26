<template>
  <div class="post">
    <div class="post__content">
      <router-link
          :to="{
          name: 'PostPage',
          params: { postId: post.postId },
        }"
          class="post-link"
      >
        <h2 class="post-title">{{ post.title ? post.title : 'Invalid title' }}</h2>
      </router-link>
      <div class="post-description">
        <p>By: {{ post.user?.username ? post.user.username : 'Invalid user' }}</p>
        <p>Posted at: {{ formatDate(post.createdAt) }}</p>
        <p> Tagged as: {{post.tag ? post.tag : 'Invalid tag' }}</p>
      </div>
      <h4>{{ post.text ? post.text : 'Invalid text' }}</h4>
    </div>
  </div>
</template>

<script>
import PostPage from "../post/PostPage.vue";

export default {
  name: "mainPagePost.vue",
  props: {
    post: Object,
  },
  methods: {
    goToPostPage(post) {
      this.$router.push({ name: "PostPage", params: { postId: post.id } });
    },
    formatDate(date) {
      if (!date) {
        return "Invalid time created";
      }
      const formattedDate = new Date(date).toLocaleDateString("en-US", {
        year: "numeric",
        month: "long",
        day: "numeric",
      });
      return formattedDate;
    },
  },
};
</script>

<style scoped>

.body {
  color: #dedbe3;
}

.post-link {
  color: inherit;
  text-decoration: none;
  cursor: pointer;
}

.post {
  min-width: 450pt; /*TODONE make this work on other displays apart from 1080p*/
  display: -ms-flexbox;
  align-items: stretch;
  margin: 10px 0;
  padding: 10px;
  border: 1px solid gray;
}

.post-title {
  margin-right: 10px;
}

.post-description {
  flex-grow: 1;
  display: flex;
  justify-content: space-between;
}

.post-description p {
  flex: 1;
  margin-right: 10px; /* Optional margin between the <p> elements */
}

</style>