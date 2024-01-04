package builders.io.bank.wallet.infrastructure.persistence;

import builders.io.bank.shared.domain.UserId;
import builders.io.bank.wallet.domain.Wallet;
import org.springframework.stereotype.Component;

@Component
public final class WalletEntityMapper {
    private WalletEntityMapper(){}
    public Wallet walletEntityToWallet(WalletEntity walletEntity) {
        return new Wallet(walletEntity.address(),
                new UserId(walletEntity.userId()),
                walletEntity.publicKey(),
                walletEntity.privateKey(),
                walletEntity.balance(),
                walletEntity.coinType());
    }
    public WalletEntity walletToWalletEntity(Wallet wallet) {
        return new WalletEntity(wallet.address(),
                wallet.userId().value(),
                wallet.publicKey(),
                wallet.privateKey(),
                wallet.balance(),
                wallet.coinType());
    }
}
