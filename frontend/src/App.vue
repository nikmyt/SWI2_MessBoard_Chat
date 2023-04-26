<script setup>
import MainPage from './pages/mainpage/MainPage.vue';
import ErrorTester from "@/pages/error/ErrorTester.vue";
import PostPage from "@/pages/post/PostPage.vue";
</script>

<!--You can put a page here but it's better to "route" it.-->
<template>
  <main>
    <router-view />
  </main>
</template>

<style scoped>
</style>
