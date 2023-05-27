package cz.osu.java.messboardapp.Form;

import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationForm
{

    private String username;
    private String password;
    private String email;
    private String password_hint;



}
