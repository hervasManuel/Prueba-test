package builders.io.bank.transactions.infrastructure;

import builders.io.bank.transactions.application.TransactionFinder;
import builders.io.bank.transactions.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TransactionGetController implements TransactionGetEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(TransactionGetController.class);
    private final TransactionFinder transactionFinder;
    public TransactionGetController(TransactionFinder transactionFinder) {
        this.transactionFinder = transactionFinder;
    }
    @Override
    public ResponseEntity getTransactionsByAddress(String address) {
        logger.info("New request: Get transactions of wallet: {}",address);
        var transactionList = transactionFinder.findByAddress(address)
                .stream()
                .map(TransactionGetController::toMap)
                .toList();
        return new ResponseEntity<>(transactionList, HttpStatus.OK);

    }
    private static HashMap<String, Object> toMap(Transaction transaction) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("txnMethod", transaction.txnMethod());
        hashMap.put("from", transaction.from());
        hashMap.put("to", transaction.to());
        hashMap.put("value", transaction.value());
        return hashMap;
    }

}
