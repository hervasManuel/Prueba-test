package builders.io.bank.wallet.application;

import builders.io.bank.shared.domain.RandomGenerator;
import builders.io.bank.shared.domain.UserId;
import builders.io.bank.wallet.domain.CoinType;
import builders.io.bank.wallet.domain.Wallet;
import builders.io.bank.wallet.domain.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.bus.EventBus;

import java.math.BigDecimal;
import java.util.Optional;

import static builders.io.bank.shared.domain.UUIDMother.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletCreationTest {

    @Mock
    private WalletRepository repository;
    @Mock
    private EventBus eventBus;

    @Mock
    private RandomGenerator randomGenerator;
    @InjectMocks
    private WalletCreation instance;

    @Test
     void createWallet() {
        UserId idMock = new UserId(UUID);
        Wallet wallet = new Wallet("0xrandom", idMock,
                "random", "random", new BigDecimal(0), CoinType.ETH);
        when(randomGenerator.generateRandomHexString(anyInt())).thenReturn("random");
        when(repository.searchByUserId(idMock)).thenReturn(Optional.empty());

        try (MockedStatic<Wallet> walletMock = mockStatic(Wallet.class)) {
            walletMock.when(() -> Wallet.create("0xrandom",idMock,
                            "random", "random", CoinType.ETH))
                    .thenReturn(wallet);
        }
        instance.createWallet(idMock);
        verify(repository).save(wallet);
    }

    @Test
    void createWalletWhenExistWalletFromUser_shouldReturnWalletExistException() {
        UserId idMock = new UserId(UUID);
        Wallet wallet = new Wallet("0xrandom", idMock,
                "random", "random", new BigDecimal(0), CoinType.ETH);
        when(repository.searchByUserId(idMock)).thenReturn(Optional.of(wallet));
        assertThrows(WalletExistException.class, () -> instance.createWallet(idMock));
    }
}