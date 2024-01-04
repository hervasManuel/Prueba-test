package builders.io.bank.users.infrastructure.persistence;

import builders.io.bank.users.domain.AuthUsername;
import builders.io.bank.users.domain.User;
import builders.io.bank.shared.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class UserEntityMapperImpl implements UserEntityMapper{
    @Override
    public User userEntityToUser(UserEntity userEntity){
        return new User(new UserId(userEntity.userId()), new AuthUsername(userEntity.username()));
    }
    @Override
    public UserEntity userToUserEntity(User user){

        return new UserEntity(user.id().value(), user.username().value());
    }
}
