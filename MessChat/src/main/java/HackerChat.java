import jakarta.websocket.*;

import java.net.URI;
import java.util.Scanner;

@ClientEndpoint
public class HackerChat {

	private Session session;

	private static final String WEBSOCKET_SERVER_URI = "ws://localhost:8080/ws";

	public static void main(String[] args) {
		displayWelcomeScreen();

		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			HackerChat hackerChat = new HackerChat();
			container.connectToServer(hackerChat, URI.create(WEBSOCKET_SERVER_URI));

			hackerChat.startChat();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void displayWelcomeScreen() {
		// ASCII art welcome screen
		System.out.println("HACKER MESSCHAT");
		// Add your ASCII art here
		System.out.println();
	}

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		System.out.println("Connected to WebSocket Server");
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println("Received message: " + message);
		// Process the received message
	}

	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}

	private void startChat() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.print("Enter command: ");
				String userInput = scanner.nextLine();



				if ("/exit".equalsIgnoreCase(userInput)) {
					session.close();
					break;
				}

				session.getBasicRemote().sendText(userInput);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
