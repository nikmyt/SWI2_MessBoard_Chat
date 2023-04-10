package cz.osu.java.messboardapp.json;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserToken
{
    private Integer userId;
    private String username;
}
