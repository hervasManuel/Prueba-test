package builders.io.bank.transactions.application;

import builders.io.bank.shared.domain.UuidGenerator;
import builders.io.bank.transactions.domain.Transaction;
import builders.io.bank.transactions.domain.TransactionRepository;
import builders.io.bank.transactions.domain.TxnMethod;
import builders.io.bank.transactions.infrastructure.TransactionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.bus.EventBus;

import java.math.BigDecimal;

import static builders.io.bank.shared.domain.UUIDMother.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionSenderTest {

    @Mock
    private EventBus eventBus;

    @Mock
    private UuidGenerator uuidGenerator;

    @Mock
    private TransactionRepository repository;
    @InjectMocks
    private TransactionSender instance;

    BigDecimal bigDecimalMock;
    Transaction transaction;

    @BeforeEach
    void setUp() {
        bigDecimalMock = mock(BigDecimal.class);
        transaction = mock(Transaction.class);
        when(transaction.txnMethod()).thenReturn(TxnMethod.ADD_BALANCE);
        when(transaction.from()).thenReturn("fromMock");
        when(transaction.to()).thenReturn("toMock");
        when(transaction.value()).thenReturn(bigDecimalMock);
    }

    @Test
    void sendTransaction() {
        TransactionRequest transactionRequest = mock(TransactionRequest.class);

        when(transactionRequest.method()).thenReturn(TxnMethod.ADD_BALANCE.name());
        when(transactionRequest.from()).thenReturn("fromMock");
        when(transactionRequest.to()).thenReturn("toMock");
        when(transactionRequest.value()).thenReturn(bigDecimalMock);
        when(uuidGenerator.generate()).thenReturn(UUID);

        try (MockedStatic<Transaction> transactionMock = mockStatic(Transaction.class)) {
            transactionMock.when(() -> Transaction.send(UUID,TxnMethod.ADD_BALANCE, "fromMock", "toMock", bigDecimalMock))
                    .thenReturn(transaction);
        }

        instance.sendTransaction(transactionRequest);
        assertEquals(transaction.txnMethod().name(), transactionRequest.method());
        assertEquals(transaction.from(), transactionRequest.from());
        assertEquals(transaction.to(), transactionRequest.to());
        assertEquals(transaction.value(), transactionRequest.value());
    }
}