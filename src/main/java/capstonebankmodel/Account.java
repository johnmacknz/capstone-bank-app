package capstonebankmodel;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import static capstonebankmodel.CsvManager.pw;

public class Account implements CsvWritable, CsvAddable{

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

    @Override
    public void editCsvRow(double newAmount, String[] infoArray) {
        if (Long.parseLong(infoArray[0]) == accountId) {
            pw.println(infoArray[0] + "," + infoArray[1] + "," + infoArray[2] + "," + newAmount);
        } else {
            pw.println(infoArray[0] + "," + infoArray[1] + "," + infoArray[2] + "," + infoArray[3]);
        }
    }

    @Override
    public void deleteCsvRow(String[] infoArray) {
        if (Long.parseLong(infoArray[0]) != accountId) {
            pw.println(infoArray[0] + "," + infoArray[1] + "," + infoArray[2] + "," + infoArray[3]);
        }
    }

    @Override
    public String[] getRecordToAdd(String username) {
        return new String[]{String.valueOf(accountId), username, ACCOUNT_TYPE, String.valueOf(balance)};
    }
}
