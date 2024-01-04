package builders.io.bank.transactions.application;

import builders.io.bank.transactions.domain.Transaction;
import builders.io.bank.transactions.domain.TransactionRepository;
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
class TransactionFinderTest {

    @Mock
    private  TransactionRepository repository;

    @InjectMocks
    private TransactionFinder instance;

    @Test
    void findByAddress() {
        String addressMock = "addressMock";
        Transaction transaction = mock(Transaction.class);
        when(repository.findByAddress(addressMock)).thenReturn(Arrays.asList(transaction));
        List<Transaction> result = instance.findByAddress(addressMock);
        assertEquals(result, Arrays.asList(transaction));
    }
}