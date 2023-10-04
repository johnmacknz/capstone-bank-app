package capstonebankmodel;

public class CheckingAccount extends Account{
    public CheckingAccount(String username) {
        super(username);
        this.ACCOUNT_TYPE = "Checking Account";
    }

    public CheckingAccount(String username, long accountId, double balance) {
        super(username, accountId, balance);
        this.ACCOUNT_TYPE = "Checking Account";
    }
}
