package builders.io.bank.wallet.domain;

import builders.io.bank.shared.domain.UserId;

import java.util.List;
import java.util.Optional;

public interface WalletRepository {
    List<Wallet> findAll();
    Wallet save(Wallet wallet);
    Optional<Wallet> searchByAddress(String address);
    Optional<Wallet> searchByUserId(UserId id);
}
