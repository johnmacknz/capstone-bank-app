package capstonebankmodel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.Scanner;

public class CsvManager {

    public static final String ACCOUNT_DATA_FILE_PATH = "src/main/resources/data/account-data.csv";

    public static final String LOAN_DATA_FILE_PATH = "src/main/resources/data/loan-data.csv";

    public static final String CUSTOMER_DATA_FILE_PATH = "src/main/resources/data/customer-data.csv";

    private static final String TEMP_DATA_FILE_PATH = "src/main/resources/data/temporary-file.csv";
    private static FileWriter fw;
    private static BufferedWriter bw;
    public static PrintWriter pw;
    private static Scanner fileScanner;
    private static String headerLine;

    static void writeCsvRow(CsvWritable csvWritable, double newAmount, String filePath) {
        File data = new File(filePath);
        File tempFile = new File(TEMP_DATA_FILE_PATH);
        try {
            loadCsvFile(filePath, TEMP_DATA_FILE_PATH);
            while(fileScanner.hasNext()) {
                String info = fileScanner.nextLine();
                String[] infoArray = info.split(",");
                csvWritable.editCsvRow(newAmount, infoArray);
            }
            replaceOldCsvFile(data, filePath, tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void writeCsvRow(CsvWritable csvWritable, String filePath) {
        File data = new File(filePath);
        File tempFile = new File(TEMP_DATA_FILE_PATH);
        try {
            loadCsvFile(filePath, TEMP_DATA_FILE_PATH);
            while(fileScanner.hasNext()) {
                String info = fileScanner.nextLine();
                String[] infoArray = info.split(",");
                csvWritable.deleteCsvRow(infoArray);
            }
            replaceOldCsvFile(data, filePath, tempFile);
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

    static void addCsvRow(CsvAddable csvAddable, String username, String filePath) {
        String[] recordToAdd = csvAddable.getRecordToAdd(username);
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        editCsv(filePath, (Object[]) recordToAdd);
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
