<template>
  <link rel="stylesheet" href="/src/assets/mainpage.css">

  <div>
    <header>
      <TopBar />
    </header>
    <div class="container">
      <LeftMenu />
      <main class="main-content">
        <div class="post-page">
          <div class="post-page__content">
            <h2>{{ post.title || 'Invalid title' }}</h2>
            <h6>Posted by {{ post.user?.username || 'Invalid user' }} on {{ formatDate(post.createdAt) }}</h6>
            <h4>{{ post.text || 'Invalid content' }}</h4>

            <button v-if="isCurrentUserPostOwner" @click="editPost">Edit</button>
            <button v-if="isCurrentUserPostOwner" @click="deletePost">Delete</button>

          </div>
          <div class="post-page__comments">
            <p>Comments:</p>
            <div v-for="comment in comments" :key="comment.id">
              <div class="comment">
                <h6>Commented by {{ comment.user?.username || 'Invalid user' }} on {{ formatDate(comment.createdAt) }}</h6>
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
import { ApiClient } from "@/client/ApiClient";

export default {
  name: "PostPage",
  components: { RightMenu, LeftMenu, TopBar, Footer },
  props: {},
  data() {
    return {
      post: {},
      comments: [],
    };
  },
  async mounted() {
    const postId = this.$route.params.postId;
    try {
      const post = await ApiClient.getPost(postId);
      this.post = post;

      await this.fetchComments();
    } catch (error) {
      console.error(error);
    }
  },
  methods: {
    async fetchComments() {
      try {
        const comments = await ApiClient.getComments(this.post.postId);
        this.comments = comments;
        console.log("Post ID: " + this.post.postId);
      } catch (error) {
        console.error(error);
      }
    },
    formatDate(date) {
      if (!date) {
        return "Invalid time";
      }
      const formattedDate = new Date(date).toLocaleDateString("en-US", {
        year: "numeric",
        month: "long",
        day: "numeric",
      });
      return formattedDate; // Restricting date length to 10 characters
    },
    async deletePost() {
      try {

        const id = this.post.postId;
        await ApiClient.deletePost(id);

        //TODO: check if response good, then u can return. else throw error.
        this.$router.push('/');
      } catch (error) {
        console.error(error);
      }
    },
    editPost(){
      this.$router.push(this.$route.path + '/edit');
      //TODO: this should work but doesn't. If you go to /edit manually, it "kicks you off".
    },
  },
  computed: {
    isCurrentUserPostOwner() {
      const token = localStorage.getItem('token');
      if (!token || !this.post.user) {
        return false;
      }
      return this.post.user.userId === parseInt(token);
    },
  },
};
</script>

<style scoped>
.comment {
  width: 500pt;
  display: -ms-flexbox;
  align-items: stretch;
  margin: 10px 0;
  padding: 10px;
  border: 1px solid gray;
}
</style>
