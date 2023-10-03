package capstonebankmodel;

public class AccountFactory {

    public static Account generateAccount(String accountType, Customer customer) {
        switch (accountType) {
            case "savings" -> {
                return new SavingsAccount(customer.getUserName());
            }
            case "checking" -> {
                return new CheckingAccount(customer.getUserName());
            }
            case "cd" -> {
                return new CDAccount(customer.getUserName());
            }
            default -> {
                return new Account(customer.getUserName());
            }
        }
    }

    public static Account generateAccount(String accountType, Customer customer, long accountId, double balance) {
        switch (accountType) {
            case "savings" -> {
                return new SavingsAccount(customer.getUserName(), accountId, balance);
            }
            case "checking" -> {
                return new CheckingAccount(customer.getUserName(), accountId, balance);
            }
            case "cd" -> {
                return new CDAccount(customer.getUserName(), accountId, balance);
            }
            default -> {
                return new Account(customer.getUserName(), accountId, balance);
            }
        }
    }
}
