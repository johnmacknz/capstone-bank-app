package capstonebankmodel;

public class AccountFactory {

    public static Account generateAccount(String accountType) {
        switch (accountType) {
            case "Savings Account" -> {
                return new SavingsAccount();
            }
            case "Checking Account" -> {
                return new CheckingAccount();
            }
            case "CD Account" -> {
                return new CDAccount();
            }
            default -> {
                return new Account();
            }
        }
    }

    public static Account generateAccount(String accountType, long accountId, double balance) {
        switch (accountType) {
            case "Savings Account" -> {
                return new SavingsAccount(accountId, balance);
            }
            case "Checking Account" -> {
                return new CheckingAccount(accountId, balance);
            }
            case "CD Account" -> {
                return new CDAccount(accountId, balance);
            }
            default -> {
                return new Account(accountId, balance);
            }
        }
    }
}
