package capstonebankmodel;

public interface IBank {
    void withdraw(Account account, double amount);
    void deposit(Account account, double amount);
    void transfer(Account sender, Account recipient, double amount);
    void addAccount(Customer customer, String accountType);

    void addAccount(Customer customer, String accountType, long accountId, double balance);

    void deleteAccount(String accountId);
    void initializeHashMaps();
    void takeLoan(Account account, Loan loan);
    void addCustomer(String firstName, String lastName, String username, String password);
    void deleteCustomer(String customerId);
}
