package capstonebankmodel;

public class PersonalLoan extends Loan{
    public PersonalLoan(String userName, double loanAmount, int loanDuration) {
        super(userName, loanAmount, loanDuration);
        loanType = "Personal Loan";
    }

    public PersonalLoan(String userName) {
        super(userName);
    }
}
