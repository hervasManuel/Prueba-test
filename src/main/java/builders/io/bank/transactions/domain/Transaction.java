package builders.io.bank.transactions.domain;

import builders.io.bank.shared.domain.AggregateRoot;
import builders.io.bank.shared.domain.transaction.TransactionSentDomainEvent;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public final class Transaction extends AggregateRoot {
    private final String transactionId;
    private final TxnMethod txnMethod;
    private final String from;
    private final String to;
    private final BigDecimal value;
    private final Timestamp timestamp;
    public Transaction(String transactionId,
                       TxnMethod txnMethod,
                       String from,
                       String to,
                       BigDecimal value,
                       Timestamp timestamp){
        this.transactionId = transactionId;
        this.txnMethod = txnMethod;
        this.timestamp = timestamp;
        this.from = from;
        this.to = to;
        this.value = value;
    }



    public static Transaction send(String transactionId,
                                   TxnMethod txnMethod,
                                   String from,
                                   String to,
                                   BigDecimal value){
        Timestamp timestamp = Timestamp.from(Instant.now());
        Transaction transaction = new Transaction(transactionId, txnMethod,from, to, value, timestamp);
        transaction.recordEvent(new TransactionSentDomainEvent(transactionId,
                                                          txnMethod.toString(),
                                                          from,
                                                          to,
                                                          value,
                                                          timestamp.toString()
        ));
        return transaction;
    }
    public String transactionId() {return transactionId;}
    public TxnMethod txnMethod() {return txnMethod;}
    public String from() {return from;}
    public String to() {return to;}
    public BigDecimal value() {return value;}
    public Timestamp timestamp() {return timestamp;}

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Transaction transaction = (Transaction) o;
        return  txnMethod.equals(transaction.txnMethod) &&
                from.equals(transaction.from) &&
                to.equals(transaction.to) &&
                value.equals(transaction.value) &&
                timestamp.equals(transaction.timestamp);
    }
    @Override
    public int hashCode() {return Objects.hash(txnMethod, from, to, value, timestamp);}
}
