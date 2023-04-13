<template>
  <div class="post">
    <div class="post__content">
      <h2 class="post__title">Title: {{ post.title }}</h2>
      <p class="post__description">Time: {{ post.createdAt }}</p>
      <p class="post__user">UserID: {{post.user ? post.user.userId : ''}}</p>
      <p class="post__username">Username: {{post.user ? post.user.username : ''}}</p>
      <h2 class="post__title">{{ post.title }}</h2>
      <p class="post__description">{{ post.createdAt }}</p>
      <!--p v-if="post.user" class="post__user">{{post.user.userId}}</p-->
      <p v-if="post.user" class="post__username">{{post.user.username}}</p>
      <p class="post__text">{{ post.text }}</p>
      <!-- interesting, if theres user-->
    </div>
  </div>
</template>

<script>
export default {
  name: "mainPagePost.vue",
  props:
      {
    post: Object
  }
}
</script>

<style scoped>

.body {
  color: #dedbe3;
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