package capstonebankmodel;

import java.util.HashMap;

public class Customer {
    private String userName;
    private String firstName;
    private String lastName;
    private HashMap<String, Long> accountsMap = new HashMap<String, Long>();
    private String password;

    public Customer(String userName) {
        this.userName = userName;
        //TODO get all the data for customer
        //TODO call exception if customer didnot exist already
    }

    public Customer(String userName, String firstName, String lastName, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        //TODO check csv for username availability
    }

    public void populateAccountsMap(String accountType, Long accountId) {
        accountsMap.put(accountType, accountId);
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public HashMap<String, Long> getAccountsMap() {
        return accountsMap;
    }

    public String getPassword() {
        return password;
    }
}
