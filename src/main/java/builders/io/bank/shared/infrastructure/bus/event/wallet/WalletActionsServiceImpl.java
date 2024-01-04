package builders.io.bank.shared.infrastructure.bus.event.wallet;

import builders.io.bank.shared.domain.UserId;
import builders.io.bank.shared.domain.bus.event.DomainEvent;
import builders.io.bank.wallet.application.WalletCreation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WalletActionsServiceImpl implements WalletActionsService {
    private static final Logger logger = LoggerFactory.getLogger(WalletActionsServiceImpl.class);
    private final WalletCreation walletCreation;

    public WalletActionsServiceImpl(WalletCreation walletCreation) {
        this.walletCreation = walletCreation;
    }
    @Override
    public void createWalletOnUserCreated(DomainEvent domainEvent) throws InterruptedException {
        logger.info("Creating Wallet on UserCreated event received");

        try {
            UserId userid = new UserId(domainEvent.aggregateId());
            walletCreation.createWallet(userid);
        }
        catch (Exception e){
            logger.error("Error creating new wallet for user: {}", domainEvent.aggregateId());
        }
    }
}