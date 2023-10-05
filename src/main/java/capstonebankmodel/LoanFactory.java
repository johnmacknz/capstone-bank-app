package capstonebankmodel;

import java.time.LocalDate;

public class LoanFactory {

    public static Loan generateLoan(String loanType, double loanAmount, int loanDuration) {
        // NEW LOAN
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
                return new Loan(loanAmount, loanDuration);
            }
        }
    }

    public static Loan generateLoan(long loanId, String loanType, double loanAmount,
                                    double outstandingAmount, int loanDuration, LocalDate date) {
        // OLD LOAN
        switch (loanType) {
            case "Personal Loan" -> {
                return new PersonalLoan(loanId, loanAmount, outstandingAmount, loanDuration, date);
            }
            case "Car Loan" -> {
                return new CarLoan(loanId, loanAmount, outstandingAmount, loanDuration, date);
            }
            case "Home Loan" -> {
                return new HomeLoan(loanId, loanAmount, outstandingAmount, loanDuration, date);
            }
            default -> {
                return new Loan(loanId, loanAmount, outstandingAmount, loanDuration, date);
            }
        }
    }
}
