package builders.io.bank.virtualmachine.application;

import builders.io.bank.wallet.application.WalletUpdateBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
public class TransferTrx implements TrxOperation {
    private static final Logger logger = LoggerFactory.getLogger(TransferTrx.class);
    private final WalletUpdateBalance walletUpdateBalance;

    private final String from;
    private final String to;
    private final BigDecimal value;

    public TransferTrx(String from,
                       String to,
                       BigDecimal value,
                       WalletUpdateBalance walletUpdateBalance){
        this.from = from;
        this.to = to;
        this.value = value;
        this.walletUpdateBalance = walletUpdateBalance;
    }
    @Override
    public void execute(){
        try {
            logger.info("Execution TRANSFER transaction -> From Address: {} Value: {}",from, value.negate());
            logger.info("                               -> To   Address: {} Value: {}",to, value);
            walletUpdateBalance.subtractBalance(from, value);
            walletUpdateBalance.addBalance(to, value);
            //save
        }
        catch (Exception e){
            logger.warn("Transaction execution TRANSFER canceled!: {}", e.getMessage());
        }
    }
}
