package cz.osu.java.messboardapp.service;


import cz.osu.java.messboardapp.json.UserToken;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.Form.RegistrationForm;
import cz.osu.java.messboardapp.repository.AppUserRepository;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService
{
    private final AppUserRepository userRepository;

    private final Argon2PasswordEncoder argon2id = new Argon2PasswordEncoder(16, 32, 1, 64000, 10);

    public ResponseEntity<String> register(RegistrationForm registrationForm)
    {
        if(userRepository.existsByUsernameIgnoreCase(registrationForm.getUsername()) == true)
        {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        else
        {
            BoardUser user = new BoardUser();
            user.setUsername(registrationForm.getUsername());
            user.setPassword(argon2id.encode(registrationForm.getPassword()));
            user.setUserId(userRepository.findAll().size()+1);
            user.setPassword_hint(registrationForm.getPassword_hint());
            user.setEmail(registrationForm.getEmail());
            userRepository.save(user);
            return new ResponseEntity<>("Account was created", HttpStatus.OK);
        }
    }
    public ResponseEntity<String> delete(BoardUser bUser, UserToken uToken)
    {
        if(bUser.getUserId() == uToken.getUserId())
        {
            userRepository.delete(bUser);
            return new ResponseEntity<>("Account was deleted", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Account couldn't be deleted", HttpStatus.BAD_REQUEST);
        }
    }
}
