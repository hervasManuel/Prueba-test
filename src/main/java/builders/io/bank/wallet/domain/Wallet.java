package builders.io.bank.wallet.domain;

import builders.io.bank.shared.domain.AggregateRoot;
import builders.io.bank.shared.domain.UserId;
import builders.io.bank.shared.domain.wallet.WalletBalanceUpdatedDomainEvent;
import builders.io.bank.shared.domain.wallet.WalletCreatedDomainEvent;

import java.math.BigDecimal;
import java.util.Objects;

public final class Wallet extends AggregateRoot {
    private final String address;
    private final UserId userId;
    private final String publicKey;
    private final String privateKey;
    private BigDecimal balance;
    private final CoinType coinType;

    public Wallet(String address,
                  UserId userId,
                  String publicKey,
                  String privateKey,
                  BigDecimal balance,
                  CoinType coinType){
        this.address = address;
        this.userId = userId;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.balance = balance;
        this.coinType = coinType;
  }
    public static Wallet create(String address, UserId userId, String publicKey, String privateKey, CoinType coinType){
        BigDecimal initialBalance = new BigDecimal(0);
        Wallet wallet = new Wallet(address, userId, publicKey,privateKey,initialBalance, coinType);
        wallet.recordEvent(new WalletCreatedDomainEvent(address, address, userId.value(), coinType.toString()));
        return wallet;
    }

    public String address() { return address;}
    public UserId userId() {return userId;}
    public String publicKey() {return publicKey;}
    public String privateKey() {return privateKey;}
    public BigDecimal balance() {return balance;}
    public CoinType coinType() {return coinType;}

    public BigDecimal addBalance(BigDecimal amount) {
        BigDecimal newBalance = balance.add(amount);
        this.balance = newBalance;
        recordEvent(new WalletBalanceUpdatedDomainEvent(address, address, userId.value(), coinType.toString(), amount));
        return newBalance;
    }

    public BigDecimal subtractBalance(BigDecimal amount) {
        BigDecimal newBalance = balance.subtract(amount);
        this.balance = newBalance;
        recordEvent(new WalletBalanceUpdatedDomainEvent(address, address, userId.value(), coinType.toString(), amount.negate()));
        return newBalance;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Wallet wallet = (Wallet) o;
        return  address.equals(wallet.address) &&
                userId.equals(wallet.userId) &&
                publicKey.equals(wallet.publicKey) &&
                privateKey.equals(wallet.privateKey) &&
                balance.equals(wallet.balance) &&
                coinType.equals(wallet.coinType);
    }
    @Override
    public int hashCode() {return Objects.hash(address,userId, publicKey, privateKey, balance, coinType);}
}
