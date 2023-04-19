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


    public RegistrationForm(String username, String password, String email)
    {
        BoardUser bUser = new BoardUser();
        bUser.setUserId((int)userRep.count()+1);
        bUser.setUsername(username);
        bUser.setPassword(password);
        bUser.setE_mail(email);
        userRep.save(bUser);
    }
}
