package com.example.messboard.Service;

import com.example.messboard.Form.AuthForm;
import com.example.messboard.Model.db.BoardUser;
import com.example.messboard.Model.json.UserToken;
import com.example.messboard.Model.repo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService
{
    private final AppUserRepository userRepository;

    public ResponseEntity<Object> authenticate(AuthForm authForm)
    {
        BoardUser user = userRepository.findAppUserByUsername(authForm.getUsername());
        if (user != null)
        {
            if(user.getPassword().equals(authForm.getPassword()))
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
