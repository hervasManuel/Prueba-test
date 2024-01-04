package builders.io.bank.transactions.domain;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll();
    Transaction save(Transaction transaction);
    List<Transaction> findByAddress(String address);
}
