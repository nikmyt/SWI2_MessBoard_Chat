<script setup>
import ChatMessage from "./ChatMessage.vue";
import SockJS from 'sockjs-client';
import {ApiClient} from "@/client/ApiClient";
import Stomp from "stompjs";
import TopBar from "@/pages/pageElements/TopBar.vue";
import LeftMenu from "@/pages/pageElements/LeftMenu.vue";
import RightMenu from "@/pages/pageElements/RightMenu.vue";
import Footer from "@/pages/pageElements/Footer.vue";
</script>

<template>
  <div>
      <header>
        <TopBar />
      </header>

      <div class="container">
        <LeftMenu />

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
                <input v-model="userName" type="text" id="name" class="form-control" placeholder="Your name here..." />
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

export default {
  name: "ChatPageSockJS",
  components: {
    RightMenu,
    LeftMenu,
    TopBar,
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
        console.log('Connected:', frame);

        //topic-exchange routing-key-1 / 2
        //yeha it works now. ok. i just needed to stop being stupid
        this.stompClient.subscribe('/topic/globalChat', (message) => { //topic/messages
          const receivedMessage = JSON.parse(message.body).content;
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

    },
    disconnect() {
      if (this.sockJS) {
        this.sockJS.close();
      }
      this.connected = false;
      console.log('Disconnected');
    },
    //what is this for?
    /*
    sendName() {
      const message = {
        type: 'hello',
        name: this.userName,
      };
      this.sockJS.send(JSON.stringify(message));
    },
    */
    sendMessage() {
      //do not need websocket here YET just send
      if (this.newMessage.trim() !== '') {
        var message = {
          type: 'text',
          sender: this.userName,
          text: this.newMessage,
        };

        message = this.newMessage;
        //this.messages.push(message);
        ApiClient.sendMessage(message);

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