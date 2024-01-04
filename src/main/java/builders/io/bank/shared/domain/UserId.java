package builders.io.bank.shared.domain;

public final class UserId extends Identifier {
    public UserId(String value) {
        super(value);
    }

    public static UserId create(String value) {
        return new UserId(value);
    }
}