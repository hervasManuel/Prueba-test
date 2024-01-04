package builders.io.bank.wallet.application;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException(String address) {
        super(String.format("Not enough balance on wallet <%s> to execute transaction", address));
    }
}
