package builders.io.bank.users.domain;

import builders.io.bank.shared.domain.StringValueObject;

public final class AuthUsername extends StringValueObject {
    public AuthUsername(String value) {
        super(value);
    }
    public static AuthUsername create(String value){
        return new AuthUsername(value);
    }
}
