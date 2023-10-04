package capstonebankmodel;

public class LoanFactory {

    public static Loan generateLoan(String loanType, double loanAmount, int loanDuration) {
        switch (loanType) {
            case "Personal Loan" -> {
                return new PersonalLoan(loanAmount, loanDuration);
            }
            case "Car Loan" -> {
                return new CarLoan(loanAmount, loanDuration);
            }
            case "Home Loan" -> {
                return new HomeLoan(loanAmount, loanDuration);
            }
            default -> {
                return new Loan(loanAmount, loanDuration); //TODO - need to changed to a default action
            }
        }
    }
}
