<template>
  <div>
    <div class="chat-window">
      <div class="chat-messages">
        <ChatMessage
            v-for="(message, index) in messages"
            :key="index"
            :username="message.sender"
            :text="message.text"
        />
      </div>
    </div>
    <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type a message..." />
  </div>
</template>

<script>
import ChatMessage from "./ChatMessage.vue";

export default {
  name: "ChatPage",
  components: {
    ChatMessage, // Register the ChatMessage component
  },
  data() {
    return {
      username: '',
      isLoggedIn: localStorage.getItem("user") !== null,
      messages: [],
      newMessage: '',
    };
  },
  methods: {
    mounted() {
      const user = localStorage.getItem("user");
      if (user) {
        this.username = user;
      } else {
        this.username = 'unlogged'
      }
    },
    sendMessage() {
      if (this.newMessage.trim() !== '') {
        this.messages.push({
          sender: this.username,
          text: this.newMessage,
        });
        this.newMessage = '';
      }
    },
  },
};
</script>

<style scoped>

</style>