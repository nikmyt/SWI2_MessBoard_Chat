<template>
  <link rel="stylesheet" href="src/assets/mainpage.css">

  <div class="error-page">
    <div class="error-page-content">
      <router-link :to="{ name: 'MainPage' }" class="logo-link">
        <div class="logo">
          <img src="../../assets/piggers.svg" alt="Error boar!">
          <!--TODONE make it clickable to get back to main page and everywhere-->
        </div>
      </router-link>
      <h1>Oopsie woopsie! Something went wrong.</h1>
      <p>Click the boar to return to main page!</p>
      <!--<p>{{ errorMessage }}</p>-->
    </div>
  </div>
</template>

<script>
export default {
  //generic error page
  name: "error",
  props: {
    errorMessage: {
      type: String,
      required: true,
    },
  },
};
</script>

<style scoped>
.error-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #5d536c;
}

.error-page-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.error-page img {
  width: auto;
  height: 100%;
  margin-bottom: 20px;
}

.error-page h1 {
  font-size: 36px;
  font-weight: bold;
  margin-bottom: 10px;
}

.error-page p {
  font-size: 18px;
  line-height: 1.5;
}
</style>