package capstonebankmodel;

import java.time.LocalDate;

public class PersonalLoan extends Loan{
    public PersonalLoan(double loanAmount, int loanDuration) {
        super(loanAmount, loanDuration);
        this.loanType = "Personal Loan";
    }

    public PersonalLoan(long loanId, double loanAmount,
                 double outstandingAmount, int loanDuration, LocalDate date) {
        super(loanId, loanAmount, outstandingAmount, loanDuration, date);
        this.loanType = "Personal Loan";
    }
}
