package builders.io.bank.wallet.application;

import builders.io.bank.wallet.domain.Wallet;
import builders.io.bank.wallet.domain.WalletRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public final class WalletDetail {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WalletDetail.class);
    private final WalletRepository repository;

    public WalletDetail(WalletRepository repository
    ){
        this.repository = repository;
    }
    public BigDecimal getBalance(String address) throws WalletNotExistException{
        logger.debug("Getting balance from wallet");
        Wallet wallet = existWallet(address);
        return wallet.balance();
    }
    private Wallet existWallet(String address) throws WalletNotExistException{
        return repository.searchByAddress(address)
                .orElseThrow(() -> {
                    logger.warn("Wallet {} not exist",address);
                    return new WalletNotExistException(address);
                });
    }
}
