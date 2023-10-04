package capstonebankmodel;

public class SavingsAccount extends Account{
    public SavingsAccount(String username) {
        super(username);
        this.ACCOUNT_TYPE = "Savings Account";
    }

    public SavingsAccount(String username, long accountId, double balance) {
        super(username, accountId, balance);
        this.ACCOUNT_TYPE = "Savings Account";
    }
}
