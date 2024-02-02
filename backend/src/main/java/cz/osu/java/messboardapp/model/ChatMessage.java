package cz.osu.java.messboardapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //damn. but this allows us to return n of messages
	private String timestamp;
	private Long destinationId;
	private Long senderId;
	private String text;
	private String extra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Long getDestination() {
		return destinationId;
	}

	public void setDestination(Long destinationId) {
		this.destinationId = destinationId;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSender(Long senderId) {
		this.senderId = senderId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Override
	public String toString() {
		return "MessageForm{" +
				", id='" + id + '\'' +
				", timestamp='" + timestamp + '\'' +
				", destinationId='" + destinationId + '\'' +
				", senderId='" + senderId + '\'' +
				", text='" + text + '\'' +
				", extra='" + extra + '\'' +
				'}';
	}
}
