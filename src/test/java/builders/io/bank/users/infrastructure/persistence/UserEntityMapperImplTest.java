package builders.io.bank.users.infrastructure.persistence;

import builders.io.bank.shared.domain.UserId;
import builders.io.bank.users.domain.AuthUsername;
import builders.io.bank.users.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static builders.io.bank.shared.domain.UUIDMother.UUID;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserEntityMapperImplTest {
    @InjectMocks
    private UserEntityMapperImpl instance;

    @Test
    void userEntityToUser() {
        UserEntity userEntityMock = new UserEntity(UUID, "userName");
        User result = instance.userEntityToUser(userEntityMock);
        assertEquals(result.id(), new UserId(UUID));
        assertEquals(result.username(), new AuthUsername("userName"));
    }

    @Test
    void userToUserEntity() {
        UserId id = new UserId(UUID);
        AuthUsername username = new AuthUsername("userName");
        User userMock = new User(id, username);
        UserEntity result = instance.userToUserEntity(userMock);
        assertEquals(result.userId(), UUID);
        assertEquals(result.username(), "userName");

    }
}