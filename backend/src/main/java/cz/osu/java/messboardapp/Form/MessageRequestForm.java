package cz.osu.java.messboardapp.Form;

import org.json.JSONObject;

public class MessageRequestForm {

    private Long destinationId;
    private String timestamp;
    private int numberOfMessages;

    public MessageRequestForm() {
    }

    public MessageRequestForm(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            String destinationIdString = jsonObject.getString("destination");
            Long destinationId = Long.parseLong(destinationIdString);
            String timestamp = jsonObject.getString("timestamp");
            int numberOfMessages = jsonObject.getInt("numberOfMessages");

            this.destinationId = destinationId;
            this.timestamp = timestamp;
            this.numberOfMessages = numberOfMessages;
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

    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

    public Long getDestinationId() {
        return destinationId;
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
                "destinationId='" + destinationId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", number of messages='" + numberOfMessages + '\'' +
                '}';
    }
}
