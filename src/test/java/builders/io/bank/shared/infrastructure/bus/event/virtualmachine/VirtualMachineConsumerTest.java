package builders.io.bank.shared.infrastructure.bus.event.virtualmachine;

import builders.io.bank.shared.domain.bus.event.DomainEvent;
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
class VirtualMachineConsumerTest {

    @Mock
    private VirtualMachineActionsService virtualMachineActionsService;

    @InjectMocks
    private VirtualMachineConsumer instance;

    @Test
    void accept() throws InterruptedException {
        Event<DomainEvent> notificationDataEvent = mock(Event.class);
        DomainEvent domainEventMock = mock(DomainEvent.class);
        when(notificationDataEvent.getData()).thenReturn(domainEventMock);
        instance.accept(notificationDataEvent);
        verify(virtualMachineActionsService).executeTransactionOnTransactionSent(domainEventMock);
    }

    @Test
    void acceptError_shouldReturnInterruptedException() throws InterruptedException {
        Event<DomainEvent> notificationDataEvent = mock(Event.class);
        DomainEvent domainEventMock = mock(DomainEvent.class);
        when(notificationDataEvent.getData()).thenReturn(domainEventMock);
        doThrow(InterruptedException.class).when(virtualMachineActionsService).executeTransactionOnTransactionSent(domainEventMock);
        instance.accept(notificationDataEvent);
        assertTrue(Thread.interrupted());
    }
}