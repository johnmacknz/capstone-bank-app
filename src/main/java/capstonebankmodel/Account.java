package capstonebankmodel;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Account {

    public String ACCOUNT_TYPE = "default";

    private long accountId;

    private double balance;

    private final String username;

    public long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public Account(String username) {
        this.username = username;
        if (BankFactory.getBank().getAccountDataHashMap().keySet().isEmpty()) {
            accountId = 1000000000L;
        } else {
            accountId = Collections.max(BankFactory.getBank().getAccountDataHashMap().keySet()) + 1;
        }
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
