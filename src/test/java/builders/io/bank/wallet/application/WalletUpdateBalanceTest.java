package builders.io.bank.wallet.application;

import builders.io.bank.shared.domain.bus.event.DomainEvent;
import builders.io.bank.wallet.domain.Wallet;
import builders.io.bank.wallet.domain.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.bus.EventBus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletUpdateBalanceTest {

    @Mock
    private WalletRepository repository;

    @Mock
    private EventBus eventBus;

    @InjectMocks
    private WalletUpdateBalance instance;

    @Test
    void addBalanceSuccess() {
        DomainEvent domainEvent = mock(DomainEvent.class);
        String addressMock = "addressMock";
        BigDecimal amountMock = new BigDecimal(1);
        Wallet wallet = mock(Wallet.class);
        when(wallet.pullDomainEvents()).thenReturn(Arrays.asList(domainEvent));
        when(repository.searchByAddress(addressMock)).thenReturn(Optional.of(wallet));
        instance.addBalance(addressMock, amountMock);
        verify(repository).save(wallet);
    }

    @Test
    void addBalanceWhenNotExistWallet_shouldReturnWalletNotExistException() {
        String addressMock = "addressMock";
        BigDecimal amountMock = new BigDecimal(1);
        when(repository.searchByAddress(addressMock)).thenReturn(Optional.empty());
        assertThrows(WalletNotExistException.class, () -> instance.addBalance(addressMock, amountMock));
    }

    @Test
    void subtractBalance() {
        String addressMock = "addressMock";
        BigDecimal amountMock = new BigDecimal(100);
        BigDecimal balanceMock = new BigDecimal(200);
        DomainEvent domainEventMock = mock(DomainEvent.class);
        Wallet wallet = mock(Wallet.class);
        when(wallet.balance()).thenReturn(balanceMock);
        when(wallet.pullDomainEvents()).thenReturn(Arrays.asList(domainEventMock));

        when(repository.searchByAddress(addressMock)).thenReturn(Optional.of(wallet));
        instance.subtractBalance(addressMock, amountMock);
        verify(repository).save(wallet);
    }

    @Test
    void subtractBalanceWhenNotEnoughBalance() {
        String addressMock = "addressMock";
        BigDecimal amountMock = new BigDecimal(100);
        BigDecimal balanceMock = new BigDecimal(50);
        Wallet wallet = mock(Wallet.class);
        when(wallet.balance()).thenReturn(balanceMock);
        when(repository.searchByAddress(addressMock)).thenReturn(Optional.of(wallet));
        assertThrows(NotEnoughBalanceException.class, () -> instance.subtractBalance(addressMock, amountMock));
    }

    @Test
    void subtractBalanceWhenNotExistWallet() {
        String addressMock = "addressMock";
        BigDecimal amountMock = new BigDecimal(100);
        when(repository.searchByAddress(addressMock)).thenReturn(Optional.empty());
        assertThrows(WalletNotExistException.class, () -> instance.subtractBalance(addressMock, amountMock));
    }
}