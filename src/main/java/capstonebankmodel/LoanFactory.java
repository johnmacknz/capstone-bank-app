package capstonebankmodel;

public class LoanFactory {

    public static Loan generateLoan(String loanType, Loan loan) {
        switch (loanType) {
            case "Personal Loan" -> {
                return new PersonalLoan(loan.getUserName());
            }
            case "Car Loan" -> {
                return new CarLoan(loan.getUserName());
            }
            case "Home Loan" -> {
                return new HomeLoan(loan.getUserName());
            }
            default -> {
                return new Loan(loan.getUserName()); //TODO - need to changed to a default action
            }
        }
    }
}
