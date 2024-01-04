package builders.io.bank.wallet.infrastructure;

import builders.io.bank.wallet.application.WalletDetail;
import builders.io.bank.wallet.application.WalletNotExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletGetControllerTest {

    @Mock
    private WalletDetail walletDetail;
    @InjectMocks
    private WalletGetController instance;

    @Test
    void getBalanceSuccess() {
        String addressMock = "addressMock";
        BigDecimal balanceMock = new BigDecimal(1);
        String response = "Wallet: addressMock Balance: 1";
        when(walletDetail.getBalance(addressMock)).thenReturn(balanceMock);
        ResponseEntity result = instance.getBalance(addressMock);
        assertEquals(result, new ResponseEntity<>(response, HttpStatus.OK));
    }

    @Test
    void getBalanceWhenWalletNotExist_shouldReturnWalletNotExistException() {
        String addressMock = "addressMock";
        when(walletDetail.getBalance(addressMock)).thenThrow(WalletNotExistException.class);
        ResponseEntity result = instance.getBalance(addressMock);
        assertEquals(result.getStatusCode(),HttpStatus.NOT_FOUND);
    }
}