package capstonebankmodel;

public class CheckingAccount extends Account{

    public final String ACCOUNT_TYPE = "checking";
    public CheckingAccount(String username) {
        super(username);
    }

    public CheckingAccount(String username, long accountId, double balance) {
        super(username, accountId, balance);
    }

    public void payBills(){

    }

}
