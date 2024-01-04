package builders.io.bank.transactions.infrastructure;

import builders.io.bank.transactions.application.TransactionFinder;
import builders.io.bank.transactions.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionGetControllerTest {

    @Mock
    private TransactionFinder transactionFinder;

    @InjectMocks
    private TransactionGetController instance;

    @Test
    void getTransactionsByAddress() {
        String addressMock = "addressMock";
        Transaction transactionMock = mock(Transaction.class);
        when(transactionFinder.findByAddress(addressMock)).thenReturn(Arrays.asList(transactionMock));
        ResponseEntity result = instance.getTransactionsByAddress(addressMock);
        assertEquals(result.getStatusCode(), HttpStatus.OK);

    }
}