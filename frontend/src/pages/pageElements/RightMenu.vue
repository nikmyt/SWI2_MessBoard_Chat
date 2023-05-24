<template>
  <aside class="sidebar-right">

    <button @click="createPost" v-if=isLoggedIn> Create a new post </button>
    <button v-else disabled> Log in to post</button>
    <button @click="viewHistory" v-if=isLoggedIn> View your posts </button>
    <button v-else disabled> Log in to view your posts</button>

    <h2>Other pages</h2>
    <ul>
      <li><a href="#">Page 1</a></li>
      <li><a href="#">Page 2</a></li>
      <li><a href="#">Page 3</a></li>
      <p>Some description</p>
    </ul>
  </aside>
</template>

<script>
export default {
  name: "RightMenu",
  data() {
    return {
      isLoggedIn: localStorage.getItem("user") !== null,
    }
  },
  methods: {
    createPost(){
      this.$router.push('/createPost');
    },
    viewHistory(){
      console.log("Unimplemented!") //TODO: implement view history
    }
  }
}
</script>

<style scoped>

</style>