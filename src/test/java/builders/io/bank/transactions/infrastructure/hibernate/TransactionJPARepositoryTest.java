package builders.io.bank.transactions.infrastructure.hibernate;

import builders.io.bank.transactions.domain.Transaction;
import builders.io.bank.transactions.infrastructure.persistence.TransactionEntity;
import builders.io.bank.transactions.infrastructure.persistence.TransactionEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionJPARepositoryTest {
    @Mock
    private SpringDataTransactionRepository repository;
    @Mock
    private TransactionEntityMapper transactionEntityMapper;
    @InjectMocks
    private TransactionJPARepository instance;

    @Test
    void findAll(){
        TransactionEntity transactionEntity = mock(TransactionEntity.class);
        Transaction transaction = mock(Transaction.class);
        when(repository.findAll()).thenReturn(Arrays.asList(transactionEntity));
        when(transactionEntityMapper.transactionEntityToTransaction(transactionEntity)).thenReturn(transaction);
        List<Transaction> result = instance.findAll();
        assertEquals(result, Arrays.asList(transaction));
    }

    @Test
    void save() {
        TransactionEntity transactionEntity = mock(TransactionEntity.class);
        Transaction transaction = mock(Transaction.class);
        when(transactionEntityMapper.transactionToTransactionEntity(transaction)).thenReturn(transactionEntity);
        when(repository.save(transactionEntity)).thenReturn(transactionEntity);
        when(transactionEntityMapper.transactionEntityToTransaction(transactionEntity)).thenReturn(transaction);
        Transaction result = instance.save(transaction);
        assertEquals(result, transaction);
    }

    @Test
    void findByAddress() {
        String addressMock = "addressMock";
        Transaction transaction = mock(Transaction.class);
        TransactionEntity transactionEntity = mock(TransactionEntity.class);
        when(repository.findByFromAddressOrToAddress(addressMock, addressMock)).thenReturn(Arrays.asList(transactionEntity));
        when(transactionEntityMapper.transactionEntityToTransaction(transactionEntity)).thenReturn(transaction);
        List<Transaction> result = instance.findByAddress(addressMock);
        assertEquals(result, Arrays.asList(transaction));

    }

}