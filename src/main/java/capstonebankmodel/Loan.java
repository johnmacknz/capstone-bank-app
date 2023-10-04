package capstonebankmodel;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class Loan {
    private String userName;
    private final double interestRate = 5.00; //TODO- for now let it be fixed
    private long loanAccountId;
    private double loanAmount;
    private double outstandingAmount;
    private int loanDuration;
    private LocalDate loanDate;
    public String loanType;
    private Bank bank;


    public Loan(String userName, double loanAmount, int loanDuration){ // for new loan account creation
        this.userName = userName;
        do {
           loanAccountId = ThreadLocalRandom.current().nextLong(1000000000L,9999999999L);
        } while (false);//TODO- add hashmap logic to check already created id
        this.loanAmount = loanAmount;
        outstandingAmount = loanAmount;
        this.loanDuration = loanDuration;
        loanDate = LocalDate.now();
    }

    public Loan(String userName) { //for existing loan accounts
        this.userName = userName;
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
    public String getUserName() {
        return userName;
    }
}
