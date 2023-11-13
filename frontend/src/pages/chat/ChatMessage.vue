<template>
  <div className="chat-bubble">
    <div className="message-username"> {{username}}</div>
    <div className="message-timestamp"></div>
    <div className="message-content"> {{message}} </div>
    <div className="message-reactions">
    </div>
  </div>
</template>

<script>
export default {
  name: "ChatMessage",
  props: {
    username: String,
    message: String,
  },
  data() {
    return {
      /*
      username: '',
      respondingTo: '',
      timestamp: '',
      messages: [], //what is this for?
      message: '',
      reactions: []
      */
    }
  }
}
</script>

<style scoped>
.chat-bubble {
  background-color: #f0f0f0;
  padding: 10px;
  border-radius: 10px;
  margin: 10px;
  max-width: 70%;
  word-wrap: break-word;
}

.message-header {
  font-weight: bold;
  margin-bottom: 5px;
}

.message-timestamp {
  color: #888;
  font-size: 12px;
  margin-bottom: 5px;
}

.message-content {
  margin-bottom: 5px;
}

.message-reactions {
  color: #888;
  font-size: 14px;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
