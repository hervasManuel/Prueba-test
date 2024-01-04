package builders.io.bank.users.domain;

import builders.io.bank.shared.domain.AggregateRoot;
import builders.io.bank.shared.domain.UserId;
import builders.io.bank.shared.domain.user.UserCreatedDomainEvent;

import java.util.Objects;

public final class User extends AggregateRoot {
    private final UserId userId;
    private final AuthUsername username;
    public User(UserId userId, AuthUsername username) {
        this.userId = userId;
        this.username = username;
    }
    public static User create(UserId id, AuthUsername username) {
        User user = new User(id, username);
        user.recordEvent(new UserCreatedDomainEvent(id.value(), username.value()));
        return user;
    }
    public UserId id() {
        return userId;
    }
    public AuthUsername username() {
        return username;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userId.equals(user.userId) &&
                username.equals(user.username);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userId, username);
    }


}
