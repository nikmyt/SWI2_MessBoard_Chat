package cz.osu.java.messboardapp.RabbitMQ;

import com.google.gson.Gson;

public class TextMessageDTO {
    //new UNIVERSAL messageForm: destination, timestamp, sender, text, extra
    private String destination;
    private String timestamp;
    private String sender;
    private String text;
    private String extra;
    public TextMessageDTO(){} //why
    public TextMessageDTO(String text){this.text = text;} //debuggy
    public TextMessageDTO(String text, String sender){this.text = text; this.sender = sender;} //debuggy
    public TextMessageDTO(String destination, String timestamp, String sender, String text, String extra) {
        this.destination = destination;
        this.timestamp = timestamp;
        this.sender = sender;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    
}
