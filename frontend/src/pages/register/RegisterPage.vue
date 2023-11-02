<template>
  <link rel="stylesheet" href="src/assets/mainpage.css">
  <TopBar />

  <div class="container">
    <LeftMenu />

    <main class="main-content">
      <h1>Register</h1>
      <div class="login-form">
        <form @submit.prevent="register">
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="newUsername">

          <label for="password">Password:</label>
          <input type="password" id="password" v-model="newPassword">


          <label for="password_hint">Password hint:</label>
          <input type="password_hint" id="password_hint" v-model="newPassword_hint">


          <label for="email">Email:</label>
          <input type="email" id="email" v-model="newEmail">

          <button type="submit">Register</button>
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
  name: "RegisterPage",
  components: {Footer, ApiClient, TopBar, LeftMenu, RightMenu},
  data() {
    return {
      newUsername: "",
      newPassword: "",
      newPassword_hint: "",
      newEmail: ""
    };
  },
  methods: {
    async register() {
      try {
        if (this.username, this.password, this.email === ""){
          console.log("Cannot register with empty username, password or email!")
          return;
        } else {
          const registration = {
            username: this.newUsername,
            password: this.newPassword,
            password_hint: this.newPassword_hint,
            email: this.newEmail,
          }
        await ApiClient.register(registration);
        console.log("Registered!")
        this.$router.push('/');
        }
      } catch (error) {
        console.log("Sorry! Couldn't register!")
        console.log(error);
      }
    }
  }
};
</script>

<style scoped>

</style>