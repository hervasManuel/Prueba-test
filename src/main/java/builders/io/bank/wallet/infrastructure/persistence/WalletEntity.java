package builders.io.bank.wallet.infrastructure.persistence;

import builders.io.bank.wallet.domain.CoinType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
public class WalletEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String address;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String publicKey;
    @Column(nullable = false)
    private String privateKey;
    @DecimalMin("0")
    @Column(precision = 20, scale = 8)
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private CoinType coinType;

    public WalletEntity(String address,
                        String userId,
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

    public WalletEntity(){
        this.userId = null;
        this.address = null;
        this.publicKey = null;
        this.privateKey = null;
        this.balance = null;
        this.coinType = null;
    }
    public String address() {
        return address;
    }
    public String userId() {
        return userId;
    }
    public String publicKey() {
        return publicKey;
    }
    public String privateKey() {
        return privateKey;
    }
    public BigDecimal balance() {
        return balance;
    }
    public CoinType coinType() {
        return coinType;
    }
}


