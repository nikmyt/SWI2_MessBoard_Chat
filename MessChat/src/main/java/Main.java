import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	private static final String RABBITMQ_SERVER = "localhost"; //ws://localhost:8080/ws ?
	private static final int RABBITMQ_PORT = 5672; //used?

	//you have some rabbit on the other java project, get it in here.

	public static void main(String[] args) {
		displayWelcomeScreen();

		try (Socket socket = new Socket(RABBITMQ_SERVER, RABBITMQ_PORT);
			 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 Scanner scanner = new Scanner(System.in)) {

			String userInput;
			while (true) {
				System.out.print("Enter command: ");
				userInput = scanner.nextLine();

				if ("/exit".equalsIgnoreCase(userInput)) {
					break;
				}

				out.println(userInput);
				String response = in.readLine();
				System.out.println("Server response: " + response);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void displayWelcomeScreen() {
		// ASCII art welcome screen
		System.out.println("HACKER MESSCHAT");
		// Add your ASCII art here
		System.out.println();
	}
}