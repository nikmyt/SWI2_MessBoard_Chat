<template>
  <header>
    <link rel="stylesheet" href="/src/assets/mainpage.css">
    <TopBar />
  </header>

  <div class="container">
    <LeftMenu />

    <main class="main-content">
      <h1>Search Results for "{{ searchQuery }}"</h1>
      <div class="posts">
        <div v-for="post in searchResults" :key="post.postId">
          <MainPagePost :post="post" />
        </div>
      </div>
    </main>
    <RightMenu />
  </div>

  <Footer />

</template>

<script>


import "@/assets/mainpage.css";
import Footer from "@/pages/pageElements/Footer.vue";
import RightMenu from "@/pages/pageElements/RightMenu.vue";
import LeftMenu from "@/pages/pageElements/LeftMenu.vue";
import TopBar from "@/pages/pageElements/TopBar.vue";
import { ApiClient } from "@/client/ApiClient";
import MainPagePost from "@/pages/mainpage/MainPagePost.vue";

export default {
  name: "SearchResults",
  props: {
    searchQuery: {
      type: String,
      required: true
    }
  },
  components: { TopBar, LeftMenu, RightMenu, Footer, MainPagePost },
  data() {
    return {
      searchResults: []
    }
  },
  async mounted() {
    this.performSearch();
  },
  watch: {
    $route: {
      immediate: true,
      handler() {
        this.performSearch();
      }
    }
  },
  methods: {
    async performSearch() {
      try {
        const searchQuery = this.$route.params.searchQuery;
        console.log('Search query:', searchQuery);
        this.searchResults = await ApiClient.getSearchResults(searchQuery);
        console.log(this.searchResults);
      } catch (error) {
        console.error('Error fetching search results:', error);
      }
    }
  }
}

</script>

<style scoped>

.post-link {
  color: inherit;
  text-decoration: none;
  cursor: pointer;
}

.post {
  min-width: 450pt; /*TODONE make this work on other displays apart from 1080p*/
  display: -ms-flexbox;
  align-items: stretch;
  margin: 10px 0;
  padding: 10px;
  border: 1px solid gray;
}

.post-title {
  margin-right: 10px;
}

.post-description {
  flex-grow: 1;
  display: flex;
  justify-content: space-between;
}

.post-description p {
  flex: 1;
  margin-right: 10px; /* Optional margin between the <p> elements */
}

</style>
