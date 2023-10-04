package capstonebankmodel;

public class HomeLoan extends Loan{
    public HomeLoan(String userName, double loanAmount, int loanDuration) {
        super(userName, loanAmount, loanDuration);
    }

    public HomeLoan(String userName) {
        super(userName);
    }
}
