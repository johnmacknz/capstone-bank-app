package capstonebankmodel;

public class SavingsAccount extends Account{
    public SavingsAccount() {
        super();
        this.ACCOUNT_TYPE = "Savings Account";
    }

    public SavingsAccount(long accountId, double balance) {
        super(accountId, balance);
        this.ACCOUNT_TYPE = "Savings Account";
    }
}
