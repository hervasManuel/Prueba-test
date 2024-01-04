package builders.io.bank.transactions.application;

import builders.io.bank.shared.domain.UuidGenerator;
import builders.io.bank.transactions.domain.Transaction;
import builders.io.bank.transactions.domain.TransactionRepository;
import builders.io.bank.transactions.domain.TxnMethod;

import builders.io.bank.transactions.infrastructure.TransactionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

@Service
public final class TransactionSender {
    private static final Logger logger = LoggerFactory.getLogger(TransactionSender.class);
    private final EventBus eventBus;
    private final TransactionRepository repository;
    private final UuidGenerator uuidGenerator;

    public TransactionSender(EventBus eventBus, TransactionRepository repository, UuidGenerator uuidGenerator){
        this.eventBus = eventBus;
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
    }
    public void sendTransaction(TransactionRequest transactionRequest){
        logger.debug("Send transaction service");
        TxnMethod method = TxnMethod.valueOf(transactionRequest.method());
        Transaction transaction = Transaction.send(uuidGenerator.generate(),
                method,
                transactionRequest.from(),
                transactionRequest.to(),
                transactionRequest.value());
        repository.save(transaction);
        transaction.pullDomainEvents().forEach( domainEvent ->
               eventBus.notify("transactions_topic", Event.wrap(domainEvent)));
    }
}
