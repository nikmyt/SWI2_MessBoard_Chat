package com.example.messboard.Controller;


import com.example.messboard.Service.AuthService;
import com.example.messboard.Service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController
{
   // private final AppUserRepository userRepository;
    private final RegistrationService registrationService;
    private final AuthService authService;
    public MainController(RegistrationService regservice, AuthService authservice) {
        this.registrationService = regservice;
        this.authService = authservice;
    }

}
