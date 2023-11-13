<template>
  <div>
    <div class="chat-window">
      <div id="main-content" class="container">
        <div class="row">
          <div class="col-md-6">
            <form class="form-inline">
              <div class="form-group">
                <label for="connect">WebSocket connection:</label>
                <button id="connect" class="btn btn-default" type="submit">Connect</button>
                <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                </button>
              </div>
            </form>
          </div>
          <div class="col-md-6">
            <form class="form-inline">
              <div class="form-group">
                <label for="name">What is your name?</label>
                <input type="text" id="name" class="form-control" placeholder="Your name here...">
              </div>
              <button id="send" class="btn btn-default" type="submit">Send</button>
            </form>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <table id="conversation" class="table table-striped">
              <thead>
              <tr>
                <th>Greetings</th>
              </tr>
              </thead>
              <tbody id="greetings">
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <input v-model="newMessage" @keyup.enter="sendMessage"  placeholder="Type a message..." />
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

      /*
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
      */
      const Stomp = require('stompjs');
      const stompClient = new Stomp.Client({
        brokerURL: 'ws://localhost:8080/ws'
      });
      stompClient.onConnect = (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', (message)=>{
          showMessage(JSON.parse(message.body).content);
        });
      };

      stompClient.onWebSocketError = (error) => {
        console.error('Error with websocket', error);
      };

      stompClient.onStompError = (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
      };

      function setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
          $("#conversation").show();
        }
        else {
          $("#conversation").hide();
        }
        $("#greetings").html("");
      }

      function connect() {
        stompClient.activate();
      }

      function disconnect() {
        stompClient.deactivate();
        setConnected(false);
        console.log("Disconnected");
      }

      function sendName() {
        stompClient.publish({
          destination: "/app/hello",
          body: JSON.stringify({'name': $("#name").val()})
        });
      }

      function showMessage(message) {
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
      }

      $(function () {
        $("form").on('submit', (e) => e.preventDefault());
        $( "#connect" ).click(() => connect());
        $( "#disconnect" ).click(() => disconnect());
        $( "#send" ).click(() => sendName());
      });
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