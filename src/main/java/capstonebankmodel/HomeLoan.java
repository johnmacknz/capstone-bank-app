package capstonebankmodel;

public class HomeLoan extends Loan{
    public HomeLoan(double loanAmount, int loanDuration) {
        super(loanAmount, loanDuration);
        this.loanType = "Home Loan";
    }
}
