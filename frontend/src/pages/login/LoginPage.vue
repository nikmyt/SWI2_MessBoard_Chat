<template>
    <link rel="stylesheet" href="src/assets/mainpage.css">
    <TopBar />

  <div class="container">
    <LeftMenu />

    <main class="main-content">
      <h1>Login or change user</h1>
      <div class="login-form">
        <form @submit.prevent="login">
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="username">
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="password">
          <button type="submit">Login</button>
        </form>
      </div>
    </main>
    <RightMenu />
  </div>

  <Footer />

</template>

<script>
import {ApiClient} from "@/client/ApiClient";
import Footer from "@/pages/pageElements/Footer.vue";
import TopBar from "@/pages/pageElements/TopBar.vue";
import RightMenu from "@/pages/pageElements/RightMenu.vue";
import LeftMenu from "@/pages/pageElements/LeftMenu.vue";

export default {
  name: "LoginPage",
  components: {Footer, ApiClient, TopBar, LeftMenu, RightMenu},
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    async login() {
      try {
        const response = await ApiClient.authenticate({
          username: this.username,
          password: this.password
        });
        //localStorage.setItem("token", response.token);
        localStorage.setItem("user", this.username)
        console.log("Logged in!")
        this.$router.push('/');
        // Redirect to homepage or a protected route
      } catch (error) {
        console.log("Sorry! Couldn't log in!")
        console.log(error);
        //TODO: Error popup: Sorry! Couldn't log in!
      }
    },
    async loginTrue() {
      const response = await ApiClient.authenticate({
        username: this.username,
        password: this.password
      });
      if (response.token) {
        this.$store.commit('setUser', response.user);
        this.$router.push('/');
        console.log("Good login.")
      } else {
        console.log("Bad login.")
        this.errorMessage = response.message;
      }
    }
  }
};
</script>

<style scoped>

</style>