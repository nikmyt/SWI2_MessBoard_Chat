<template>
  <div>
    <button @click="simulateError">Simulate Error</button>
  </div>
  <error v-if="showError" :error-message="errorMessage" />
</template>

<script>

import error from "./error.vue"

export default {
  name: "ErrorTester",
  components: {
    error,
  },
  data() {
    return {
      showError: false,
      errorMessage: "",
    };
  },
  methods: {
    async simulateError() {
      try {
        // code that throws an error
      } catch (error) {
        this.showError = true;
        this.errorMessage = "Something went wrong. Please try again later.";
      }
    },
  },
};
</script>
