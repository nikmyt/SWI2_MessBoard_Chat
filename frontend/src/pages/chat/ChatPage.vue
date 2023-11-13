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
      //EventBus.$stomp.subscribe('/user/queue/messages', (message) => {
      //  this.messages.push(message);
      //  console.log('Received message from RabbitMQ:', message.body);
      //});

      const socket = new WebSocket('ws://localhost:8080/ws');

      socket.onopen = () => {
        console.log('WebSocket connection opened');
      };

      socket.onmessage = (event) => {
        const messageContent = event.data;
        console.log('Received message from WebSocket: ' + messageContent);
      };

      socket.onclose = () => {
        console.log('WebSocket connection closed');
      };


    },
    sendMessage() { //message?
      if (this.newMessage.trim() !== '') {
        this.messages.push({
          sender: this.username,
          text: this.newMessage,
        });
        socket.send(this.newMessage);
        /*
        try {
        EventBus.$stomp.publish('/api/message/send', JSON.stringify({
          sender: this.username,
          text: this.newMessage,
        }))} catch (error) {
        console.error('Error publishing message to RabbitMQ:', error);
        this.errorMessage = 'Failed to send message to RabbitMQ. Please try again.';
        this.messages.push(errorMessage);
      }*/
        this.newMessage = '';
      }
    },
  },
};
</script>

<style scoped>

</style>