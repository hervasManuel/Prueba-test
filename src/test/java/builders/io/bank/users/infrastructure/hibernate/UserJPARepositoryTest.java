package builders.io.bank.users.infrastructure.hibernate;

import builders.io.bank.shared.domain.UserId;
import builders.io.bank.users.domain.AuthUsername;
import builders.io.bank.users.domain.AuthUsernameMother;
import builders.io.bank.users.domain.User;
import builders.io.bank.users.infrastructure.persistence.UserEntity;
import builders.io.bank.users.infrastructure.persistence.UserEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static builders.io.bank.shared.domain.UUIDMother.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserJPARepositoryTest {
    @Mock
    private SpringDataUserRepository repository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJPARepository instance;

    @Test
    void findAll() {
        UserEntity userEntity = mock(UserEntity.class);
        User user = mock(User.class);
        when(userEntityMapper.userEntityToUser(userEntity)).thenReturn(user);
        when(repository.findAll()).thenReturn(Arrays.asList(userEntity));
        List<User> result = instance.findAll();
        assertEquals(result, Arrays.asList(user));
    }

    @Test
    void save() {
        AuthUsername username = AuthUsernameMother.random();
        UserId id = new UserId(UUID);
        User user = new User(id, username);
        UserEntity userEntity = mock(UserEntity.class);

        when(userEntityMapper.userToUserEntity(user)).thenReturn(userEntity);
        when(repository.save(userEntity)).thenReturn(userEntity);
        when(userEntityMapper.userEntityToUser(userEntity)).thenReturn(user);
        User result = instance.save(user);
        assertEquals(result.username(), user.username());
        assertEquals(result.id(), user.id());
    }

    @Test
    void findByUserId() {
        AuthUsername username = AuthUsernameMother.random();
        UserId id = new UserId(UUID);
        User user = new User(id, username);
        UserEntity userEntity = mock(UserEntity.class);
        when(repository.findByUserId(UUID)).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.userEntityToUser(userEntity)).thenReturn(user);
        Optional<User> result = instance.findByUserId(id);
        assertEquals(result, Optional.of(user));
    }

    @Test
    void findByUsername() {
        AuthUsername username = new AuthUsername("userNameMock");
        UserId id = new UserId(UUID);
        User user = new User(id, username);
        UserEntity userEntity = mock(UserEntity.class);
        when(repository.findByUsername("userNameMock")).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.userEntityToUser(userEntity)).thenReturn(user);
        Optional<User> result = instance.findByUsername(username);
        assertEquals(result, Optional.of(user));
    }
}