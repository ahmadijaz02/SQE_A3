package TransactionProcessing;

public class TransactionManager {

    public boolean processTransaction(int fromAccount, int toAccount, double amount) {
        // Simulated transaction logic
        return fromAccount > 0 && toAccount > 0 && amount > 0;
    }

    public boolean revertTransaction(int transactionId) {
        // Simulated revert logic
        return transactionId > 0;
    }
}