package cz.osu.java.messboardapp.Form;

import org.json.JSONObject;

public class DestinationForm {
    private String destination;
    private Long userID;
    public DestinationForm(String json) {
        System.out.println("json:" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);

            String destination = jsonObject.getString("destination");
            String userIDstring = jsonObject.getString("userID");
            Long userID = Long.parseLong(userIDstring);

            this.destination = destination;
            this.userID = userID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getDestination() {
        return destination;
    }

    public Long getUserID() {
        return userID;
    }



    @Override
    public String toString() {
        return "MessageForm{" +
                "destination='" + destination + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
