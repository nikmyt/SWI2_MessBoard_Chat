<template>
  <div>
    <h2>{{ post.title }}</h2>
    <p>{{ post.createdAt }}</p>
    <p>{{ post.text }}</p>
    <h3>Comments:</h3>
    <div v-for="comment in comments" :key="comment.id">
      <p>{{ comment.text }}</p>
      <p>{{ comment.createdAt }}</p>
      <p>{{ comment.user.username }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "PostPage",
  props: {
    post: {
      type: Object,
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
    fetchComments() {
      // Make an API call to fetch the comments for the post

    }
  }
};
</script>

<style scoped>

</style>
