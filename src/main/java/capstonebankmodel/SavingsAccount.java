package capstonebankmodel;

public class SavingsAccount extends Account{

    public final String ACCOUNT_TYPE = "savings";
    public SavingsAccount(String username) {
        super(username);
    }

    public SavingsAccount(String username, long accountId, double balance) {
        super(username, accountId, balance);
    }
}
