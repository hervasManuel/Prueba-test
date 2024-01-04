package builders.io.bank.transactions.infrastructure.persistence;

import builders.io.bank.transactions.domain.Transaction;
import org.springframework.stereotype.Service;

@Service
public final class TransactionEntityMapper {
    public Transaction transactionEntityToTransaction(TransactionEntity transactionEntity){
        return new Transaction(transactionEntity.transactionId(),
                               transactionEntity.txnMethod(),
                               transactionEntity.fromAddress(),
                               transactionEntity.toAddress(),
                               transactionEntity.value(),
                               transactionEntity.timestamp());
    }
    public TransactionEntity transactionToTransactionEntity(Transaction transaction){
        return new TransactionEntity(transaction.transactionId(),
                                     transaction.txnMethod(),
                                     transaction.from(),
                                     transaction.to(),
                                     transaction.value(),
                                     transaction.timestamp());
    }
}