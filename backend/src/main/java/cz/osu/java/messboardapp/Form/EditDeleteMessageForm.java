package cz.osu.java.messboardapp.Form;

import org.json.JSONObject;

public class EditDeleteMessageForm {
    private Long destinationId;
    private String timestamp;
    private Long senderId;
    private String textToEdit;

    public EditDeleteMessageForm(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            String destinationString = jsonObject.getString("destination");
            Long destinationId = Long.parseLong(destinationString);
            String timestamp = jsonObject.getString("timestamp");
            String senderString = jsonObject.getString("sender");
            Long senderId = Long.parseLong(senderString);
            String textToEdit = jsonObject.getString("textToEdit");

            this.destinationId = destinationId;
            this.timestamp = timestamp;
            this.senderId = senderId;
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

    public void setTextToEdit(String textToEdit){
        this.textToEdit = textToEdit;
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

    public String getTextToEdit(){
        return textToEdit;
    }


    @Override
    public String toString() {
        return "MessageForm{" +
                "destinationId='" + destinationId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", senderId='" + senderId + '\'' +
                ", textToEdit='" + textToEdit + '\'' +
                '}';
    }
}
