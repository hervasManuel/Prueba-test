package builders.io.bank.shared.domain.user;

import builders.io.bank.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class UserCreatedDomainEvent extends DomainEvent {
    private final String username;

    public UserCreatedDomainEvent(String aggregateId, String username) {
        super(aggregateId);
        this.username = username;
    }

    public UserCreatedDomainEvent(
            String aggregateId,
            String eventId,
            String occurredOn,
            String username
    ) {
        super(aggregateId, eventId, occurredOn);
        this.username = username;
    }

    @Override
    public String eventName() {
        return "user.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        HashMap<String, Serializable> primitives = new HashMap<>();
        primitives.put("username", username);
        return primitives;
    }
    public String username() {
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
        UserCreatedDomainEvent that = (UserCreatedDomainEvent) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
