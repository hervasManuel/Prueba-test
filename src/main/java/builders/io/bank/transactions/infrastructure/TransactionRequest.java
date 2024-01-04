package builders.io.bank.transactions.infrastructure;
import java.math.BigDecimal;


public final class TransactionRequest{
    private String method;
    private String from;
    private String to;
    private BigDecimal value;

    public String method() {return method;}
    public String from() {return from;}
    public String to() {return to;}
    public BigDecimal value() {return value;}

    public void setMethod(String method) {
        this.method = method;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
