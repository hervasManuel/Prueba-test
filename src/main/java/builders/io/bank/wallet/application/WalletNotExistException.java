package builders.io.bank.wallet.application;

public class WalletNotExistException extends RuntimeException {
    public WalletNotExistException(String address) {
        super(String.format("The wallet <%s> not exist", address));
    }
}
