package builders.io.bank.shared.infrastructure.bus.event.info;

import builders.io.bank.shared.domain.bus.event.DomainEvent;
import builders.io.bank.shared.domain.wallet.WalletBalanceUpdatedDomainEvent;
import builders.io.bank.shared.domain.wallet.WalletCreatedDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class InfoConsumer implements  Consumer<Event<DomainEvent>> {
    private static final Logger logger = LoggerFactory.getLogger(InfoConsumer.class);
    @Autowired
    private InfoActionsService infoActionsService;
    @Override
    public void accept(Event<DomainEvent> notificationDataEvent) {
        DomainEvent notificationData = notificationDataEvent.getData();
        try {
            if (notificationData instanceof WalletCreatedDomainEvent)
                infoActionsService.printWalletCreatedOnWalletCreated(notificationData);
            if (notificationData instanceof WalletBalanceUpdatedDomainEvent)
                infoActionsService.printWalletBalanceOnWalletBalanceIncremented(notificationData);
        }
        catch (InterruptedException e) {
            logger.warn("Wallet notifications interrupted!: {}",e.getMessage());
            Thread.currentThread().interrupt();

        }
    }
}
