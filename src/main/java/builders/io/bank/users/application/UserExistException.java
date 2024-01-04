package builders.io.bank.users.application;

import builders.io.bank.users.domain.AuthUsername;

public class UserExistException extends RuntimeException {
    public UserExistException(AuthUsername username) {

        super(String.format("The user <%s> exist", username.value()));
    }
}
