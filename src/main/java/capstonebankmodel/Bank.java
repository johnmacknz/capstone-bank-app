package capstonebankmodel;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Bank {

    HashMap<String, Customer> customerDataHashMap = new HashMap<>();
    HashMap<Long, Account> accountDataHashMap = new HashMap<>();
    HashMap<Long, Loan> loanDataHashMap = new HashMap<>();

    public Bank() {
        initializeHashMaps();
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);
        csvEditAccountBalance(account.getAccountId(), account.getBalance());
    }


    public void deposit(Account account, double amount) {
        account.deposit(amount);
        csvEditAccountBalance(account.getAccountId(), account.getBalance());
    }

    public void transfer(Account sender, Account recipient, double amount) {
        sender.transferTo(amount, recipient);
        csvEditAccountBalance(sender.getAccountId(), sender.getBalance());
        csvEditAccountBalance(recipient.getAccountId(), recipient.getBalance());
    }

    public void addAccount(Customer customer, String accountType) {
        // NEW ACCOUNT
        Account account = AccountFactory.generateAccount(accountType);
        accountDataHashMap.put(account.getAccountId(), account);
        csvAddAccountRecord(account, customer.getUserName());
        customer.addAccount(account);
    }

    private void csvAddAccountRecord(Account account, String customerName) {
        String csvFilePath = "src/main/resources/data/account-data.csv";
        String[] recordToAdd = {String.valueOf(account.getAccountId()), customerName,
                account.ACCOUNT_TYPE, String.valueOf(account.getBalance())};
        try (Writer fileWriter = new FileWriter(csvFilePath, true);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord((Object[]) recordToAdd);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Customer customer, String accountType, long accountId, double balance) {
        // OLD ACCOUNT
        Account account = AccountFactory.generateAccount(accountType, accountId, balance);
        accountDataHashMap.put(account.getAccountId(), account);
        customer.addAccount(account);
    }

    public void deleteAccount(long accountId) {
        accountDataHashMap.remove(accountId);
        csvDeleteAccount(accountId);
    }

    public void initializeHashMaps() {
        try (Scanner fileScanner = new Scanner(new File("src/main/resources/data/customer-data.csv"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] customerDetails = line.split(",");
                addCustomer(customerDetails[0], customerDetails[1], customerDetails[2], customerDetails[3]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Scanner fileScanner = new Scanner(new File("src/main/resources/data/account-data.csv"))) {
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
        csvAddLoan(loan, customer.getUserName());
        customer.addLoan(loan);
    }

    public void addLoan(Customer customer, long loanId, String loanType, double loanAmount,
                        double outstandingAmount, int loanDuration, LocalDate date) {
        Loan loan = LoanFactory.generateLoan(loanId, loanType, loanAmount, outstandingAmount, loanDuration, date);
        loanDataHashMap.put(loan.getLoanId(), loan);
        customer.addLoan(loan);
    }

    private void csvAddLoan(Loan loan, String username) {
        String csvFilePath = "src/main/resources/data/loan-data.csv";
        String[] recordToAdd = {String.valueOf(loan.getLoanId()), username, loan.loanType,
                String.valueOf(loan.getLoanAmount()), String.valueOf(loan.getOutstandingAmount()),
                String.valueOf(loan.getLoanDuration()), String.valueOf(loan.getLoanDate())};

        try (Writer fileWriter = new FileWriter(csvFilePath, true);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord((Object[]) recordToAdd);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewCustomer(String username, String firstName, String lastName, String password) {
        // NEW CUSTOMER
        Customer customer = new Customer(username, firstName, lastName, password);
        customerDataHashMap.put(customer.getUserName(), customer);
        csvAddCustomerRecord(customer);
    }

    public void addCustomer(String username, String firstName, String lastName, String password) {
        // OLD CUSTOMER
        Customer customer = new Customer(username, firstName, lastName, password);
        customerDataHashMap.put(customer.getUserName(), customer);
    }

    private void csvAddCustomerRecord(Customer customer) {
        String csvFilePath = "src/main/resources/data/customer-data.csv";
        String[] recordToAdd = {customer.getUserName(), customer.getFirstName(), customer.getLastName(), customer.getPassword()};

        try (Writer fileWriter = new FileWriter(csvFilePath, true);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord((Object[]) recordToAdd);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void csvDeleteAccount(long accountNumber) {
        String tempFilePath = "src/main/resources/data/temporary-file.csv";
        File accountData = new File("src/main/resources/data/account-data.csv");
        File tempFile = new File(tempFilePath);
        try {
            FileWriter fw = new FileWriter(tempFilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner fileScanner = new Scanner(new File("src/main/resources/data/account-data.csv"));
            fileScanner.useDelimiter("[,\n]");
            while(fileScanner.hasNext()) {
                String accountId = fileScanner.next();
                String username = fileScanner.next();
                String accountType = fileScanner.next();
                String balance = fileScanner.next();
                if (Long.parseLong(accountId) != accountNumber) {
                    pw.print(accountId + "," + username + "," + accountType + "," + balance);
                }
            }
            fileScanner.close();
            pw.flush();
            pw.close();
            accountData.delete();
            File dump = new File("src/main/resources/data/account-data.csv");
            tempFile.renameTo(dump);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void csvEditAccountBalance(long accountNumber, double newBalance) {
        String tempFilePath = "src/main/resources/data/temporary-file.csv";
        File accountData = new File("src/main/resources/data/account-data.csv");
        File tempFile = new File(tempFilePath);
        try {
            FileWriter fw = new FileWriter(tempFilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner fileScanner = new Scanner(new File("src/main/resources/data/account-data.csv"));
            fileScanner.useDelimiter("[,\n]");
            while(fileScanner.hasNext()) {
                String accountId = fileScanner.next();
                String username = fileScanner.next();
                String accountType = fileScanner.next();
                String balance = fileScanner.next();
                if (Long.parseLong(accountId) == accountNumber) {
                    pw.println(accountId + "," + username + "," + accountType + "," + newBalance);
                } else {
                    pw.print(accountId + "," + username + "," + accountType + "," + balance);
                }
            }
            fileScanner.close();
            pw.flush();
            pw.close();
            accountData.delete();
            File dump = new File("src/main/resources/data/account-data.csv");
            tempFile.renameTo(dump);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
