package capstonebankmodel;

import java.time.LocalDate;

public class CarLoan extends Loan{
    public CarLoan(double loanAmount, int loanDuration) {
        super(loanAmount, loanDuration);
        this.loanType = "Car Loan";
    }

    public CarLoan(long loanId, double loanAmount,
                        double outstandingAmount, int loanDuration, LocalDate date) {
        super(loanId, loanAmount, outstandingAmount, loanDuration, date);
        this.loanType = "Car Loan";
    }
}
