package builders.io.bank.transactions.infrastructure.persistence;

import builders.io.bank.transactions.domain.TxnMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ledger")
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private TxnMethod txnMethod;
    @Column(nullable = false)
    private String fromAddress;
    @Column(nullable = false)
    private String toAddress;
    @DecimalMin("0")
    @Column(precision = 20, scale = 8)
    private BigDecimal value;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;
    public TransactionEntity(String transactionId,
                             TxnMethod txnMethod,
                             String fromAddress,
                             String toAddress,
                             BigDecimal value,
                             Timestamp timestamp){
        this.transactionId = transactionId;
        this.txnMethod = txnMethod;
        this.timestamp = timestamp;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.value = value;
    }
    public TransactionEntity(){
    }

    public String transactionId() {return transactionId;}
    public TxnMethod txnMethod() {return txnMethod;}
    public String fromAddress() {return fromAddress;}
    public String toAddress() {return toAddress;}
    public BigDecimal value() {return value;}
    public Timestamp timestamp() {return timestamp;}
}

