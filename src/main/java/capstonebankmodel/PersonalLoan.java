package capstonebankmodel;

public class PersonalLoan extends Loan{
    public PersonalLoan(double loanAmount, int loanDuration) {
        super(loanAmount, loanDuration);
        this.loanType = "Personal Loan";
    }
}
