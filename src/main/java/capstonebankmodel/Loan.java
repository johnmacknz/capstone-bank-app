package capstonebankmodel;

import java.time.LocalDate;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Loan {

    private final double interestRate = 5.00; //TODO- for now let it be fixed
    private long loanAccountId;
    private double loanAmount;
    private double outstandingAmount;
    private int loanDuration;
    private LocalDate loanDate;
    public String loanType;


    public Loan(double loanAmount, int loanDuration){
        // for new loan account creation
        if (BankFactory.getBank().getLoanDataHashMap().keySet().isEmpty()) {
            loanAccountId = 1000000000L;
        } else {
            loanAccountId = Collections.max(BankFactory.getBank().getLoanDataHashMap().keySet()) + 1;
        }
        this.loanAmount = loanAmount;
        outstandingAmount = loanAmount;
        this.loanDuration = loanDuration;
        loanDate = LocalDate.now();
    }

    public void calculateInterest(long loanAccountId){

        //bank.getLoanDataHashMap().keySet();
    }

    public void repayLoan(){

    }

    public long getLoanAccountId() {
        return loanAccountId;
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
        return loanDate;
    }
}
