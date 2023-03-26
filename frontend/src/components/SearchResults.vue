<template>
  <div>
    <h1>Search Results for "{{ searchQuery }}"</h1>
    <!-- Display search results here -->
  </div>
</template>

<script>
export default {
  name: "SearchResults",
  data() {
    return {
      searchQuery: this.$route.query.q,
    }
  }
}

</script>

<style scoped>

</style>