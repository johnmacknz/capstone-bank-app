package capstonebankmodel;

public class CDAccount extends Account{
    public CDAccount() {
        super();
        this.ACCOUNT_TYPE = "CD Account";
    }

    public CDAccount(long accountId, double balance) {
        super(accountId, balance);
        this.ACCOUNT_TYPE = "CD Account";
    }
}
