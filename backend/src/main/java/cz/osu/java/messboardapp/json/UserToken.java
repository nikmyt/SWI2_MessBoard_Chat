package cz.osu.java.messboardapp.json;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserToken
{
    private Integer userId;
    private String username;

    @Override
    public String toString() {
        return "MessageForm{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
