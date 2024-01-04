package builders.io.bank.wallet.infrastructure;

import builders.io.bank.wallet.application.WalletDetail;
import builders.io.bank.wallet.application.WalletNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class WalletGetController implements WalletGetEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(WalletGetController.class);
    private final WalletDetail walletDetail;
    public WalletGetController(WalletDetail walletDetail){
        this.walletDetail = walletDetail;
    }
    @Override
    public ResponseEntity getBalance(String address){
        try {
            logger.info("New info request for wallet {}", address);
            BigDecimal balance = walletDetail.getBalance(address);
            String response = "Wallet: " + address +" Balance: " + balance.toString();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (WalletNotExistException exception){
            logger.error(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}