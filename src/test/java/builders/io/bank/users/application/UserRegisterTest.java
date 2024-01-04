package builders.io.bank.users.application;

import builders.io.bank.shared.domain.UuidGenerator;
import builders.io.bank.users.domain.AuthUsername;
import builders.io.bank.users.domain.AuthUsernameMother;
import builders.io.bank.users.domain.User;
import builders.io.bank.shared.domain.UserId;
import builders.io.bank.users.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.bus.EventBus;

import java.util.Optional;

import static builders.io.bank.shared.domain.UUIDMother.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRegisterTest {
    @Mock
    private UserRepository repository;

    @Mock
    private EventBus eventBus;
    @Mock
    private UuidGenerator uuidGenerator;
    @InjectMocks
    private UserRegister instance;


    @BeforeEach
    void setUp() {
    }

    @Test
    void registerSuccess() {
        AuthUsername username = AuthUsernameMother.random();
        UserId id = new UserId(UUID);
        User user = new User(id,username);
        when(repository.findByUsername(username)).thenReturn(Optional.empty());
        when(uuidGenerator.generate()).thenReturn(UUID);
        try (MockedStatic<UserId> userIdMockedStatic = mockStatic(UserId.class)) {
            userIdMockedStatic.when(() -> UserId.create(UUID))
                    .thenReturn(id);
        }
        try (MockedStatic<User> userMock = mockStatic(User.class)) {
            userMock.when(() -> User.create(id,username))
                    .thenReturn(user);
        }
        instance.register(username);
        verify(repository).save(user);
    }

    @Test
    void registerWhenExistUsername_shouldReturnUserExistException() {
        AuthUsername username = AuthUsernameMother.random();
        User user = mock(User.class);
        when(repository.findByUsername(username)).thenReturn(Optional.of(user));
        assertThrows(UserExistException.class, () -> instance.register(username));
    }
}