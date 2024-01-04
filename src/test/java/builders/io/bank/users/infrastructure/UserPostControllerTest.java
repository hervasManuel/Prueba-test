package builders.io.bank.users.infrastructure;

import builders.io.bank.users.application.UserExistException;
import builders.io.bank.users.application.UserRegister;
import builders.io.bank.users.domain.AuthUsername;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserPostControllerTest {
    @Mock
    private UserRegister userRegister;
    @InjectMocks
    private UserPostController instance;
    RequestNewUser requestNewUser;

    @BeforeEach
    void setUp() {
        requestNewUser = new RequestNewUser();
        requestNewUser.setUsername("nameMock");
    }

    @Test
    void newUserSuccess() {
        AuthUsername authUsername = new AuthUsername("nameMock");
        try (MockedStatic<AuthUsername> authUsernameMock = mockStatic(AuthUsername.class)) {
            authUsernameMock.when(() -> AuthUsername.create("nameMock"))
                    .thenReturn(authUsername);
        }
        ResponseEntity result = instance.newUser(requestNewUser);
        verify(userRegister).register(authUsername);
        assertEquals(result, new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Test
    void newUserWhenUserExist_shouldReturnUserExistException() {
        AuthUsername authUsername = new AuthUsername("nameMock");
        try (MockedStatic<AuthUsername> authUsernameMock = mockStatic(AuthUsername.class)) {
            authUsernameMock.when(() -> AuthUsername.create("nameMock"))
                    .thenReturn(authUsername);
        }
        doThrow(UserExistException.class).when(userRegister).register(authUsername);
        ResponseEntity result = instance.newUser(requestNewUser);
        assertEquals(result.getStatusCode(), HttpStatus.CONFLICT);
    }
}