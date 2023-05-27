package cz.osu.java.messboardapp;

import cz.osu.java.messboardapp.Form.AuthForm;
import cz.osu.java.messboardapp.json.UserToken;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthServiceTesting {
    @Mock
    private AppUserRepository userRepository;

    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthService(userRepository);
    }

    @Test
    public void testAuthenticate_WithValidCredentials_ReturnsUserToken() {
        // Příprava
        AuthForm authForm = new AuthForm();
        authForm.setUsername("testUser");
        authForm.setPassword("testPassword");

        BoardUser user = new BoardUser();
        user.setUserId(1);
        user.setUsername("testUser");
        user.setPassword("testPassword");

        when(userRepository.findAppUserByUsername(authForm.getUsername())).thenReturn(user);

        // Získání odpovědi
        ResponseEntity<Object> response = authService.authenticate(authForm);

        // Předpoklady
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(UserToken.class, response.getBody().getClass());

        UserToken userToken = (UserToken) response.getBody();
        assertEquals(user.getUserId(), userToken.getUserId());
        assertEquals(user.getUsername(), userToken.getUsername());
    }

    @Test
    public void testAuthenticate_WithIncorrectPassword_ReturnsBadRequest() {
        // Příprava
        AuthForm authForm = new AuthForm();
        authForm.setUsername("testUser");
        authForm.setPassword("wrongPassword");

        BoardUser user = new BoardUser();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        when(userRepository.findAppUserByUsername(authForm.getUsername())).thenReturn(user);

        // Získání odpovědi
        ResponseEntity<Object> response = authService.authenticate(authForm);

        // Předpoklady
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Incorrect password", response.getBody());
    }

    @Test
    public void testAuthenticate_WithNonExistingUser_ReturnsBadRequest() {
        // Příprava
        AuthForm authForm = new AuthForm();
        authForm.setUsername("nonExistingUser");
        authForm.setPassword("password");

        when(userRepository.findAppUserByUsername(authForm.getUsername())).thenReturn(null);

        // Získání odpovědi
        ResponseEntity<Object> response = authService.authenticate(authForm);

        // Předpoklady
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Incorrect username", response.getBody());
    }
}
