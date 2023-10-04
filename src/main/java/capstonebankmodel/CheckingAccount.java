package capstonebankmodel;

public class CheckingAccount extends Account{
    public CheckingAccount() {
        super();
        this.ACCOUNT_TYPE = "Checking Account";
    }

    public CheckingAccount(long accountId, double balance) {
        super(accountId, balance);
        this.ACCOUNT_TYPE = "Checking Account";
    }

    public void payBills(){

    }

}
