<template>
  <div class="post">
    <!--div class="post__rating">{{ post.rating }}</div>
    <div-- class="post__emoji">{{ post.emoji }}</div-->
    <div class="post__content">
      <h2 class="post__title">{{ post.name }}</h2>
      <p class="post__description">{{ post.timeCreated }}</p>
      <p class="post__comments">#of comments</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "mainPagePost.vue",
  props: {
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

.post-rating {
  font-size: 1.5em;
  margin-right: 10px;
}

.post-emoji {
  font-size: 2em;
  margin-right: 10px;
}

.post-content {
  flex-grow: 1;
}

.post-content h3 {
  margin: 0;
}

.post-content p {
  margin: 10px 0;
}

.post__comments{
  color: #5d536c;
  float: right;
}
</style>