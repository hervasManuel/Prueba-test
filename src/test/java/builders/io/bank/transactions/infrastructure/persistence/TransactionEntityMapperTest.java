package builders.io.bank.transactions.infrastructure.persistence;

import builders.io.bank.transactions.domain.Transaction;
import builders.io.bank.transactions.domain.TxnMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionEntityMapperTest {

    @InjectMocks
    private TransactionEntityMapper instance;

    TransactionEntity transactionEntity;
    Transaction transaction;
    @BeforeEach
    void setUp() {
        Timestamp timestamp = mock(Timestamp.class);
        transactionEntity = mock(TransactionEntity.class);
        when(transactionEntity.txnMethod()).thenReturn(TxnMethod.TRANSFER);
        when(transactionEntity.fromAddress()).thenReturn("fromAddress");
        when(transactionEntity.toAddress()).thenReturn("toAddress");
        when(transactionEntity.value()).thenReturn(new BigDecimal(1));
        when(transactionEntity.timestamp()).thenReturn(timestamp);

        transaction = mock(Transaction.class);
        when(transaction.txnMethod()).thenReturn(TxnMethod.TRANSFER);
        when(transaction.from()).thenReturn("fromAddress");
        when(transaction.to()).thenReturn("toAddress");
        when(transaction.value()).thenReturn(new BigDecimal(1));
        when(transaction.timestamp()).thenReturn(timestamp);
    }

    @Test
    void transactionEntityToTransaction() {
        Transaction result = instance.transactionEntityToTransaction(transactionEntity);
        assertEquals(result.txnMethod(), transaction.txnMethod());
        assertEquals(result.from(), transaction.from());
        assertEquals(result.to(), transaction.to());
        assertEquals(result.value(), transaction.value());
        assertEquals(result.timestamp(), transaction.timestamp());

    }

    @Test
    void transactionToTransactionEntity() {
        TransactionEntity result = instance.transactionToTransactionEntity(transaction);
        assertEquals(result.txnMethod(), transactionEntity.txnMethod());
        assertEquals(result.fromAddress(), transactionEntity.fromAddress());
        assertEquals(result.toAddress(), transactionEntity.toAddress());
        assertEquals(result.value(), transactionEntity.value());
        assertEquals(result.timestamp(), transactionEntity.timestamp());
    }
}