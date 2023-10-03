package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.Form.AuthForm;
import cz.osu.java.messboardapp.json.UserToken;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Service
@AllArgsConstructor
public class AuthService
{
    private final AppUserRepository userRepository;

    private final Argon2PasswordEncoder argon2id = new Argon2PasswordEncoder(16, 32, 1, 64000, 10);

    public ResponseEntity<Object> authenticate(AuthForm authForm)
    {
        BoardUser user = userRepository.findAppUserByUsername(authForm.getUsername());
        if (user != null)
        {
            if(argon2id.matches(authForm.getPassword(), user.getPassword()))
            {
                UserToken userToken = new UserToken(user.getUserId(), user.getUsername());
                return new ResponseEntity<>(userToken, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
            }
        }
        else
        {
            return new ResponseEntity<>("Incorrect username", HttpStatus.BAD_REQUEST);
        }
    }
}