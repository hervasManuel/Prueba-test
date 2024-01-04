package builders.io.bank.wallet.application;

import builders.io.bank.shared.domain.UserId;

public class WalletExistException extends RuntimeException {
    public WalletExistException(UserId id) {
        super(String.format("The user <%s> has a wallet previously created", id.value()));
    }
}
