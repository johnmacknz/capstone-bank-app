package capstonebankmodel;

public class CarLoan extends Loan{
    public CarLoan(String userName, double loanAmount, int loanDuration) {
        super(userName, loanAmount, loanDuration);
    }

    public CarLoan(String userName) {
        super(userName);
    }
}
