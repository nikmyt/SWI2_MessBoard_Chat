import React, { useState, useEffect } from 'react';
import { ApiClient } from '../client/ApiClient';
import {MessageForm} from "../form/MessageForm";
import SockJsClient from "react-stomp";

//what the f|UCK is this? tsx
//help me im scared

//antidote
/*
import React, { useState } from 'react';
import SockJsClient from 'react-stomp';

const SOCKET_URL = 'http://localhost:8080/ws';

const Page = () => {
  const [message, setMessage] = useState('You server message here.');

  let onConnected = () => {
    console.log("Connected!!")
  }

  let onMessageReceived = (msg) => {
    setMessage(msg.message);
  }

  return (
      <div>
        <SockJsClient
            url={SOCKET_URL}
            topics={['/topic/message']}
            onConnect={onConnected}
            onDisconnect={console.log("Disconnected!")}
            onMessage={msg => onMessageReceived(msg)}
            debug={false}
        />
        <div>{message}</div>
      </div>
  );
}

export default Page;
 */

const ChatPage = () => {
    const [username, setUsername] = useState('');
    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('user') !== null);
    const [messages, setMessages] = useState<MessageForm[]>([]);
    const [newMessage, setNewMessage] = useState('');
    const [connected, setConnected] = useState(false);
    const [selectedChatroom, setSelectedChatroom] = useState('globalChat');

    useEffect(() => {
        const user = localStorage.getItem("user") ?? null;
        setUsername(user || "missing username");
        connectOnLoad();

        return () => {
            // Cleanup logic, if needed
            disconnect();
        };
    }, []); // Empty dependency array ensures the effect runs only once on mount

    const connectOnLoad = () => {
        setTimeout(() => connect(), 1000);
    };

    const connect = () => {
        // Simulating SockJS and Stomp logic for simplicity
        // Adjust the actual WebSocket logic as needed
        const fakeWebSocket = new WebSocket('ws://localhost:8080/ws');

        fakeWebSocket.onopen = () => {
            setConnected(true);
            console.log('Connected');

            fakeWebSocket.onmessage = (message) => {
                const receivedMessage = JSON.parse(message.data);
                //what the FUCK is this supposed to be
                setMessages((prevMessages) => [...prevMessages, receivedMessage]);
                console.log('got a message:', receivedMessage);
            };
        };

        fakeWebSocket.onerror = (error) => {
            console.error('Error with WebSocket', error);
        };

        //yeah chat, NO. TODO unfuck this cumsock
        //oh hey that was easy.. suspiciously so
        SockJsClient.subscribe('/topic/' + selectedChatroom, (message : any) => {
            console.log('Got a message from Rabbit:', message.body);
            const rawContent = JSON.parse(message.body.toString());
            const receivedMessage = {
                destination: rawContent.destination,
                timestamp: rawContent.timestamp,
                sender: rawContent.sender,
                text: rawContent.text,
                extra: rawContent.extra,
            };
            //again WTF?
            setMessages((prevMessages) => [...prevMessages, receivedMessage]);
            showMessage(receivedMessage);
        });
    };

    const showMessage = (message : any) => { //not sure it's a messageform, its a string no?
        // TODO: Move show message logic here.
    };

    const disconnect = () => {
        // Simulating WebSocket close logic
        setConnected(false);
        console.log('Disconnected');
    };

    const sendMessage = () => {
        if (newMessage.trim() !== '') {
            const message = {
                destination: '/topic/globalChat', // TODO: Change to the correct destination
                timestamp: Date.now().toString(),
                sender: username,
                text: newMessage,
                extra: '',
            };

            console.log(JSON.stringify(message));

            ApiClient.sendMessage(message); //TIL the stringify was pointless... oh no. TODO check this sweet insect
            setNewMessage('');
        }
    };

    const formatDate = (timestamp : string) => { //timestamp is a string?
        const date = new Date(parseInt(timestamp));
        return date.toLocaleString();
    };

    return (
        <div>
            {/* Your HTML/JSX structure goes here */}
            <div className="chat-messages">
                {messages.map((message, index) => (
                    <div key={index} className="chat-message">
                        <p className="message-header">{`${message.sender} - ${formatDate(message.timestamp)}`}</p>
                        <p className="message-text">{message.text}</p>
                    </div>
                ))}
            </div>
            <input
                value={newMessage}
                onChange={(e) => setNewMessage(e.target.value)}
                onKeyUp={(e) => e.key === 'Enter' && sendMessage()}
                placeholder="Type a message..."
            />
        </div>
    );
};

export default ChatPage;