package builders.io.bank.wallet.infrastructure.hibernate;

import builders.io.bank.wallet.infrastructure.persistence.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataWalletRepository extends JpaRepository<WalletEntity,Long> {
    Optional<WalletEntity> findByUserId(String userId);
    Optional<WalletEntity> findByAddress(String address);
}
