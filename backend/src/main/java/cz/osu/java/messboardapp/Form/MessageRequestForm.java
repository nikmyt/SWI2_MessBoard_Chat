package cz.osu.java.messboardapp.Form;

import org.json.JSONObject;

public class MessageRequestForm {

    private String destination;
    private String timestamp;
    private int numberOfMessages;
    public MessageRequestForm(String json) {
        System.out.println("json:" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);

            String destination = jsonObject.getString("destination");
            String timestamp = jsonObject.getString("timestamp");
            int numberOfMessages = jsonObject.getInt("numberOfMessages");

            this.destination = destination;
            this.timestamp = timestamp;
            this.numberOfMessages = numberOfMessages;
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

    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

    public String getDestination() {
        return destination;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getNumberOfMessages() {
        return numberOfMessages;
    }


    @Override
    public String toString() {
        return "MessageForm{" +
                "destination='" + destination + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", number of messages='" + numberOfMessages + '\'' +
                '}';
    }
}
