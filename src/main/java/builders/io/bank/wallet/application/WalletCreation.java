package builders.io.bank.wallet.application;

import builders.io.bank.shared.domain.RandomGenerator;
import builders.io.bank.shared.domain.UserId;
import builders.io.bank.wallet.domain.CoinType;
import builders.io.bank.wallet.domain.Wallet;
import builders.io.bank.wallet.domain.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.util.Random;

@Service
public final class WalletCreation {
    private static final Logger logger = LoggerFactory.getLogger(WalletCreation.class);
    private final WalletRepository repository;
    private final EventBus eventBus;
    private final Random random = new Random();
    private final RandomGenerator randomGenerator;

    public WalletCreation(WalletRepository repository,
                          EventBus eventBus,
                          RandomGenerator randomGenerator
    ) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.randomGenerator = randomGenerator;
    }

    public void createWallet(UserId userId) throws WalletExistException {
        String userIdValue = userId.value();
        logger.info("Creating a new wallet for user: {}", userIdValue);
        ensureNotExistWalletFromUser(userId);

        String address = "0x"+ randomGenerator.generateRandomHexString(40);
        String publicKey = randomGenerator.generateRandomHexString(128);
        String privateKey = randomGenerator.generateRandomHexString(64);
        CoinType coinType = CoinType.ETH;
        Wallet wallet = Wallet.create(address, userId, publicKey, privateKey, coinType);
        repository.save(wallet);

        wallet.pullDomainEvents().forEach(domainEvent ->
                eventBus.notify("wallets_topic", Event.wrap(domainEvent)));
    }
    private void ensureNotExistWalletFromUser(UserId id) {
        String userIdValue = id.value();
        if (repository.searchByUserId(id).isPresent()) {
            logger.warn("Wallet already exist for user: {}", userIdValue);
            throw new WalletExistException(id);
        }
    }
}