package cz.osu.javamessboardapp.service;


import cz.osu.javamessboardapp.model.BoardUser;
import cz.osu.javamessboardapp.Form.RegistrationForm;
import cz.osu.javamessboardapp.repository.AppUserRepository;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService
{
    private final AppUserRepository userRepository;

    public ResponseEntity<String> register(RegistrationForm registrationForm)
    {
        //check if username exists
        //Y -> return that username is taken, http status = bad request
        //N -> create new user and return that user was, registered = OK
        if(userRepository.existsByUsernameIgnoreCase(registrationForm.getUsername()) == true)
        {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        else
        {
            BoardUser user = new BoardUser();
            user.setUsername(registrationForm.getUsername());
            user.setPassword(registrationForm.getPassword());
            userRepository.save(user);
            return new ResponseEntity<>("Account was created", HttpStatus.OK);
        }



    }
}
