package cz.osu.java.messboardapp.Form;

import org.json.JSONObject;

public class MessageForm {

	private String destination;
	private String timestamp;
	private String sender;
	private String text;
	private String extra;

	public MessageForm(String json) {
		System.out.println("json:" + json);
		try {
			JSONObject jsonObject = new JSONObject(json);

			String destination = jsonObject.getString("destination");
			String timestamp = jsonObject.getString("timestamp");
			String sender = jsonObject.getString("sender");
			String text = jsonObject.getString("text");
			String extra = jsonObject.getString("extra");

			this.destination = destination;
			this.timestamp = timestamp;
			this.sender = sender;
			this.text = text;
			this.extra = extra;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setExtra(String extra) {
		this.extra = extra;
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

	@Override
	public String toString() {
		return "MessageForm{" +
				"destination='" + destination + '\'' +
				", timestamp='" + timestamp + '\'' +
				", sender='" + sender + '\'' +
				", text='" + text + '\'' +
				", extra='" + extra + '\'' +
				'}';
	}
}
