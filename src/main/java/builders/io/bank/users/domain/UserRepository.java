package builders.io.bank.users.domain;

import builders.io.bank.shared.domain.UserId;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    User save(User user);
    Optional<User> findByUserId(UserId id);
    Optional<User> findByUsername(AuthUsername username);
}
