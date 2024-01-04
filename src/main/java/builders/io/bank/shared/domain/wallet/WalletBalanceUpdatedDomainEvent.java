package builders.io.bank.shared.domain.wallet;

import builders.io.bank.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

public class WalletBalanceUpdatedDomainEvent extends DomainEvent {
    private final String address;
    private final String userId;
    private final String coinType;
    private final BigDecimal value;

    public WalletBalanceUpdatedDomainEvent(String aggregateId, String address,
                                           String userId,
                                           String coinType,
                                           BigDecimal value) {
        super(aggregateId);
        this.address = address;
        this.userId = userId;
        this.coinType = coinType;
        this.value = value;
    }

    public WalletBalanceUpdatedDomainEvent(
            String aggregateId,
            String eventId,
            String occurredOn,
            String address,
            String userId,
            String coinType,
            BigDecimal value
    ) {
        super(aggregateId, eventId, occurredOn);
        this.address = address;
        this.userId = userId;
        this.coinType = coinType;
        this.value = value;
    }

    @Override
    public String eventName() {
        return "wallet.balance.incremented";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        HashMap<String, Serializable> primitives = new HashMap<>();
        primitives.put("address", address);
        primitives.put("userId", userId);
        primitives.put("coinType", coinType);
        primitives.put("value", value);
        return primitives;
    }

    public String address() { return address;}
    public String userId() {return userId;}
    public String coinType() {return coinType;}
    public BigDecimal value() {return value;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WalletBalanceUpdatedDomainEvent that = (WalletBalanceUpdatedDomainEvent) o;
        return address.equals(that.address) &&
                userId.equals(that.userId) &&
                coinType.equals(that.coinType) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address,userId,coinType,value);
    }
}
