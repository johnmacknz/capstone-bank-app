package capstonebankmodel;

import java.time.LocalDate;
import java.util.Collections;

public class Loan {

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
}
