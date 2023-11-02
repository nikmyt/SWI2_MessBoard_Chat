<template>
  <link rel="stylesheet" href="/src/assets/mainpage.css">

  <div>
    <TopBar />
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
            <div class="comment-args">
              <p>Comments:</p>
              <button v-if="isLoggedIn" @click="toggleCommentForm">Create a comment</button>
            </div>
            <!-- this pops up -->
            <form v-if="showCommentForm" @submit.prevent="submitComment">
              <textarea v-model="commentText" placeholder="Enter your comment"></textarea>
              <button type="submit">Submit</button>
            </form>
            <!-- comments, TODO: put comments in their own element -->
            <div v-for="comment in comments" :key="comment.postId">
              <div class="comment">
                <h6>Commented by {{ comment.username || 'Invalid user' }} on {{ formatDate(comment.createdAt) }}</h6>
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
      isLoggedIn: localStorage.getItem("user") !== null, //kind of hate how this is on every page
      showCommentForm: false,
      commentText: '',
      const: localStorage.getItem('token'),
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
    async editPost() {
      const postId = this.post.postId;
      this.$router.push({ name: 'EditPost', params: { postId } });
    },
    toggleCommentForm() {
      this.showCommentForm = !this.showCommentForm;
    },
    submitComment() {
      const comment = {
        text: this.commentText,
        userId: this.const,
        postId: this.post.postId,
        createdAt: new Date(),
      };

    this.createComment(comment);
    this.commentText = '';
    this.showCommentForm = false;
    },
    createComment(comment) {
      if(comment.newText == "") {
        console.log("Cannot submit empty comment!")
        return;
      } else {
        //console.log('Creating comment:', comment.toString());
        ApiClient.createComment(comment).then(() => {
          this.comments.push(comment); //TODO: unsure if edit/delete will work correclty
          //OR
          //window.location.reload();
        })
      }
  }
  }, computed: {
    isCurrentUserPostOwner() {

      if (!this.const || !this.post.user ) {
        return false;
      }
      console.log('User ID:', this.post.user.userId);
      const parsedToken = parseInt(this.const, 10);
      console.log('Parsed Token:', parsedToken);

      return this.post.user.userId === parsedToken;
    },
  },
};
</script>

<style scoped>
.comment-args{
  display: flex;
  justify-content: space-between;
}

textarea{
  margin: 3px;
  width: 100%;
  min-width: 375px;
  height: 200px;
  max-height: 800px;
  overflow-y: auto;
  resize: none;
}

.comment {
  width: 500pt;
  display: -ms-flexbox;
  align-items: stretch;
  margin: 10px 0;
  padding: 10px;
  border: 1px solid gray;
}
</style>
