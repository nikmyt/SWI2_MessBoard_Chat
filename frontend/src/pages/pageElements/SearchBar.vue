<template>
  <div class="search">
    <form @submit="search">
      <input type="text" placeholder="Search for boards, users, tags..." v-model="searchQuery">
      <button type="submit">Search</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
    data() {
      return {
        searchQuery: ''
      }
    },
    methods: {
      search(event) {
        event.preventDefault(); //VERY IMPORTANT!
        if (this.searchQuery) {
          this.$router.push({ name: 'SearchResults', params: { searchQuery: this.searchQuery } }); //params or props??!
        }
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

</style>