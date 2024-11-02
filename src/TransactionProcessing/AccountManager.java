package TransactionProcessing;

public class AccountManager {

    public boolean createAccount(String accountData) {
        // Simulated account creation
        return !accountData.isEmpty();
    }

    public boolean updateBalance(int accountId, double amount) {
        // Simulated balance update
        return accountId > 0;
    }
}