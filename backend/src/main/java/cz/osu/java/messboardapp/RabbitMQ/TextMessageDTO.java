package cz.osu.java.messboardapp.RabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;


public class TextMessageDTO implements Serializable {
    //new UNIVERSAL messageForm: destination, timestamp, sender, text, extra
    private Long Id;
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public static String toJson(TextMessageDTO textMessageDTO){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String jsonified = gson.toJson(textMessageDTO.getText());
        return jsonified;
    }

    public static TextMessageDTO fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, TextMessageDTO.class);
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
