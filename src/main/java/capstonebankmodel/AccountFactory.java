package capstonebankmodel;

public class AccountFactory {

    public static Account generateAccount(String accountType, Customer customer) {
        switch (accountType) {
            case "Savings Account" -> {
                return new SavingsAccount(customer.getUserName());
            }
            case "Checking Account" -> {
                return new CheckingAccount(customer.getUserName());
            }
            case "CD Account" -> {
                return new CDAccount(customer.getUserName());
            }
            default -> {
                return new Account(customer.getUserName());
            }
        }
    }

    public static Account generateAccount(String accountType, Customer customer, long accountId, double balance) {
        switch (accountType) {
            case "Savings Account" -> {
                return new SavingsAccount(customer.getUserName(), accountId, balance);
            }
            case "Checking Account" -> {
                return new CheckingAccount(customer.getUserName(), accountId, balance);
            }
            case "CD Account" -> {
                return new CDAccount(customer.getUserName(), accountId, balance);
            }
            default -> {
                return new Account(customer.getUserName(), accountId, balance);
            }
        }
    }
}
