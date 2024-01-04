package builders.io.bank.transactions.application;

import builders.io.bank.transactions.domain.Transaction;
import builders.io.bank.transactions.domain.TransactionRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TransactionFinder {
    private final TransactionRepository repository;
    public TransactionFinder(TransactionRepository repository){
        this.repository = repository;
    }
    public List<Transaction> findByAddress(String address){
        return repository.findByAddress(address);
    }
}
