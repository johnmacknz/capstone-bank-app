package capstonebankmodel;

import java.time.LocalDate;
import java.util.Collections;

import static capstonebankmodel.CsvManager.pw;

public class Loan implements CsvWritable, CsvAddable{

    private final double interestRate = 5.00; //TODO- for now let it be fixed
    private long loanId;
    private double loanAmount;
    private double outstandingAmount;
    private int loanDuration;
    private LocalDate startDate;
    public String loanType;

    public Loan(long loanId, double loanAmount, double outstandingAmount, int loanDuration, LocalDate startDate) {
        this.loanId=loanId;
        this.loanAmount=loanAmount;
        this.outstandingAmount=outstandingAmount;
        this.loanDuration=loanDuration;
        this.startDate=startDate;
    }

    public Loan(double loanAmount, int loanDuration){
        // for new loan account creation
        if (BankFactory.getBank().getLoanDataHashMap().keySet().isEmpty()) {
            loanId = 1000000000L;
        } else {
            loanId = Collections.max(BankFactory.getBank().getLoanDataHashMap().keySet()) + 1;
        }
        this.loanAmount = loanAmount;
        outstandingAmount = loanAmount;
        this.loanDuration = loanDuration;
        startDate = LocalDate.now();
    }

    public void calculateInterest(long loanAccountId){

        //bank.getLoanDataHashMap().keySet();
    }

    public void repayLoan(double amount){
        outstandingAmount -= amount;
    }

    public long getLoanId() {
        return loanId;
    }
    public double getLoanAmount() {
        return loanAmount;
    }
    public double getOutstandingAmount() {
        return outstandingAmount;
    }
    public int getLoanDuration() {
        return loanDuration;
    }
    public LocalDate getLoanDate() {
        return startDate;
    }

    @Override
    public void editCsvRow(double newAmount, String[] infoArray) {
        if (Long.parseLong(infoArray[0]) == loanId) {
            pw.println(infoArray[0] + "," + infoArray[1] + "," + infoArray[2] + "," + infoArray[3]
                    + "," + newAmount + "," + infoArray[5] + "," + infoArray[6]);
        } else {
            pw.println(infoArray[0] + "," + infoArray[1] + "," + infoArray[2] + "," + infoArray[3]
                    + "," + infoArray[4] + "," + infoArray[5] + "," + infoArray[6]);
        }
    }

    @Override
    public void deleteCsvRow(String[] infoArray) {
        if (Long.parseLong(infoArray[0]) != loanId) {
            pw.println(infoArray[0] + "," + infoArray[1] + "," + infoArray[2] + "," + infoArray[3]
                    + "," + infoArray[4] + "," + infoArray[5] + "," + infoArray[6]);
        }
    }

    @Override
    public String[] getRecordToAdd(String username) {
        return new String[]{String.valueOf(loanId), username, loanType, String.valueOf(loanAmount),
                String.valueOf(outstandingAmount), String.valueOf(loanDuration), String.valueOf(startDate)};
    }
}
