package builders.io.bank.shared.infrastructure.bus.event.virtualmachine;

import builders.io.bank.shared.domain.bus.event.DomainEvent;

public interface VirtualMachineActionsService {
    void executeTransactionOnTransactionSent(DomainEvent domainEvent)
            throws InterruptedException;

}