package builders.io.bank.wallet.application;

import builders.io.bank.wallet.domain.Wallet;
import builders.io.bank.wallet.domain.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletDetailTest {

    @Mock
    private WalletRepository repository;

    @InjectMocks
    private WalletDetail instance;

    @Test
    void getBalance() {
        String addressMock = "addressMock";
        Wallet wallet = mock(Wallet.class);
        BigDecimal amountMock = new BigDecimal(1);
        when(wallet.balance()).thenReturn(amountMock);
        when(repository.searchByAddress(addressMock)).thenReturn(Optional.of(wallet));
        BigDecimal result = instance.getBalance(addressMock);
        assertEquals(result, amountMock);
    }

    @Test
    void getBalanceWhenNotExistWallet_shouldReturnWalletNotExistException() {
        String addressMock = "addressMock";
        when(repository.searchByAddress(addressMock)).thenReturn(Optional.empty());
        assertThrows(WalletNotExistException.class, () -> instance.getBalance(addressMock));
    }
}