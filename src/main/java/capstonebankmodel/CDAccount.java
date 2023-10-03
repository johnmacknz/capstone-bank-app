package capstonebankmodel;

public class CDAccount extends Account{

    public final String ACCOUNT_TYPE = "cd";
    public CDAccount(String username) {
        super(username);
    }

    public CDAccount(String username, long accountId, double balance) {
        super(username, accountId, balance);
    }
}
