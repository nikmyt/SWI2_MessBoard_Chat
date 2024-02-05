import jakarta.websocket.*;

import java.net.URI;

@ClientEndpoint
public class WebSocketClient {

	private Session session;

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		System.out.println("Connected to WebSocket Server");
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println("Received message: " + message);
		// Process the message as needed
	}

	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}

	public static void main(String[] args) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			String uri = "ws://localhost:8080/ws";
			container.connectToServer(WebSocketClient.class, URI.create(uri));

			// You can send messages after connecting
			// For simplicity, let's assume you have a method sendMessage(String message) in your class
			// Call this method when you want to send a message
			// e.g., client.sendMessage("Hello, server!");

			// Keep the program running or use some event-driven mechanism
			// to maintain the WebSocket connection.

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
