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
        <h2 class="post__title">Title: {{ post.title ? post.title : 'Invalid title' }}</h2>
      </router-link>
      <p class="post__description">Time: {{ formatDate(post.createdAt) }}</p>
      <p class="post__username">Username: {{ post.user?.username ? post.user.username : 'Invalid user' }}</p>
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
  width: 500pt; /*TODO make this work on other displays apart from 1080p*/
  display: -ms-flexbox;
  align-items: stretch;
  margin: 10px 0;
  padding: 10px;
  border: 1px solid gray;
}

.post-title {
  font-size: 1.5em;
  margin-right: 10px;
}

.post-description {
  flex-grow: 1;
}
.post-user {
  flex-grow: 1;
}
.post-username {
  flex-grow: 1;
}


</style>