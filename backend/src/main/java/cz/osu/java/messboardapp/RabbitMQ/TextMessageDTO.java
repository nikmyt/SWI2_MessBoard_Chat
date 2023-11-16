package cz.osu.java.messboardapp.RabbitMQ;

public class TextMessageDTO {
    public TextMessageDTO(){}
    public TextMessageDTO(String message){this.message = message;}
    private String message;

    public String getMessage(){
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
}
