package capstonebankmodel;

import java.util.HashMap;

public class Bank implements IBank{

    HashMap<String, Customer> customerDataHashMap = new HashMap<>();
    HashMap<Long, Account> accountDataHashMap = new HashMap<>();

    @Override
    public void withdraw(Account account, double amount) {
        account.withdraw(amount);
    }

    @Override
    public void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    @Override
    public void transfer(Account sender, Account recipient, double amount) {
        sender.transferTo(amount, recipient);
    }

    @Override
    public void addAccount(Customer customer, String accountType) {
        customerDataHashMap.put(customer.getUserName(), customer);
        Account account = AccountFactory.generateAccount(accountType, customer);
        accountDataHashMap.put(account.getAccountId(), account);
        // TODO add new account to account-data.csv
    }

    @Override
    public void addAccount(Customer customer, String accountType, long accountId, double balance) {
        customerDataHashMap.put(customer.getUserName(), customer);
        Account account = AccountFactory.generateAccount(accountType, customer, accountId, balance);
        accountDataHashMap.put(account.getAccountId(), account);
    }

    @Override
    public void deleteAccount(String accountId) {
        accountDataHashMap.remove(accountId);
        // TODO delete account to account-data.csv
    }

    @Override
    public void initializeAccounts() {

    }

    @Override
    public void takeLoan(Account account, Loan loan) {

    }

    @Override
    public void addCustomer(String firstName, String lastName, String username, String password) {
        Customer customer = new Customer(username, firstName, lastName, password);
        customerDataHashMap.put(customer.getUserName(), customer);
        // TODO add new customer data to customer-data.csv
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerDataHashMap.remove(customerId);
        // TODO delete customer from customer-data.csv
    }
    public HashMap<String, Customer> getCustomerDataHashMap() {
        return customerDataHashMap;
    }

    public HashMap<Long, Account> getAccountDataHashMap() {
        return accountDataHashMap;
    }
}
