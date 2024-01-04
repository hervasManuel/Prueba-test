package builders.io.bank.wallet.infrastructure;

import builders.io.bank.shared.domain.UserId;

import builders.io.bank.wallet.application.WalletCreation;
import builders.io.bank.wallet.application.WalletExistException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletPostController implements WalletPostEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(WalletPostController.class);
    private final WalletCreation walletCreation;
    public WalletPostController(WalletCreation walletCreation){
        this.walletCreation = walletCreation;
    }
    @Override
    public ResponseEntity<String> createWallet(@RequestBody RequestNewWallet requestNewWallet){
        try {
            String requestUserId = requestNewWallet.userId();
            logger.info("New Wallet request for a user {}", requestUserId);
            UserId id = UserId.create(requestNewWallet.userId());
            walletCreation.createWallet(id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (WalletExistException exception){
            logger.error(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        catch (IllegalArgumentException iaException){
            logger.error(iaException.getMessage());
            return new ResponseEntity<>(iaException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}

