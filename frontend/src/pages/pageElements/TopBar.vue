<template>
  <!-- Logo and search bar -->
  <router-link :to="{ name: 'MainPage' }" class="logo-link">
    <div class="logo">
      <img src="/src/assets/piggers.svg" alt="Logo">
      <!--TODONE make it clickable to get back to main page and everywhere-->
    </div>
  </router-link>
  <router-link :to="{ name: 'MainPage'}" >
    <h1 class="title">MessBoard</h1>
  </router-link>

  <SearchBar />

  <!-- Hamburger menu. doens't show up lol. TODO
  <div class="hamburger-menu">
    <span>Item 1</span>
    <span>Item 2</span>
    <span>Item 3</span>
  </div>
  -->

  <div class="user-info">
    <button @click="login" v-if="!isLoggedIn">Login</button>
    <button @click="logout" v-if="isLoggedIn">Logout</button>
    <button @click="register" v-if="!isLoggedIn">Register</button>
    <!-- Should you be able to register when logged in? -->
  </div>
  <p class="login-text" v-if="isLoggedIn"  style="padding-left: 10px;">Logged in as {{ username }}</p>
  <p class="login-text" v-else  style="padding-left: 10px;">Not logged in.</p>
</template>

<script>
import SearchBar from "@/pages/pageElements/SearchBar.vue";

export default {
  components: {SearchBar},
  data() {
    return {
      username: '',
      isLoggedIn: localStorage.getItem("user") !== null, //kind of hate how this is on every page
    };
  },
  mounted() {
    const user = localStorage.getItem("user");
    if (user) {
      this.username = user;
    } else {
      this.username = null
      //okay, somehow my logout null checks worked despite me setting it as not-null.
    }
    /*const token = localStorage.getItem('token'); // retrieve the token from localStorage
    if (token) {
      const [payload] = token.split('.'); // split the token into header, payload and signature
      const decodedPayload = atob(payload); // decode the payload using the atob function
      const { username } = JSON.parse(decodedPayload); // parse the payload as JSON and extract the username
      this.username = username; // set the username in the component data
    }*/
  },
  methods: {
    logout() {
      localStorage.removeItem('token'); // remove the token from localStorage
      localStorage.removeItem("user");
      window.location.reload();
    },
    login(){
      this.$router.push('/login');
    },
    register(){
      this.$router.push('/register');
    }
  }
}
</script>

<style scoped>

.login-text{
  color: #dedbe3;
}

.logo-link {
  color: inherit;
  text-decoration: none;
  cursor: pointer;
}

</style>
