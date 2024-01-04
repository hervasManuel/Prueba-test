package builders.io.bank.transactions.infrastructure.hibernate;

import builders.io.bank.transactions.infrastructure.persistence.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity,String> {
    List<TransactionEntity> findByFromAddressOrToAddress(String from,String to);

}
