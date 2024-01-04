package builders.io.bank.virtualmachine.application;

import builders.io.bank.wallet.application.WalletUpdateBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class AddBalanceTrx implements TrxOperation {
    private static final Logger logger = LoggerFactory.getLogger(AddBalanceTrx.class);
    private final WalletUpdateBalance walletUpdateBalance;
    private final String to;
    private final BigDecimal value;

    public AddBalanceTrx(String to,
                         BigDecimal value,
                         WalletUpdateBalance walletUpdateBalance) {
        this.to = to;
        this.value=value;
        this.walletUpdateBalance = walletUpdateBalance;
    }
    @Override
    public void execute(){
        try{
            logger.info("Execution ADD_BALANCE transaction: Address: {} Value: {}",to ,value);
            walletUpdateBalance.addBalance(to,value);
        }
        catch (Exception e){
            logger.warn("Execution ADD_BALANCE transaction canceled!: {}",e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
