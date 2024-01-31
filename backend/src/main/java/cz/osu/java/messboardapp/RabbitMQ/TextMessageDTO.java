package cz.osu.java.messboardapp.RabbitMQ;

import com.google.gson.Gson;

public class TextMessageDTO {
    //new UNIVERSAL messageForm: destination, timestamp, sender, text, extra
    private Long destinationId;
    private String timestamp;
    private Long senderId;
    private String text;
    private String extra;
    public TextMessageDTO(){} //why
    public TextMessageDTO(String text){this.text = text;} //debuggy
    public TextMessageDTO(String text, Long senderId){this.text = text; this.senderId = senderId;} //debuggy
    public TextMessageDTO(Long destinationId, String timestamp, Long senderId, String text, String extra) {
        this.destinationId = destinationId;
        this.timestamp = timestamp;
        this.senderId = senderId;
        this.text = text;
        this.extra = extra;
    }

    public String getText(){
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSender(Long senderId) {
        this.senderId = senderId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String toJson(){
        Gson gson = new Gson();
        String jsonified = gson.toJson(this);
        return jsonified;
    }

    @Override
    public String toString() {
        return "TextMessageDTO{" +
                "destinationId='" + destinationId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", senderId='" + senderId + '\'' +
                ", text='" + text + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
