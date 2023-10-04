package capstonebankmodel;

public class CDAccount extends Account{
    public CDAccount(String username) {
        super(username);
        this.ACCOUNT_TYPE = "CD Account";
    }

    public CDAccount(String username, long accountId, double balance) {
        super(username, accountId, balance);
        this.ACCOUNT_TYPE = "CD Account";
    }
}
