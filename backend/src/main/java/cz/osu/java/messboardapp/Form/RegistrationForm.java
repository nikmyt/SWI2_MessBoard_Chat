package cz.osu.java.messboardapp.Form;

import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import lombok.Getter;

@Getter
public class RegistrationForm
{
    private AppUserRepository userRep;
    private String username;
    private String password;
    private String email;
    private String password_hint;


    public RegistrationForm(String username, String password, String email, String password_hint)
    {
        BoardUser bUser = new BoardUser();
        bUser.setUserId((int)userRep.count()+1);
        bUser.setUsername(username);
        bUser.setPassword(password);
        bUser.setE_mail(email);
        //bUser.setPassword_hint
        userRep.save(bUser);
    }
}
