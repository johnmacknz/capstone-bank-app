package capstonebankmodel;

public interface CsvWritable {
    void editCsvRow(double newAmount, String[] infoArray);
    void deleteCsvRow(String[] infoArray);
}
