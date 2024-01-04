package builders.io.bank.shared.domain.wallet;

import builders.io.bank.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class WalletCreatedDomainEvent extends DomainEvent {
    private final String address;
    private final String userId;
    private final String coinType;

    public WalletCreatedDomainEvent(String aggregateId, String address,
                                    String userId,
                                    String coinType) {
        super(aggregateId);
        this.address = address;
        this.userId = userId;
        this.coinType = coinType;
    }

    public WalletCreatedDomainEvent(
            String aggregateId,
            String eventId,
            String occurredOn,
            String address,
            String userId,
            String coinType
    ) {
        super(aggregateId, eventId, occurredOn);
        this.address = address;
        this.userId = userId;
        this.coinType = coinType;
    }

    @Override
    public String eventName() {
        return "wallet.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        HashMap<String, Serializable> primitives = new HashMap<>();
        primitives.put("address", address);
        primitives.put("userId", userId);
        primitives.put("coinType", coinType);
        return primitives;
    }
    public String address() { return address;}
    public String userId() {return userId;}
    public String coinType() {return coinType;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WalletCreatedDomainEvent that = (WalletCreatedDomainEvent) o;
        return address.equals(that.address) &&
                userId.equals(that.userId) &&
                coinType.equals(that.coinType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address,userId,coinType);
    }
}
