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
                <td>{{ message.text }}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
        <!-- Chat messages -->
        <div class="chat-messages">
          <!-- Display chat messages here -->
        </div>
      </div>
      <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type a message..." />
    </div>
  </div>
</template>

<script>
import ChatMessage from "./ChatMessage.vue";
import SockJS from 'sockjs-client';

export default {
  name: "ChatPageSockJS",
  components: {
    ChatMessage,
  },
  data() {
    return {
      userName: '',
      isLoggedIn: localStorage.getItem("user") !== null,
      messages: [],
      newMessage: '',
      connected: false,
      sockJS: null,
    };
  },
  methods: {
    mounted() {
      const user = localStorage.getItem("user");
      this.userName = user || 'unlogged';
    },
    connect() {
      this.sockJS = new SockJS('http://localhost:8080/ws');

      this.sockJS.onopen = () => {
        this.connected = true;
        console.log('Connected');

        this.sockJS.onmessage = (message) => {
          const receivedMessage = JSON.parse(message.data);
          this.messages.push(receivedMessage);
        };
      };

      this.sockJS.onerror = (error) => {
        console.error('Error with WebSocket', error);
      };
    },
    disconnect() {
      if (this.sockJS) {
        this.sockJS.close();
      }
      this.connected = false;
      console.log('Disconnected');
    },
    sendName() {
      const message = {
        type: 'hello',
        name: this.userName,
      };
      this.sockJS.send(JSON.stringify(message));
    },
    sendMessage() {
      if (this.newMessage.trim() !== '') {
        const message = {
          type: 'text',
          sender: this.userName,
          text: this.newMessage,
        };
        this.messages.push(message);
        this.sockJS.send(JSON.stringify(message));
        this.newMessage = '';
      }
    },
  },
};
</script>