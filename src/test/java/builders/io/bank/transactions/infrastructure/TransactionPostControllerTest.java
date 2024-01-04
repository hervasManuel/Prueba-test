package builders.io.bank.transactions.infrastructure;

import builders.io.bank.transactions.application.TransactionSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionPostControllerTest {

    @Mock
    private TransactionSender transactionSender;

    @InjectMocks
    private TransactionPostController instance;

    @Test
    void sendTransactionSuccess() {
        TransactionRequest requestTransactionMock = mock(TransactionRequest.class);
        ResponseEntity result = instance.sendTransaction(requestTransactionMock);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(transactionSender).sendTransaction(requestTransactionMock);
    }
}