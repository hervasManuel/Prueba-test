package builders.io.bank.users.application;

import builders.io.bank.shared.domain.UuidGenerator;
import builders.io.bank.users.domain.AuthUsername;
import builders.io.bank.users.domain.User;
import builders.io.bank.shared.domain.UserId;
import builders.io.bank.users.domain.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;
import org.slf4j.Logger;

@Service
public final class UserRegister {
    private static final Logger logger = LoggerFactory.getLogger(UserRegister.class);
    private final UserRepository repository;
    private final EventBus eventBus;
    private final UuidGenerator uuidGenerator;

    public UserRegister(UserRepository repository,
                        EventBus eventBus,
                        UuidGenerator uuidGenerator) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.uuidGenerator = uuidGenerator;
    }
    public void register(AuthUsername username) throws UserExistException {
        String usernameValue = username.value();
        logger.debug("Registering User:{}",usernameValue);
        ensureNotExistUsername(username);

        UserId id = UserId.create(uuidGenerator.generate());
        User user = User.create(id, username);

        repository.save(user);

        user.pullDomainEvents().forEach(domainEvent ->
                eventBus.notify("users_topic", Event.wrap(domainEvent)));
    }
    private void ensureNotExistUsername(AuthUsername username) {
        String usernameValue = username.value();
        if (repository.findByUsername(username).isPresent()) {
            logger.warn("Username not created: {} exist", usernameValue);
            throw new UserExistException(username);
        }
    }
}