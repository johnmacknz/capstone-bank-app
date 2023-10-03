package capstonebankmodel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Bank implements IBank {

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
        csvAddAccountRecord(account, customer.getUserName());
        customer.addAccount(account);
    }

    private void csvAddAccountRecord(Account account, String customerName) {
        String csvFilePath = "src/main/resources/data/account-data.csv";
        String[] recordToAdd = {String.valueOf(account.getAccountId()), customerName, account.ACCOUNT_TYPE, String.valueOf(account.getBalance())};

        try (Writer fileWriter = new FileWriter(csvFilePath, true);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
            //csvPrinter.println(); // Start a new line
            // Add a new record
            csvPrinter.printRecord((Object[]) recordToAdd);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAccount(Customer customer, String accountType, long accountId, double balance) {
        customerDataHashMap.put(customer.getUserName(), customer);
        Account account = AccountFactory.generateAccount(accountType, customer, accountId, balance);
        accountDataHashMap.put(account.getAccountId(), account);
        customer.addAccount(account);
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
        csvAddCustomerRecord(customer);
        deleteCustomer("test01");
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


    @Override
    public void deleteCustomer(String customerId) {
        customerDataHashMap.remove(customerId);
        // TODO delete customer from customer-data.csv
        File temporaryFile = createTemporaryCsvFile();
        File customerDataFile = new File("src/main/resources/data/customer-data.csv");
        csvDeleteCustomer(customerId);
        customerDataFile.delete();
        temporaryFile.renameTo(new File("src/main/resources/data/customer-data.csv"));
    }

    private File createTemporaryCsvFile(){
        return new File("src/main/resources/data/temporary-file.csv");
    }

    private void csvDeleteCustomer(String customerId) {
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/data/customer-data.csv"));
            String row;
            FileWriter myWriter = new FileWriter("src/main/resources/data/temporary-file.csv");

            while (((row = csvReader.readLine()) != null)){
                String[] line = row.split(",");
                if (!line[0].equals(customerId)){
                    myWriter.write(row + "\n");
                }
            }
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Customer> getCustomerDataHashMap() {
        return customerDataHashMap;
    }

    public HashMap<Long, Account> getAccountDataHashMap() {
        return accountDataHashMap;
    }
}
