package builders.io.bank.shared.infrastructure.bus.event.info;

import builders.io.bank.shared.domain.bus.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InfoActionsServiceImpl implements InfoActionsService {
    private static final Logger logger = LoggerFactory.getLogger(InfoActionsServiceImpl.class);

    @Override
    public void printWalletCreatedOnWalletCreated(DomainEvent domainEvent) {
        String message = domainEvent.aggregateId();
        logger.info("WalletCreated event - Wallet address: {}", message);
    }
    @Override
    public void printWalletBalanceOnWalletBalanceIncremented(DomainEvent domainEvent) {
        String message = domainEvent.aggregateId();
        logger.info("WalletBalanceIncremented event - Wallet address: {}", message);
    }
}