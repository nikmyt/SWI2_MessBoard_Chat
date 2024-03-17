<template>
  <div>
    <div class="chat-window">
      <div class="container">
        <!-- WebSocket connection form -->
        <div class="row">
          <div class="col-md-6">
            <form class="form-inline" @submit.prevent="connect">
              <div class="form-group">
                <label for="connect">WebSocket connection:</label>
                <button id="connect" class="btn btn-default" :disabled="connected" type="submit">Connect</button>
                <button id="disconnect" class="btn btn-default" :disabled="!connected" @click="disconnect" type="button">
                  Disconnect
                </button>
              </div>
            </form>
          </div>
          <!-- Name input form -->
          <div class="col-md-6">
            <form class="form-inline">
              <div class="form-group">
                <label for="name">What is your name?</label>
                <input v-model="userName" type="text" id="name" class="form-control" placeholder="Your name here..." />
              </div>
              <button class="btn btn-default" @click="sendName" type="button">Send</button>
            </form>
          </div>
        </div>
        <!-- Message table -->
        <div class="row">
          <div class="col-md-12">
            <table class="table table-striped" v-show="connected">
              <thead>
              <tr>
                <th>Greetings</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(message, index) in messages" :key="index">
                <td>{{ message }}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
        <!-- Chat messages -->
        <div class="chat-messages">
          <ChatMessage v-for="(message, index) in messages" :key="index" :username="sender" :message="text" />
        </div>
      </div>
      <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type a message..." />
    </div>
  </div>
</template>

<script>
import ChatMessage from "./ChatMessage.vue";
import Stomp from 'stompjs';

export default {
  name: "ChatPageStomp",
  components: {
    ChatMessage,
  },
  data() {
    return {
      username: '',
      isLoggedIn: localStorage.getItem("user") !== null,
      messages: [],
      newMessage: '',
      connected: false,
      stompClient: null,
    };
  },
  methods: {
    mounted() {
      const user = localStorage.getItem("user");
      if (user) {
        this.username = user;
      } else {
        this.username = 'unlogged';
      }

      const socket = new WebSocket('ws://localhost:8080/ws');
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect({}, frame => {
        this.connected = true;

        this.stompClient.subscribe('/topic/messages', message => {
          this.showMessage(JSON.parse(message.body).content);
        });
      }, error => {
        console.error('Error with WebSocket', error);
      });
    },
    connect() {
      const socket = new WebSocket('ws://localhost:8080/ws/'); //why twice?
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect({}, frame => {
        this.connected = true;

        this.stompClient.subscribe('/topic/messages', message => {
          this.showMessage(JSON.parse(message.body).content);
        });
      }, error => {
        console.error('Error with WebSocket', error);
      });
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    sendName() {
      this.stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({ name: this.userName }),
      });
    },
    sendMessage() {
      if (this.newMessage.trim() !== '') {
        this.messages.push({
          sender: this.username,
          text: this.newMessage,
        });
        // Send message through WebSocket
        this.stompClient.send('/app/send', {}, JSON.stringify({ text: this.newMessage }));
        this.newMessage = '';
      }
    },
    showMessage(message) {
      this.messages.push(message);
    },
  },
};
</script>

<style scoped>
/* Your styles go here */
</style>