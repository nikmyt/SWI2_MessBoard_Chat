<template>
  <div>
      <header>
        <TopBar />
      </header>

      <div class="container">
        <aside class="sidebar-left">
          <h2>Select chatroom</h2>
          <button @click="changeChatroom('globalChat')">🪐 Global chat</button>
          <div>
            <button v-for="room in rooms" :key="room.destinationId" @click="changeChatroom(room.destinationId)">
              {{ room.name }}
            </button>
          </div>
          <!-- TODONE?: load and create more buttons for rooms where user is joined in -->
          <button @click="creatingChatroom = true">➕ Create new chatroom</button>
        </aside>

        <div v-if="creatingChatroom" class="chatroomPopup">
          <input v-model="newChatroomName" @keyup.enter="createRoom" placeholder="Name your chatroom..." />
          <button @click="createRoom">➕ Create</button>
        </div>

        <div class="chat-window">
      <div class="container">
        <!-- message container - where chat messages go -->
        <div id="chatcontainer" class="">
        </div>
        <div class="chat-messages">
          <div v-for="(message, index) in messages" :key="index" class="chat-message">
            <p class="message-header">{{ message.sender }} - {{ formatDate(message.timestamp) }}</p>
            <p class="message-text">{{ message.text }}</p>
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
import {MessageRequestForm} from "@/form/MessageRequestForm";

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
      userID: '',
      isLoggedIn: localStorage.getItem("user") !== null,
      messages: [],
      newMessage: '',
      connected: false,
      sockJS: null,
      selectedChatroom: '1',
      newChatroomName: '',
      creatingChatroom: false,
      rooms: [],
    };
  },
  mounted() {
    const user = localStorage.getItem("user");
    if (user) {
      this.username = user;
      this.userID = localStorage.getItem("token");
    } else {
      this.username = null
    }
    this.connectOnLoad();
    //console.log("what is a token:" + localStorage.getItem("token")); //a token is userId
    this.fetchMessages(); //TODO: add args here? YES, because you need to expand on fetchMessages to do more.
    this.fetchUserRooms();
  },
  methods: {
    connectOnLoad() {
      //make it so it automatically tries to reconnect when connected = false
      setTimeout(() => {this.connect();}, 1000);
      },
    connect() {
      this.sockJS = new SockJS('http://localhost:8080/ws');

      this.sockJS.onopen = () => {
        this.connected = true;
        console.log('Connected');

        this.sockJS.onmessage = (message) => {
          const receivedMessage = JSON.parse(message.data);
          this.messages.push(receivedMessage);
          console.log("got a message:" + JSON.parse(message.body).content);
        };
      };

      this.sockJS.onerror = (error) => {
        console.error('Error with WebSocket', error);
      };

      const socket = new WebSocket('ws://localhost:8080/ws');
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect({}, (frame) => {
        this.connected = true;

        //TODONE?: 11.1 make sure to subscribe to where user is (replace destination)
        this.stompClient.subscribe("/topic/" + this.selectedChatroom, (message) => {
          console.log("Got a message from Rabbit: " + message.body);
          //const trimmed = message.body.trim();
          const rawContent = JSON.parse(message.body.toString());
          //console.log("destination: " + rawContent.destination);
          const receivedMessage = {
            destination: rawContent.destination, //unused since subscribe is filtering it already
            timestamp: rawContent.timestamp,
            sender: rawContent.sender,
            text: rawContent.text,
            extra: rawContent.extra
          };

          this.messages.push(receivedMessage);
          this.showMessage(receivedMessage);
        });
      }, error => {
        console.error('Error with WebSocket', error);
      });
    },
    showMessage() {
      //TODO: move show message logic here.
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
          destination: "/topic/" + this.selectedChatroom, //TODONE?: change to correct destination, fix username
          timestamp: Date.now().toString(),
          sender: this.username,
          text: this.newMessage,
          extra: "",
        };

        console.log(JSON.stringify(message));

        ApiClient.sendMessage(JSON.stringify(message));

        /*
        var something = {method: 'POST',headers: {'Content-Type': 'application/json',},body: JSON.stringify(message)}
        console.log(something); //well this is what i sent. not useful
        //this.sockJS.send(JSON.stringify(message));
        */

        this.newMessage = '';
      }
    },
    async fetchMessages() {
      try {
        const messageRequestForm = new MessageRequestForm();
        messageRequestForm.destination = "/topic/" + this.selectedChatroom; //TODONE?: change to automatically go to where room button clicked
        messageRequestForm.timestamp = Date.now().toString(); //before? i think so. BE says: findByDestinationAnd TimestampLessThan OrderByTimestampDesc
        messageRequestForm.numberOfMessages = 256; //variable somehow

        const messages = await ApiClient.getMessages(messageRequestForm);

        this.messages = messages;
      } catch (error) {
        console.error('Error fetching messages:', error);
      }
    },
    clearMessages(){
      //call before showing new messages? or...
      this.messages = [];
    },
    fetchUserRooms(){
      this.rooms = ApiClient.getUserRooms(this.userID); //yes?
      //wait what does this do
      //right, get a v-for up there in the buttons and link up logic to pretty buttons
    },
    joinRoom(roomID){
      //where tf do i get room id? oh from the rooms[].id
    },
    createRoom() {
      //check if such named thing exists...? naw, it's ok, it uses id's
      //MAKE SURE TO SHOW USER THE CREATED CHAT'S ID?... or not, they're automatically added.
      //1) saveRoom - yes, id is destination sender. destination is name
      if (this.newChatroomName.trim() !== '') {
        const room = {
          destination: this.newChatroomName.trim(), // Use the trimmed chatroom name as the destination
          userID: this.userID,
        };

        // Show success/failure to the user here
        ApiClient.saveRoom(room);

        // Close the popup after creating the chatroom
        this.creatingChatroom = false;
        this.newChatroomName = ''; // Clear the input field
        this.fetchUserRooms(); //get the roomses
      }
    },
    addUserToRoom(useroni){
      //only available if you're the creator? or... anyone? how do we make sure not to have dupli adds?
      //2?) AddUserToRoom(joinDestinatioform) - also make sure to do this when user makes a room

    },
    formatDate(timestamp) {
      const date = new Date(parseInt(timestamp));
      return date.toLocaleString();
    },
  },
  watch: {
    messages: {
      handler(messages) {
        //TODO: i changed it, check you're not losing messages
        const maxMessages = 256; //Set the maximum number of messages
        if (messages.length > maxMessages) {
          //Remove the oldest messages to keep the array within the limit
          this.messages = messages.slice(-maxMessages);
        }
      },
      deep: true,
    },
  },
};
</script>

<style scoped>
.chat-messages {
  max-width: 600px;
  max-height: 400px;
  overflow-y: auto;
  margin: 0 auto;
}

.chat-message {
  margin-bottom: 10px;
  padding: 10px;
  background-color: #f0f0f0;
  border-radius: 8px;
}

.message-header {
  font-weight: bold;
  margin-bottom: 5px;
}

.message-text {
  margin: 0;
}

.chatroomPopup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

</style>