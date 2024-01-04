package builders.io.bank.shared.infrastructure.bus.event.info;

import builders.io.bank.shared.domain.bus.event.DomainEvent;
import builders.io.bank.shared.domain.wallet.WalletBalanceUpdatedDomainEvent;
import builders.io.bank.shared.domain.wallet.WalletCreatedDomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.bus.Event;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfoConsumerTest {

    @Mock
    private InfoActionsService infoActionsService;

    @InjectMocks
    private InfoConsumer instance;

    @Test
    void accept_whenWalletCreatedDomainEvent() throws InterruptedException {
        DomainEvent domainEventMock = mock(WalletCreatedDomainEvent.class);
        Event<DomainEvent> event = mock(Event.class);
        when(event.getData()).thenReturn(domainEventMock);
        instance.accept(event);
        verify(infoActionsService).printWalletCreatedOnWalletCreated(domainEventMock);
    }

    @Test
    void accept_whenWalletBalanceUpdatedDomainEvent() throws InterruptedException {
        DomainEvent domainEventMock = mock(WalletBalanceUpdatedDomainEvent.class);
        Event<DomainEvent> event = mock(Event.class);
        when(event.getData()).thenReturn(domainEventMock);
        instance.accept(event);
        verify(infoActionsService).printWalletBalanceOnWalletBalanceIncremented(domainEventMock);
    }

    @Test
    void acceptError() throws InterruptedException {
        DomainEvent domainEventMock = mock(WalletBalanceUpdatedDomainEvent.class);
        Event<DomainEvent> event = mock(Event.class);
        when(event.getData()).thenReturn(domainEventMock);
        doThrow(InterruptedException.class).when(infoActionsService).
                printWalletBalanceOnWalletBalanceIncremented(domainEventMock);
        instance.accept(event);
        assertTrue(Thread.interrupted());


    }
}