package capstonebankmodel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class Bank {

    private final HashMap<String, Customer> customerDataHashMap = new HashMap<>();
    private final HashMap<Long, Account> accountDataHashMap = new HashMap<>();
    private final HashMap<Long, Loan> loanDataHashMap = new HashMap<>();

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

    public void withdraw(@NotNull Account account, double amount) {
        account.withdraw(amount);
        CsvManager.writeCsvRow(account, account.getBalance(), CsvManager.ACCOUNT_DATA_FILE_PATH);
    }


    public void deposit(@NotNull Account account, double amount) {
        account.deposit(amount);
        CsvManager.writeCsvRow(account, account.getBalance(), CsvManager.ACCOUNT_DATA_FILE_PATH);
    }

    public void transfer(@NotNull Account sender, Account recipient, double amount) {
        sender.transferTo(amount, recipient);
        CsvManager.writeCsvRow(sender, sender.getBalance(), CsvManager.ACCOUNT_DATA_FILE_PATH);
        CsvManager.writeCsvRow(recipient, recipient.getBalance(), CsvManager.ACCOUNT_DATA_FILE_PATH);
    }

    public void addAccount(@NotNull Customer customer, String accountType) {
        // NEW ACCOUNT
        Account account = AccountFactory.generateAccount(accountType);
        accountDataHashMap.put(account.getAccountId(), account);
        CsvManager.addCsvRow(account, customer.getUsername(), CsvManager.ACCOUNT_DATA_FILE_PATH);
        customer.addAccount(account);
    }

    private void csvAddAccountRecord(@NotNull Account account, String customerName) {
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

    public void addAccount(@NotNull Customer customer, String accountType, long accountId, double balance) {
        // OLD ACCOUNT
        Account account = AccountFactory.generateAccount(accountType, accountId, balance);
        accountDataHashMap.put(account.getAccountId(), account);
        customer.addAccount(account);
    }

    public void initializeHashMaps() {
        try (Scanner fileScanner = new Scanner(new File("src/main/resources/data/customer-data.csv"))) {
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
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
                fileScanner.nextLine();
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
                fileScanner.nextLine();
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

    public void addNewLoan(@NotNull Customer customer, String loanType, double loanAmount, int loanDuration) {
        Loan loan = LoanFactory.generateLoan(loanType, loanAmount, loanDuration);
        loanDataHashMap.put(loan.getLoanId(), loan);
        CsvManager.addCsvRow(loan, customer.getUsername(), CsvManager.LOAN_DATA_FILE_PATH);
        customer.addLoan(loan);
    }

    public void addLoan(@NotNull Customer customer, long loanId, String loanType, double loanAmount,
                        double outstandingAmount, int loanDuration, LocalDate date) {
        Loan loan = LoanFactory.generateLoan(loanId, loanType, loanAmount, outstandingAmount, loanDuration, date);
        loanDataHashMap.put(loan.getLoanId(), loan);
        customer.addLoan(loan);
    }

    private void csvAddLoan(@NotNull Loan loan, String username) {
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
        customerDataHashMap.put(customer.getUsername(), customer);
        CsvManager.addCsvRow(customer, customer.getUsername(), CsvManager.CUSTOMER_DATA_FILE_PATH);
    }

    public void addCustomer(String username, String firstName, String lastName, String password) {
        // OLD CUSTOMER
        Customer customer = new Customer(username, firstName, lastName, password);
        customerDataHashMap.put(customer.getUsername(), customer);
    }

    private void csvAddCustomerRecord(@NotNull Customer customer) {
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

    private void csvEditAccountBalance(long accountNumber, double newBalance) {
        String tempFilePath = "src/main/resources/data/temporary-file.csv";
        File accountData = new File("src/main/resources/data/account-data.csv");
        File tempFile = new File(tempFilePath);
        try {
            FileWriter fw = new FileWriter(tempFilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner fileScanner = new Scanner(new File("src/main/resources/data/account-data.csv"));
            if (fileScanner.hasNextLine()) {
                String headerLine = fileScanner.nextLine();
                pw.println(headerLine);
            }
            while(fileScanner.hasNext()) {
                 String accountInfo = fileScanner.nextLine();
                String[] accountInfoArray = accountInfo.split(",");
                if (Long.parseLong(accountInfoArray[0]) == accountNumber) {
                    pw.println(accountInfoArray[0] + "," + accountInfoArray[1] + "," + accountInfoArray[2] + "," + newBalance);
                } else {
                    pw.println(accountInfoArray[0] + "," + accountInfoArray[1] + "," + accountInfoArray[2] + "," + accountInfoArray[3]);
                }
            }
            fileScanner.close();
            pw.flush();
            pw.close();


            boolean deleteResult = accountData.delete();


            File dump = new File("src/main/resources/data/account-data.csv");
            boolean renameResult =  tempFile.renameTo(dump);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void repayLoan(Customer customer, Account account, @NotNull Loan loan, double amount) {
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

    private void csvDeleteLoan(long loanId) {
        String tempFilePath = "src/main/resources/data/temporary-file.csv";
        File accountData = new File("src/main/resources/data/loan-data.csv");
        File tempFile = new File(tempFilePath);
        try {
            FileWriter fw = new FileWriter(tempFilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner fileScanner = new Scanner(new File("src/main/resources/data/loan-data.csv"));
            if (fileScanner.hasNextLine()) {
                String headerLine = fileScanner.nextLine();
                pw.println(headerLine);
            }
            while(fileScanner.hasNext()) {
                String loanInfo = fileScanner.nextLine();
                String[] loanInfoArray = loanInfo.split(",");
                if (Long.parseLong(loanInfoArray[0]) != loanId) {
                    pw.println(loanInfoArray[0] + "," + loanInfoArray[1] + "," + loanInfoArray[2] + "," + loanInfoArray[3]
                            + "," + loanInfoArray[4] + "," + loanInfoArray[5] + "," + loanInfoArray[6]);
                }
            }
            fileScanner.close();
            pw.flush();
            pw.close();
            accountData.delete();
            File dump = new File("src/main/resources/data/loan-data.csv");
            tempFile.renameTo(dump);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void csvEditLoanOutstandingAmount(long loanId, double newOutstandingAmount) {
        String tempFilePath = "src/main/resources/data/temporary-file.csv";
        File accountData = new File("src/main/resources/data/loan-data.csv");
        File tempFile = new File(tempFilePath);
        try {
            FileWriter fw = new FileWriter(tempFilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner fileScanner = new Scanner(new File("src/main/resources/data/loan-data.csv"));
            if (fileScanner.hasNextLine()) {
                String headerLine = fileScanner.nextLine();
                pw.println(headerLine);
            }
            while(fileScanner.hasNext()) {
                String loanInfo = fileScanner.nextLine();
                String[] loanInfoArray = loanInfo.split(",");
                if (Long.parseLong(loanInfoArray[0]) == loanId) {
                    pw.println(loanInfoArray[0] + "," + loanInfoArray[1] + "," + loanInfoArray[2] + "," + loanInfoArray[3]
                            + "," + newOutstandingAmount + "," + loanInfoArray[5] + "," + loanInfoArray[6]);
                } else {
                    pw.println(loanInfoArray[0] + "," + loanInfoArray[1] + "," + loanInfoArray[2] + "," + loanInfoArray[3]
                            + "," + loanInfoArray[4] + "," + loanInfoArray[5] + "," + loanInfoArray[6]);
                }
            }
            fileScanner.close();
            pw.flush();
            pw.close();
            accountData.delete();
            File dump = new File("src/main/resources/data/loan-data.csv");
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
