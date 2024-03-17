package cz.osu.java.messboardapp.Form;

import org.json.JSONObject;

public class MessageForm {

	private Long destinationId;
	private String timestamp;
	private Long senderId;
	private String text;
	private String extra;

	public MessageForm() {
	}

	public MessageForm(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);

			String destinationIdString = jsonObject.getString("destination");
			Long destinationId = Long.parseLong(destinationIdString);
			String timestamp = jsonObject.getString("timestamp");
			String senderIdString = jsonObject.getString("sender");
			Long senderId = Long.parseLong(senderIdString);
			String text = jsonObject.getString("text");
			String extra = jsonObject.getString("extra");

			this.destinationId = destinationId;
			this.timestamp = timestamp;
			this.senderId = senderId;
			this.text = text;
			this.extra = extra;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public Long getDestinationId() {
		return destinationId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public Long getSenderId() {
		return senderId;
	}

	public String getText() {
		return text;
	}

	public String getExtra() {
		return extra;
	}

	@Override
	public String toString() {
		return "MessageForm{" +
				"destinationId='" + destinationId + '\'' +
				", timestamp='" + timestamp + '\'' +
				", senderId='" + senderId + '\'' +
				", text='" + text + '\'' +
				", extra='" + extra + '\'' +
				'}';
	}
}
