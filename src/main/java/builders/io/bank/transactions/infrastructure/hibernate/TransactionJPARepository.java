package builders.io.bank.transactions.infrastructure.hibernate;

import builders.io.bank.transactions.domain.Transaction;
import builders.io.bank.transactions.domain.TransactionRepository;
import builders.io.bank.transactions.infrastructure.persistence.TransactionEntity;
import builders.io.bank.transactions.infrastructure.persistence.TransactionEntityMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class TransactionJPARepository implements TransactionRepository {
    private final SpringDataTransactionRepository repository;
    private final TransactionEntityMapper transactionEntityMapper;
    public TransactionJPARepository(SpringDataTransactionRepository repository, TransactionEntityMapper transactionEntityMapper){
        this.repository = repository;
        this.transactionEntityMapper = transactionEntityMapper;
    }
    @Override
    public List<Transaction> findAll(){
        List<Transaction> transactionList = new ArrayList<>();
        repository.findAll().forEach(transactionEntity ->
                transactionList.add(transactionEntityMapper.transactionEntityToTransaction(transactionEntity)));
        return transactionList;
    }
    @Override
    public Transaction save(Transaction transaction){
        TransactionEntity entityTransaction = transactionEntityMapper.transactionToTransactionEntity(transaction);
        return transactionEntityMapper.transactionEntityToTransaction(repository.save(entityTransaction));
    }

    @Override
    public List<Transaction> findByAddress(String address) {
        return repository.findByFromAddressOrToAddress(address,address)
                .stream()
                .map(transactionEntityMapper::transactionEntityToTransaction)
                .toList();

    }
}
