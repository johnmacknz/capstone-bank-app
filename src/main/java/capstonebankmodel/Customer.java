package capstonebankmodel;

import java.util.HashMap;

public class Customer {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

    private HashMap<String, Long> accountTypeHashMap = new HashMap<String, Long>();

    public HashMap<String, Long> getLoanTypeHashMap() {
        return loanTypeHashMap;
    }

    private HashMap<String, Long> loanTypeHashMap = new HashMap<String, Long>();


    public Customer(String userName) {
        this.userName = userName;
        //TODO get all the data for customer
        //TODO throw exception if customer does not exist already
    }

    public Customer(String userName, String firstName, String lastName, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        //TODO check csv for username availability
    }

    public void addAccount(Account account) {
        accountTypeHashMap.put(account.ACCOUNT_TYPE, account.getAccountId());
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

    public String getPassword() {
        return password;
    }

    public HashMap<String, Long> getAccountTypeHashMap() {
        return accountTypeHashMap;
    }

    public void addLoan(Loan loan) {
        loanTypeHashMap.put(loan.loanType, loan.getLoanAccountId());
    }
}
