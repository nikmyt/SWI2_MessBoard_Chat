<template>
  <div>
      <header>
        <TopBar />
      </header>

      <div class="container">
        <!-- add a chatroom display, with "New room" at the bootom and "Global chat" on top -->
        <aside class="sidebar-left">
          <h2>this is chatroom display</h2>
          <button @click="changeChatroom">🪐 Global chat</button>
          <button @click="createNewChatroom">➕ Create new chatroom</button>
          <!-- frick i just realized i need to have people joinery thingy somehow. whereeeee -->
        </aside>

        <div class="chat-window">
      <div class="container">
        <!-- message container - where chat messages go -->
        <div id="chatcontainer" class="">

        </div>

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

          <!--
          <div class="col-md-6">
            <form class="form-inline">
              <div class="form-group">
                <label for="name">What is your name?</label>
                <input v-model="username" type="text" id="name" class="form-control" placeholder="Your name here..." />
              </div>
              <button class="btn btn-default" @click="sendMessage" type="button">Send</button>
            </form>
          </div>
          -->

        </div>

        <div class="chat-messages">
          <div v-for="(message) in messages">
            <p>{{ message }}</p>
          </div>

        </div>
      </div>
      <input v-model="newMessage" @keyup.enter="sendMessage" placeholder="Type a message..." />
    </div>
    <RightMenu />
  </div>

  <Footer />
  </div>
</template>

<script>

import RightMenu from "@/pages/pageElements/RightMenu.vue";
import LeftMenu from "@/pages/pageElements/LeftMenu.vue";
import TopBar from "@/pages/pageElements/TopBar.vue";
import ChatMessage from "@/pages/chat/ChatMessage.vue";
import Footer from "@/pages/pageElements/Footer.vue";
import SockJS from 'sockjs-client';
import {ApiClient} from "@/client/ApiClient";

export default {
  name: "ChatPageSockJS",
  components: {
    RightMenu,
    LeftMenu,
    TopBar,
    ChatMessage,
    Footer,
  },
  data() {
    return {
      username: '',
      isLoggedIn: localStorage.getItem("user") !== null,
      messages: [],
      newMessage: '',
      connected: false,
      sockJS: null,
      //selectedChatroom: '/topic/globalChat',
      //selectedChatroom: 'globalChat',
    };
  },
  methods: {
    mounted() {
      const user = localStorage.getItem("user");
      if (user) {
        this.username = user;
      } else {
        this.username = null
      }
    },
    connect() {
      this.sockJS = new SockJS('http://localhost:8080/ws');
      //const socket = new WebSocket(this.sockJS);

      this.sockJS.onopen = () => {
        this.connected = true;
        console.log('Connected'); //what? here??? nothing here

        this.sockJS.onmessage = (message) => {
          const receivedMessage = JSON.parse(message.data);
          this.messages.push(receivedMessage);
          console.log("got a message:" + JSON.parse(message.body).content);
        };
      };

      this.sockJS.onerror = (error) => {
        console.error('Error with WebSocket', error);
      };

      //STOMP
      const socket = new WebSocket('ws://localhost:8080/ws');
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect({}, (frame) => {
        this.connected = true;

        //TODO: 11.1 make sure to subscribe to where user is (replace
        this.stompClient.subscribe("/topic/globalChat", (message) => {
          //its being sent in Base64
          console.log("Got a message from Rabbit: " + message.body);
          //const trimmed = message.body.trim();
          const rawContent = JSON.parse(message.body.toString());
          console.log("destin: " + rawContent.destination);
          const receivedMessage = {
            destination: rawContent.destination, //this would be unused since subscribe is filtering it already
            timestamp: rawContent.timestamp,
            sender: rawContent.sender,
            text: rawContent.text,
            extra: rawContent.extra
          };

          this.messages.push(receivedMessage);
          this.showMessage(receivedMessage);
          //console.log('Received a message via Stomp:', receivedMessage);
          //so it works, great. now you have to retrofit it so it shows the username, timestamp maybe
        });
      }, error => {
        console.error('Error with WebSocket', error);
      });
    },
    showMessage() {
      //uhh yeah
      console.log("showeming message");
      console.log(this.username);
      console.log(this.isLoggedIn);

    },
    disconnect() {
      if (this.sockJS) {
        this.sockJS.close();
      }
      this.connected = false;
      console.log('Disconnected');
    },
    sendMessage() {
      if (this.newMessage.trim() !== '') {
        var message = {
          //destination, timestamp, sender, text, extra
          destination: "/topic/globalChat", //TODO: change to correct destination, fix username
          timestamp: Date.now().toString(),
          sender: "weenus1", //this.username,
          text: this.newMessage, //like why does this work and username doesnt. this is unfair
          extra: "",
        };

        //have to solve the above so it doesn't resolve as NULL
        //message = this.newMessage; //this is well recieved

        console.log(JSON.stringify(message)); //i have to unstinfiy it now at reception.

        ApiClient.sendMessage(JSON.stringify(message)); //stringify?

        /*
        var something = {method: 'POST',headers: {'Content-Type': 'application/json',},body: JSON.stringify(message)}
        console.log(something); //well this is what i sent. not useful
        //this.sockJS.send(JSON.stringify(message));
        */

        this.newMessage = '';
      }
    },
  },
};
</script>