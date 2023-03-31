package cz.osu.javamessboardapp.controller;


import cz.osu.javamessboardapp.service.AuthService;
import cz.osu.javamessboardapp.service.RegistrationService;

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

    @GetMapping("/")
    public String index() {
        return "<html><head><style>" +
                "body { display: flex; justify-content: center; align-items: center; height: 100%; }" +
                "h1 { text-align: center; color: blue; font-family: Comic Sans MS, cursive, sans-serif; }" +
                "</style></head><body>" +
                "<h1>MessBoard, for times when you are just bored</h1>" +
                "</body></html>";
    }

}
