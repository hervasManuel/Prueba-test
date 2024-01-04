package builders.io.bank.wallet.infrastructure;
public class RequestNewWallet {
    private String userId;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String userId() {
        return userId;
    }
}
