package capstonebankmodel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.Scanner;

public class CsvManager {

    private static final String ACCOUNT_DATA_FILE_PATH = "src/main/resources/data/account-data.csv";

    private static final String LOAN_DATA_FILE_PATH = "src/main/resources/data/loan-data.csv";

    private static final String TEMP_DATA_FILE_PATH = "src/main/resources/data/temporary-file.csv";
    private static FileWriter fw;
    private static BufferedWriter bw;
    private static PrintWriter pw;
    private static Scanner fileScanner;
    private static String headerLine;

    static void csvEditLoanOutstandingAmount(long loanId, double newOutstandingAmount) {
        File data = new File(LOAN_DATA_FILE_PATH);
        File tempFile = new File(TEMP_DATA_FILE_PATH);
        try {
            loadCsvFile(LOAN_DATA_FILE_PATH, TEMP_DATA_FILE_PATH);
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
            replaceOldCsvFile(data, LOAN_DATA_FILE_PATH, tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void csvDeleteLoan(long loanId) {
        File data = new File(LOAN_DATA_FILE_PATH);
        File tempFile = new File(TEMP_DATA_FILE_PATH);
        try {
            loadCsvFile(LOAN_DATA_FILE_PATH, TEMP_DATA_FILE_PATH);
            while(fileScanner.hasNext()) {
                String loanInfo = fileScanner.nextLine();
                String[] loanInfoArray = loanInfo.split(",");
                if (Long.parseLong(loanInfoArray[0]) != loanId) {
                    pw.println(loanInfoArray[0] + "," + loanInfoArray[1] + "," + loanInfoArray[2] + "," + loanInfoArray[3]
                            + "," + loanInfoArray[4] + "," + loanInfoArray[5] + "," + loanInfoArray[6]);
                }
            }
            replaceOldCsvFile(data, LOAN_DATA_FILE_PATH, tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void csvEditAccountBalance(long accountNumber, double newBalance) {
        File data = new File(ACCOUNT_DATA_FILE_PATH);
        File tempFile = new File(TEMP_DATA_FILE_PATH);
        try {
            loadCsvFile(ACCOUNT_DATA_FILE_PATH, TEMP_DATA_FILE_PATH);
            while(fileScanner.hasNext()) {
                 String accountInfo = fileScanner.nextLine();
                String[] accountInfoArray = accountInfo.split(",");
                if (Long.parseLong(accountInfoArray[0]) == accountNumber) {
                    pw.println(accountInfoArray[0] + "," + accountInfoArray[1] + "," + accountInfoArray[2] + "," + newBalance);
                } else {
                    pw.println(accountInfoArray[0] + "," + accountInfoArray[1] + "," + accountInfoArray[2] + "," + accountInfoArray[3]);
                }
            }
            replaceOldCsvFile(data, ACCOUNT_DATA_FILE_PATH, tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void replaceOldCsvFile(File data, String dataFilePath, File tempFile) {
        fileScanner.close();
        pw.flush();
        pw.close();
        data.delete();
        File dump = new File(dataFilePath);
        tempFile.renameTo(dump);
    }

    private static void loadCsvFile(String filePath, String tempFilePath) throws IOException {
        fw = new FileWriter(tempFilePath, true);
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw);
        fileScanner = new Scanner(new File(filePath));
        if (fileScanner.hasNextLine()) {
            headerLine = fileScanner.nextLine();
            pw.println(headerLine);
        }
    }

    static void csvAddCustomerRecord(Customer customer) {
        String csvFilePath = "src/main/resources/data/customer-data.csv";
        String[] recordToAdd = {customer.getUserName(), customer.getFirstName(), customer.getLastName(), customer.getPassword()};
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        editCsv(csvFilePath, (Object[]) recordToAdd);
    }

    static void csvAddLoan(Loan loan, String username) {
        String csvFilePath = "src/main/resources/data/loan-data.csv";
        String[] recordToAdd = {String.valueOf(loan.getLoanId()), username, loan.loanType,
                String.valueOf(loan.getLoanAmount()), String.valueOf(loan.getOutstandingAmount()),
                String.valueOf(loan.getLoanDuration()), String.valueOf(loan.getLoanDate())};
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        editCsv(csvFilePath, (Object[]) recordToAdd);
    }

    static void csvAddAccountRecord(Account account, String customerName) {
        String csvFilePath = "src/main/resources/data/account-data.csv";
        String[] recordToAdd = {String.valueOf(account.getAccountId()), customerName,
                account.ACCOUNT_TYPE, String.valueOf(account.getBalance())};
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        editCsv(csvFilePath, (Object[]) recordToAdd);
    }

    private static void editCsv(String csvFilePath, Object[] recordToAdd) {
        try (Writer fileWriter = new FileWriter(csvFilePath, true);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(recordToAdd);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
