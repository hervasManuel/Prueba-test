package builders.io.bank.wallet.infrastructure.hibernate;

import builders.io.bank.shared.domain.UserId;
import builders.io.bank.wallet.domain.Wallet;
import builders.io.bank.wallet.infrastructure.persistence.WalletEntity;
import builders.io.bank.wallet.infrastructure.persistence.WalletEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static builders.io.bank.shared.domain.UUIDMother.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletJPARepositoryTest {
    @Mock
    private SpringDataWalletRepository repository;
    @Mock
    private WalletEntityMapper walletEntityMapper;

    @InjectMocks
    private WalletJPARepository instance;

    @Test
    void findAll() {
        Wallet walletMock = mock(Wallet.class);
        WalletEntity walletEntityMock = mock(WalletEntity.class);
        when(walletEntityMapper.walletEntityToWallet(walletEntityMock)).thenReturn(walletMock);
        when(repository.findAll()).thenReturn(Arrays.asList(walletEntityMock));
        List<Wallet> result = instance.findAll();
        assertEquals(result, Arrays.asList(walletMock));
    }

    @Test
    void save() {
        Wallet walletMock = mock(Wallet.class);
        WalletEntity walletEntityMock = mock(WalletEntity.class);
        when(walletEntityMapper.walletToWalletEntity(walletMock)).thenReturn(walletEntityMock);
        when(repository.save(walletEntityMock)).thenReturn(walletEntityMock);
        when(walletEntityMapper.walletEntityToWallet(walletEntityMock)).thenReturn(walletMock);
        Wallet result =  instance.save(walletMock);
        assertEquals(result, walletMock);
    }

    @Test
    void searchByAddress() {
        String addressMock = "addressMock";
        Wallet walletMock = mock(Wallet.class);
        WalletEntity walletEntityMock = mock(WalletEntity.class);
        when(repository.findByAddress(addressMock)).thenReturn(Optional.of(walletEntityMock));
        when(walletEntityMapper.walletEntityToWallet(walletEntityMock)).thenReturn(walletMock);
        Optional<Wallet> result = instance.searchByAddress(addressMock);
        assertEquals(result, Optional.of(walletMock));

    }

    @Test
    void searchByUserId() {
        UserId idMock = new UserId(UUID);
        WalletEntity walletEntityMock = mock(WalletEntity.class);
        Wallet walletMock = mock(Wallet.class);
        when(repository.findByUserId(UUID)).thenReturn(Optional.of(walletEntityMock));
        when(walletEntityMapper.walletEntityToWallet(walletEntityMock)).thenReturn(walletMock);
        Optional<Wallet> result = instance.searchByUserId(idMock);
        assertEquals(result, Optional.of(walletMock));
    }
}