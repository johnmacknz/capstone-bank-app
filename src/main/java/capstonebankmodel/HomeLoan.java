package capstonebankmodel;

import java.time.LocalDate;

public class HomeLoan extends Loan{
    public HomeLoan(double loanAmount, int loanDuration) {
        super(loanAmount, loanDuration);
        this.loanType = "Home Loan";
    }

    public HomeLoan(long loanId, double loanAmount,
                        double outstandingAmount, int loanDuration, LocalDate date) {
        super(loanId, loanAmount, outstandingAmount, loanDuration, date);
        this.loanType = "Home Loan";
    }
}
