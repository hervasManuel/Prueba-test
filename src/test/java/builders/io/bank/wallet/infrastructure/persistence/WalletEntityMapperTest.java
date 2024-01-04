package builders.io.bank.wallet.infrastructure.persistence;

import builders.io.bank.shared.domain.UserId;
import builders.io.bank.wallet.domain.CoinType;
import builders.io.bank.wallet.domain.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static builders.io.bank.shared.domain.UUIDMother.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletEntityMapperTest {
    @InjectMocks
    private WalletEntityMapper instance;

    WalletEntity walletEntity;

    Wallet wallet;

    @BeforeEach
    void setUp() {
        BigDecimal balanceMock = new BigDecimal(1);
        CoinType coinTypeMock = CoinType.ETH;
        UserId idMock = new UserId(UUID);

        walletEntity = mock(WalletEntity.class);
        when(walletEntity.address()).thenReturn("addressMock");
        when(walletEntity.userId()).thenReturn(UUID);
        when(walletEntity.publicKey()).thenReturn("publicKeyMock");
        when(walletEntity.privateKey()).thenReturn("privateKeyMock");
        when(walletEntity.balance()).thenReturn(balanceMock);
        when(walletEntity.coinType()).thenReturn(coinTypeMock);

        wallet = mock(Wallet.class);
        when(wallet.address()).thenReturn("addressMock");
        when(wallet.userId()).thenReturn(idMock);
        when(wallet.publicKey()).thenReturn("publicKeyMock");
        when(wallet.privateKey()).thenReturn("privateKeyMock");
        when(wallet.balance()).thenReturn(balanceMock);
        when(wallet.coinType()).thenReturn(coinTypeMock);
    }

    @Test
    void walletEntityToWallet() {
        Wallet result = instance.walletEntityToWallet(walletEntity);
        assertEquals(result.address(), wallet.address());
        assertEquals(result.userId(), wallet.userId());
        assertEquals(result.publicKey(), wallet.publicKey());
        assertEquals(result.privateKey(), wallet.privateKey());
        assertEquals(result.balance(), wallet.balance());
        assertEquals(result.coinType(), wallet.coinType());
    }

    @Test
    void walletToWalletEntity() {
        WalletEntity result = instance.walletToWalletEntity(wallet);
        assertEquals(result.address(), walletEntity.address());
        assertEquals(result.userId(), walletEntity.userId());
        assertEquals(result.publicKey(), walletEntity.publicKey());
        assertEquals(result.privateKey(), walletEntity.privateKey());
        assertEquals(result.balance(), walletEntity.balance());
        assertEquals(result.coinType(), walletEntity.coinType());
    }
}