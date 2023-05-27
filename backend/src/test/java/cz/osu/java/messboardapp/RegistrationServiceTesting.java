package cz.osu.java.messboardapp;

import cz.osu.java.messboardapp.json.UserToken;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.Form.RegistrationForm;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RegistrationServiceTesting {
    @Mock
    private AppUserRepository userRepository;

    private RegistrationService registrationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registrationService = new RegistrationService(userRepository);
    }

    @Test
    public void testRegister_WithUniqueUsername_ReturnsOk() {
        // Příprava
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setUsername("testUser");
        registrationForm.setPassword("testPassword");
        registrationForm.setPassword_hint("hint");
        registrationForm.setEmail("test@test.com");

        when(userRepository.existsByUsernameIgnoreCase(registrationForm.getUsername())).thenReturn(false);

        // Získání odpovědi
        ResponseEntity<String> response = registrationService.register(registrationForm);

        // Předpoklady
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account was created", response.getBody());
    }

    @Test
    public void testRegister_WithExistingUsername_ReturnsBadRequest() {
        // Příprava
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setUsername("existingUser");
        registrationForm.setPassword("testPassword");
        registrationForm.setPassword_hint("hint");
        registrationForm.setEmail("test@test.com");

        when(userRepository.existsByUsernameIgnoreCase(registrationForm.getUsername())).thenReturn(true);

        // Získání odpovědi
        ResponseEntity<String> response = registrationService.register(registrationForm);

        // Předpoklady
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Username already exists", response.getBody());
    }

    @Test
    public void testDelete_WithMatchingUserAndToken_ReturnsOk() {
        // Příprava
        BoardUser user = new BoardUser();
        user.setUserId(1);

        UserToken userToken = new UserToken(1, "testUser");

        // Získání odpovědi
        ResponseEntity<String> response = registrationService.delete(user, userToken);

        // Předpoklady
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account was deleted", response.getBody());
    }

    @Test
    public void testDelete_WithNonMatchingUserAndToken_ReturnsBadRequest() {
        // Příprava
        BoardUser user = new BoardUser();
        user.setUserId(1);

        UserToken userToken = new UserToken(2, "testUser");

        // Získání odpovědi
        ResponseEntity<String> response = registrationService.delete(user, userToken);

        // Předpoklady
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Account couldn't be deleted", response.getBody());
    }
}

