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
    <p class="login-text">Logged in as {{ username }}</p>
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

.search input {
  border-radius: 20px;
  padding: 10px;
  background-color: #5d536c; /*34495e*/
  border: none;
  color: white;
}

.login-text{
  color: #dedbe3;
}

.logo-link {
  color: inherit;
  text-decoration: none;
  cursor: pointer;
}

</style>
