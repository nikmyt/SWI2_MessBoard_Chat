package cz.osu.java.messboardapp.Form;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageForm {

	private String destination;
	private String timestamp;
	private String sender;
	private String text;
	private String extra;

	public MessageForm(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			MessageForm messageForm = objectMapper.readValue(json, MessageForm.class);
			this.destination = messageForm.getDestination();
			this.timestamp = messageForm.getTimestamp();
			this.sender = messageForm.getSender();
			this.text = messageForm.getText();
			this.extra = messageForm.getExtra();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDestination() {
		return destination;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getSender() {
		return sender;
	}

	public String getText() {
		return text;
	}

	public String getExtra() {
		return extra;
	}
}
