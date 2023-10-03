package capstonebankmodel;

public class BankFactory {
    private static Bank bank = new Bank();
    public static Bank getBank() {
        return bank;
    }
}
