package builders.io.bank.wallet.application;

import builders.io.bank.wallet.domain.Wallet;
import builders.io.bank.wallet.domain.WalletRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.math.BigDecimal;

@Service
public final class WalletUpdateBalance {
    private static final Logger logger = LoggerFactory.getLogger(WalletUpdateBalance.class);
    private final WalletRepository repository;
    private final EventBus eventBus;

    public WalletUpdateBalance(WalletRepository repository,
                               EventBus eventBus
    ){
        this.repository = repository;
        this.eventBus = eventBus;
    }
    public void addBalance(String address, BigDecimal amount) throws WalletNotExistException{
        logger.info("Adding balance of {}", address);
        Wallet wallet = existWallet(address);

        wallet.addBalance(amount);

        repository.save(wallet);

        wallet.pullDomainEvents().forEach( domainEvent ->
                eventBus.notify("wallets_topic", Event.wrap(domainEvent)));
    }
    public void subtractBalance(String address, BigDecimal amount) throws WalletNotExistException{
        logger.info("Subtract balance of {}", address);
        Wallet wallet = existWallet(address);
        ensureEnoughBalance(wallet,amount);

        wallet.subtractBalance(amount);
        repository.save(wallet);

        wallet.pullDomainEvents().forEach( domainEvent ->
                eventBus.notify("wallets_topic", Event.wrap(domainEvent)));
    }
    private Wallet existWallet(String address) throws WalletNotExistException{
        return repository.searchByAddress(address)
                .orElseThrow(() -> {
                    logger.warn("Wallet {} not exist",address);
                    return new WalletNotExistException(address);
                });
    }
    private void ensureEnoughBalance(Wallet wallet, BigDecimal amount){
        if ((wallet.balance().subtract(amount)).compareTo(BigDecimal.ZERO) < 0) {
            logger.warn("Insufficient balance for transaction");
            throw new NotEnoughBalanceException(wallet.address());
        }
    }
}
