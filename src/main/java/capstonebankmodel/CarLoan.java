package capstonebankmodel;

public class CarLoan extends Loan{
    public CarLoan(double loanAmount, int loanDuration) {
        super(loanAmount, loanDuration);
        this.loanType = "Car Loan";
    }
}
