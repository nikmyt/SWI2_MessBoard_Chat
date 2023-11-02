<template>
  <div className="chat-bubble">
    <div className="message-header">
      {{ username }} <span v-if="respondingTo">is responding to {{ respondingTo }}</span>
    </div>
    <div className="message-timestamp">{{ timestamp }}</div>
    <div className="message-content">
    </div>
    <div className="message-reactions">
    </div>
    <input v-model="newMessage" placeholder="Type your message..."/>
  </div>
</template>

<script>
export default {
  name: "ChatMessage",
  data() {
    return {
      username: '',
      respondingTo: '',
      timestamp: '',
      messages: [],
      newMessage: '',
      reactions: []
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
