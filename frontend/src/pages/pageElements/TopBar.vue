<template>
  <!-- Logo and search bar -->
  <router-link :to="{ name: 'MainPage' }" class="post-link">
    <div class="logo">
      <img src="/src/assets/piggers.svg" alt="Logo">
      <!--TODO make it clickable to get back to main page and everywhere-->
    </div>
  </router-link>
  <h1 class="title">MessBoard</h1>
  <div class="search">
    <input type="text" placeholder="Search for boards, users, terms...">
  </div>

  <!-- Hamburger menu. doens't show up lol. TODO -->
  <div class="hamburger-menu">
    <span>Item 1</span>
    <span>Item 2</span>
    <span>Item 3</span>
  </div>

  <div class="user-info">
    <p>Logged in as {{ username }}</p>
    <button @click="login">Login or change user</button>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: ''
    };
  },
  mounted() {
    const user = localStorage.getItem("user");
    if (user) {
      this.username = user;
    } else {
      this.username = "Not logged in."
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
      //this.$router.push('/login'); // navigate to the login page
    },
    login(){
      this.$router.push('/login');
    }
  }
}
</script>

<style scoped>

</style>
