package builders.io.bank.virtualmachine.application;

import builders.io.bank.wallet.application.WalletUpdateBalance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransferTrxTest {

    @Mock
    private WalletUpdateBalance walletUpdateBalance;
    @Mock
    private BigDecimal value;

    @InjectMocks
    private TransferTrx instance;

    @Test
    void execute() {
        value = new BigDecimal(1);
        instance.execute();
        verify(walletUpdateBalance).subtractBalance(any(), any());
        verify(walletUpdateBalance).addBalance(any(), any());
    }
}