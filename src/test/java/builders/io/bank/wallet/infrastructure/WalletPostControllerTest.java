package builders.io.bank.wallet.infrastructure;

import builders.io.bank.shared.domain.UserId;
import builders.io.bank.wallet.application.WalletCreation;
import builders.io.bank.wallet.application.WalletExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static builders.io.bank.shared.domain.UUIDMother.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletPostControllerTest {
    @Mock
    private WalletCreation walletCreation;

    @InjectMocks
    private WalletPostController instance;

    @Test
    void createWalletSuccess() {
        RequestNewWallet requestNewWallet = mock(RequestNewWallet.class);
        UserId idMock = new UserId(UUID);
        when(requestNewWallet.userId()).thenReturn(UUID);
        try (MockedStatic<UserId> userIdMock = mockStatic(UserId.class)) {
            userIdMock.when(() -> UserId.create(UUID))
                    .thenReturn(idMock);
        }
        ResponseEntity result = instance.createWallet(requestNewWallet);
        verify(walletCreation).createWallet(idMock);
        assertEquals(result, new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Test
    void createWalletNotExistWallet_shouldReturnWalletExistException() {
        UserId idMock = new UserId(UUID);
        RequestNewWallet requestNewWallet = mock(RequestNewWallet.class);
        when(requestNewWallet.userId()).thenReturn(UUID);
        try (MockedStatic<UserId> userIdMock = mockStatic(UserId.class)) {
            userIdMock.when(() -> UserId.create(UUID))
                    .thenReturn(idMock);
        }
        doThrow(WalletExistException.class).when(walletCreation).createWallet(idMock);
        ResponseEntity result = instance.createWallet(requestNewWallet);
        assertEquals(result.getStatusCode(), HttpStatus.CONFLICT);
    }
}