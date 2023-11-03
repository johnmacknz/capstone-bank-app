package capstonebankmodel;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class Bank {

    private HashMap<String, Customer> customerDataHashMap = new HashMap<>();
    private HashMap<Long, Account> accountDataHashMap = new HashMap<>();
    private HashMap<Long, Loan> loanDataHashMap = new HashMap<>();

    public double getTotalDeposits() {
        return totalDeposits;
    }

    private double totalDeposits;

    public double getTotalLoans() {
        return totalLoans;
    }

    private double totalLoans;

    public Bank() {
        initializeHashMaps();
        initializeTotalDeposits();
        initializeTotalLoans();
    }

    private void initializeTotalDeposits() {
        for (Account account : accountDataHashMap.values()) {
            totalDeposits += account.getBalance();
        }
    }

    private void initializeTotalLoans() {
        for (Loan loan : loanDataHashMap.values()) {
            totalLoans += loan.getOutstandingAmount();
        }
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);
        CsvManager.writeCsvRow(account, account.getBalance(), CsvManager.ACCOUNT_DATA_FILE_PATH);
    }


    public void deposit(Account account, double amount) {
        account.deposit(amount);
        CsvManager.writeCsvRow(account, account.getBalance(), CsvManager.ACCOUNT_DATA_FILE_PATH);
    }

    public void transfer(Account sender, Account recipient, double amount) {
        sender.transferTo(amount, recipient);
        CsvManager.writeCsvRow(sender, sender.getBalance(), CsvManager.ACCOUNT_DATA_FILE_PATH);
        CsvManager.writeCsvRow(recipient, recipient.getBalance(), CsvManager.ACCOUNT_DATA_FILE_PATH);
    }

    public void addAccount(Customer customer, String accountType) {
        // NEW ACCOUNT
        Account account = AccountFactory.generateAccount(accountType);
        accountDataHashMap.put(account.getAccountId(), account);
        CsvManager.addCsvRow(account, customer.getUsername(), CsvManager.ACCOUNT_DATA_FILE_PATH);
        customer.addAccount(account);
    }

    public void addAccount(Customer customer, String accountType, long accountId, double balance) {
        // OLD ACCOUNT
        Account account = AccountFactory.generateAccount(accountType, accountId, balance);
        accountDataHashMap.put(account.getAccountId(), account);
        customer.addAccount(account);
    }

    public void initializeHashMaps() {
        try (Scanner fileScanner = new Scanner(new File("src/main/resources/data/customer-data.csv"))) {
            if (fileScanner.hasNextLine()) {
                String headerLine = fileScanner.nextLine();
                //skip the header line
            }
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] customerDetails = line.split(",");
                addCustomer(customerDetails[0], customerDetails[1], customerDetails[2], customerDetails[3]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Scanner fileScanner = new Scanner(new File("src/main/resources/data/account-data.csv"))) {
            if (fileScanner.hasNextLine()) {
                String headerLine = fileScanner.nextLine();
                //skip the header line
            }
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] accountDetails = line.split(",");
                Customer customer = customerDataHashMap.get(accountDetails[1]);
                addAccount(customer, accountDetails[2],
                        Long.parseLong(accountDetails[0]), Double.parseDouble(accountDetails[3]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Scanner fileScanner = new Scanner(new File("src/main/resources/data/loan-data.csv"))) {
            if (fileScanner.hasNextLine()) {
                String headerLine = fileScanner.nextLine();
                //skip the header line
            }
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] loanDetails = line.split(",");
                Customer customer = customerDataHashMap.get(loanDetails[1]);
                addLoan(customer, Long.parseLong(loanDetails[0]), loanDetails[2], Double.parseDouble(loanDetails[3]),
                        Double.parseDouble(loanDetails[4]), Integer.parseInt(loanDetails[5]), LocalDate.parse(loanDetails[6]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewLoan(Customer customer, String loanType, double loanAmount, int loanDuration) {
        Loan loan = LoanFactory.generateLoan(loanType, loanAmount, loanDuration);
        loanDataHashMap.put(loan.getLoanId(), loan);
        CsvManager.addCsvRow(loan, customer.getUsername(), CsvManager.LOAN_DATA_FILE_PATH);
        customer.addLoan(loan);
    }

    public void addLoan(Customer customer, long loanId, String loanType, double loanAmount,
                        double outstandingAmount, int loanDuration, LocalDate date) {
        Loan loan = LoanFactory.generateLoan(loanId, loanType, loanAmount, outstandingAmount, loanDuration, date);
        loanDataHashMap.put(loan.getLoanId(), loan);
        customer.addLoan(loan);
    }

    public void addNewCustomer(String username, String firstName, String lastName, String password) {
        // NEW CUSTOMER
        Customer customer = new Customer(username, firstName, lastName, password);
        customerDataHashMap.put(customer.getUsername(), customer);
        CsvManager.addCsvRow(customer, customer.getUsername(), CsvManager.CUSTOMER_DATA_FILE_PATH);
    }

    public void addCustomer(String username, String firstName, String lastName, String password) {
        // OLD CUSTOMER
        Customer customer = new Customer(username, firstName, lastName, password);
        customerDataHashMap.put(customer.getUsername(), customer);
    }

    public void repayLoan(Customer customer, Account account, Loan loan, double amount) {
        withdraw(account, amount);
        loan.repayLoan(amount);
        if (loan.getOutstandingAmount() > 0) {
            CsvManager.writeCsvRow(loan, loan.getOutstandingAmount(), CsvManager.LOAN_DATA_FILE_PATH);
        } else if (loan.getOutstandingAmount() == 0) {
            customer.getLoanTypeHashMap().remove(loan.loanType);
            loanDataHashMap.remove(loan.getLoanId());
            CsvManager.writeCsvRow(loan, CsvManager.LOAN_DATA_FILE_PATH);
        } else {
            deposit(account, -loan.getOutstandingAmount());
            customer.getLoanTypeHashMap().remove(loan.loanType);
            loanDataHashMap.remove(loan.getLoanId());
            CsvManager.writeCsvRow(loan, CsvManager.LOAN_DATA_FILE_PATH);
        }

    }

    public HashMap<String, Customer> getCustomerDataHashMap() {
        return customerDataHashMap;
    }

    public HashMap<Long, Account> getAccountDataHashMap() {
        return accountDataHashMap;
    }

    public HashMap<Long, Loan> getLoanDataHashMap() {
        return loanDataHashMap;
    }
}
