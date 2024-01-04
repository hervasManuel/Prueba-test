package builders.io.bank.wallet.infrastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestNewWalletTest {

    @Test
    void userId_returnValue() {
        RequestNewWallet requestNewWallet = new RequestNewWallet();
        requestNewWallet.setUserId("userId");
        assertEquals("userId", requestNewWallet.userId());
    }
}