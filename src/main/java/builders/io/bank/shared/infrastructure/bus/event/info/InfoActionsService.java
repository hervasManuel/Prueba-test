package builders.io.bank.shared.infrastructure.bus.event.info;

import builders.io.bank.shared.domain.bus.event.DomainEvent;

public interface InfoActionsService {
    void printWalletCreatedOnWalletCreated(DomainEvent domainEvent)
            throws InterruptedException;
    void printWalletBalanceOnWalletBalanceIncremented(DomainEvent domainEvent)
            throws InterruptedException;

}