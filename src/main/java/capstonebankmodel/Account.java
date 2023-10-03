package capstonebankmodel;

public class Account {

    public final String ACCOUNT_TYPE = "default";

    private long accountId;

    private double balance;

    private String username;

    public long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public Account(String username) {
        this.username = username;
        // TODO generate ID
        balance = 0;
    }

    public Account(String username, long accountId, double balance) {
        this.username = username;
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
