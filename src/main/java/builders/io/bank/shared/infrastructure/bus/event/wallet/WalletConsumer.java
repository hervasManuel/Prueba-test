package builders.io.bank.shared.infrastructure.bus.event.wallet;

import builders.io.bank.shared.domain.bus.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.bus.Event;
import reactor.fn.Consumer;
@Service
public class WalletConsumer implements  Consumer<Event<DomainEvent>> {
    private static final Logger logger = LoggerFactory.getLogger(WalletConsumer.class);
    @Autowired
    private WalletActionsService walletActionsService;
    @Override
    public void accept(Event<DomainEvent> notificationDataEvent) {
        DomainEvent eventData = notificationDataEvent.getData();
        try {
            walletActionsService.createWalletOnUserCreated(eventData);
        } catch (InterruptedException e) {
            logger.warn("CreateWalletOnUserCreated interrupted!: {}",e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
