<template>
  <div class="error-page">
    <div class="error-page-content">
      <img src="../../assets/piggers.svg" alt="Error" />
      <h1>Oopsie woopsie! Something went wrong.</h1>
      <p>{{ errorMessage }}</p>
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
}

.error-page-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.error-page img {
  max-width: 100%;
  height: auto;
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