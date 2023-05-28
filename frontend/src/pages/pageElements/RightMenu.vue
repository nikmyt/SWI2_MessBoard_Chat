<template>
  <aside class="sidebar-right">
    <button @click="createPost" v-if="isLoggedIn">Create a new post</button>
    <button v-else disabled>Log in to post</button>
    <button @click="viewHistory" v-if="isLoggedIn">View your posts</button>
    <button v-else disabled>Log in to view your posts</button>

    <h2>Your stats:</h2>
    <ul>
      <li>Posts posted: {{ postCount }}</li>
      <li>Comments commented: {{ commentCount }}</li>

    </ul>
  </aside>
</template>

<script>
import { ApiClient } from "@/client/ApiClient";

export default {
  name: "RightMenu",
  data() {
    return {
      isLoggedIn: localStorage.getItem("user") !== null,
      postCount: 0,
      commentCount: 0,
      editCount: 0,
    };
  },
  mounted() {
    if (this.isLoggedIn) {
      this.fetchUserStats();
    }
  },
  methods: {
    createPost() {
      this.$router.push("/createPost");
    },
    viewHistory() {
      console.log("Unimplemented!"); // TODO: implement view history
    },
    async fetchUserStats() {
      try {
        const userId = localStorage.getItem("token");
        this.postCount = await ApiClient.getPostCount(userId);
        this.commentCount = await ApiClient.getCommentCount(userId);

      } catch (error) {
        console.error("Error fetching user stats:", error);
      }
    },
  },
};
</script>

<style scoped>
ul {
  list-style: none;
  padding: 0;
}
</style>
