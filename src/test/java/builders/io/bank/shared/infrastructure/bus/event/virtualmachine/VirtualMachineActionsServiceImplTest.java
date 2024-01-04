package builders.io.bank.shared.infrastructure.bus.event.virtualmachine;

import builders.io.bank.shared.domain.bus.event.DomainEvent;
import builders.io.bank.wallet.application.WalletUpdateBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static builders.io.bank.transactions.domain.TxnMethod.ADD_BALANCE;
import static builders.io.bank.transactions.domain.TxnMethod.TRANSFER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VirtualMachineActionsServiceImplTest {

    @Mock
    private WalletUpdateBalance walletUpdateBalance;

    @InjectMocks
    private VirtualMachineActionsServiceImpl instance;

    Map eventPrimitives;

    @BeforeEach
    void setUp() {
        eventPrimitives = new HashMap<String, Serializable>();
        eventPrimitives.put("from", "fromMock");
        eventPrimitives.put("to", "toMock");
        eventPrimitives.put("value", new BigDecimal(1));
    }

    @Test
    void executeTransactionOnTransactionSent_withAddBalance() {
        DomainEvent domainEvent = mock(DomainEvent.class);
        eventPrimitives.put("method", ADD_BALANCE);
        when(domainEvent.toPrimitives()).thenReturn(eventPrimitives);

        instance.executeTransactionOnTransactionSent(domainEvent);
        verify(walletUpdateBalance).addBalance(any(), any());
    }

    @Test
    void executeTransactionOnTransactionSent_withTransfer() {
        DomainEvent domainEvent = mock(DomainEvent.class);
        eventPrimitives.put("method", TRANSFER);
        when(domainEvent.toPrimitives()).thenReturn(eventPrimitives);
        instance.executeTransactionOnTransactionSent(domainEvent);
        verify(walletUpdateBalance).subtractBalance(any(), any());
    }
}