<script>
export default {
  name: "TopBar"
}
</script>

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
</template>

<style scoped>

</style>
