package builders.io.bank.transactions.infrastructure;


import builders.io.bank.transactions.application.TransactionSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TransactionPostController implements TransactionPostEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(TransactionPostController.class);
    private final TransactionSender transactionSender;
    public TransactionPostController(TransactionSender transactionSender){
        this.transactionSender = transactionSender;
    }
    @Override
    public ResponseEntity sendTransaction(TransactionRequest requestTransaction) {
        logger.info("New request: Send transaction");
        transactionSender.sendTransaction(requestTransaction);
        logger.info("Transaction sent correctly");
        return new ResponseEntity<>("Transaction sent correctly", HttpStatus.OK);

    }
}

