package capstonebankmodel;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Account {

    public String ACCOUNT_TYPE = "default";

    private final long accountId;

    private double balance;

    public long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public Account() {
        if (BankFactory.getBank().getAccountDataHashMap().keySet().isEmpty()) {
            accountId = 1000000000L;
        } else {
            accountId = Collections.max(BankFactory.getBank().getAccountDataHashMap().keySet()) + 1;
        }
        balance = 0;
    }

    public Account(long accountId, double balance) {
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
