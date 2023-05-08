<template>
  <header>
    <TopBar />
  </header>

  <div class="login-form">
    <form @submit.prevent="login">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username">
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password">
      <button type="submit">Login</button>

    </form>
  </div>

  <Footer />
</template>

<script>
import {ApiClient} from "@/client/ApiClient";
import Footer from "@/pages/pageElements/Footer.vue";

export default {
  name: "LoginPage",
  components: {Footer, ApiClient},
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
        localStorage.setItem("token", response.token);
        localStorage.setItem("user", this.username)
        console.log("Logged in!")
        this.$router.push('/');
        // Redirect to homepage or a protected route
      } catch (error) {
        console.log("Sorry! Couldn't log in!")
        console.log(error);
        //Error popup: Sorry! Couldn't log in!
      }
    },
    async loginFake() {
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