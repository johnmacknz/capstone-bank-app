package capstonebankmodel;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Bank{

    HashMap<String, Customer> customerDataHashMap = new HashMap<>();
    HashMap<Long, Account> accountDataHashMap = new HashMap<>();

    public Bank() {
        initializeHashMaps();
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);
    }

    public void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    public void transfer(Account sender, Account recipient, double amount) {
        sender.transferTo(amount, recipient);
    }

    public void addAccount(Customer customer, String accountType) {
        // NEW ACCOUNT
        Account account = AccountFactory.generateAccount(accountType, customer);
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
        Account account = AccountFactory.generateAccount(accountType, customer, accountId, balance);
        accountDataHashMap.put(account.getAccountId(), account);
        customer.addAccount(account);
    }

    public void deleteAccount(String accountId) {
        accountDataHashMap.remove(accountId);
        // TODO delete account to account-data.csv
    }

    public void initializeHashMaps() {
        try (Scanner fileScanner = new Scanner(new File("src/main/resources/data/customer-data.csv"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] customerDetails = line.split(",");
                addCustomer(customerDetails[0], customerDetails[1], customerDetails[2], customerDetails[3]);
            }
            System.out.println(customerDataHashMap);
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
    }

    public void takeLoan(Account account, Loan loan) {

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
