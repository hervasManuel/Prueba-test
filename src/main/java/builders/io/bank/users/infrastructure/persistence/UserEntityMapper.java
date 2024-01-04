package builders.io.bank.users.infrastructure.persistence;

import builders.io.bank.users.domain.User;


public interface UserEntityMapper {
    User userEntityToUser(UserEntity userEntity);
    UserEntity userToUserEntity(User user);
}
