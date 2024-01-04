package builders.io.bank.users.domain;

import builders.io.bank.shared.domain.WordMother;

public final class AuthUsernameMother {
    public static AuthUsername create(String value) {
        return new AuthUsername(value);
    }
    public static AuthUsername random() {
        return create(WordMother.random());
    }
}
