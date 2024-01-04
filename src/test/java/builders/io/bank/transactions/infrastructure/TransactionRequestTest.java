package builders.io.bank.transactions.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionRequestTest {

    @Test
    void method_returnValue() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMethod("methodMock");
        assertEquals("methodMock", transactionRequest.method());

    }

    @Test
    void from_returnValue() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setFrom("fromMock");
        assertEquals("fromMock", transactionRequest.from());
    }

    @Test
    void to_returnValue() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTo("toMock");
        assertEquals("toMock", transactionRequest.to());
    }

    @Test
    void value_returnValue() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setValue(new BigDecimal(1));
        assertEquals(new BigDecimal(1), transactionRequest.value());
    }
}