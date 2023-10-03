package capstonebankmodel;

public class Account {

    private String accountId;

    private double balance;

    public Account(){
        // TODO generate ID
        balance = 0;
    }

    public Account(String accountId, double balance){
        this.accountId = accountId;
        this.balance = balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void transferTo(double amount, Account recipient) {
        this.balance -= amount;
        recipient.balance += amount;
    }
}
