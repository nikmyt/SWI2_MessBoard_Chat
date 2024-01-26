package cz.osu.java.messboardapp.Form;

import org.json.JSONObject;

public class JoinDestinationForm {
    private Long destinationId;
    private Long userID;
    public JoinDestinationForm(String json) {
        System.out.println("json:" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);

            String destinationString = jsonObject.getString("destination");
            Long destinationId = Long.parseLong(destinationString);
            String userIDstring = jsonObject.getString("userID");
            Long userID = Long.parseLong(userIDstring);

            this.destinationId = destinationId;
            this.userID = userID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Long getDestinationId() {
        return destinationId;
    }

    public Long getUserID() {
        return userID;
    }


    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "MessageForm{" +
                "destinationId='" + destinationId + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
