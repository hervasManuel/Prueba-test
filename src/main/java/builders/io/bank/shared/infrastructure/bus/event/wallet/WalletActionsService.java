package builders.io.bank.shared.infrastructure.bus.event.wallet;

import builders.io.bank.shared.domain.bus.event.DomainEvent;

public interface WalletActionsService {
    void createWalletOnUserCreated(DomainEvent domainEvent)
            throws InterruptedException;

}