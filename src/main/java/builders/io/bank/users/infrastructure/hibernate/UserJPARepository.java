package builders.io.bank.users.infrastructure.hibernate;

import builders.io.bank.users.domain.AuthUsername;
import builders.io.bank.users.domain.User;
import builders.io.bank.shared.domain.UserId;
import builders.io.bank.users.domain.UserRepository;
import builders.io.bank.users.infrastructure.persistence.UserEntity;
import builders.io.bank.users.infrastructure.persistence.UserEntityMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public final class UserJPARepository implements UserRepository {
    private final SpringDataUserRepository repository;
    private final UserEntityMapper userEntityMapper;
    public UserJPARepository(SpringDataUserRepository repository, UserEntityMapper userEntityMapper){
        this.repository = repository;
        this.userEntityMapper = userEntityMapper;
    }
    @Override
    public List<User> findAll(){
        List<User> userList = new ArrayList<>();
        repository.findAll().forEach(userEntity -> userList.add(userEntityMapper.userEntityToUser(userEntity)));
        return userList;
    }
    @Override
    public User save(User user){
        UserEntity entityUser = userEntityMapper.userToUserEntity(user);
        return userEntityMapper.userEntityToUser(repository.save(entityUser));
    }
    @Override
    public Optional<User> findByUserId(UserId id){
        return repository.findByUserId(id.value())
                .map(userEntityMapper::userEntityToUser);
    }
    public Optional<User> findByUsername(AuthUsername username){
        return repository.findByUsername(username.value())
                .map(userEntityMapper::userEntityToUser);
    }
}
