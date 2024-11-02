package test;

//TransactionManagerTests.java
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TransactionProcessing.AccountManager;
import TransactionProcessing.TransactionManager;

public class TransactionManagerTests {

	TransactionManager tm = new TransactionManager();

	@DataProvider(name = "transactionData")
	public Object[][] transactionData() {
		return new Object[][] { { 1, 2, 100.0, true }, { 1, 0, -50.0, false } };
	}

	@Test(dataProvider = "transactionData")
	public void testProcessTransaction(int from, int to, double amount, boolean expected) {
		Assert.assertEquals(tm.processTransaction(from, to, amount), expected);
	}

	@Test
	public void testRevertTransaction() {
		Assert.assertTrue(tm.revertTransaction(123));
	}
}

class AccountManagerTests {

	AccountManager am = new AccountManager();

	@Test
	public void testCreateAccount() {
		Assert.assertTrue(am.createAccount("New Account"));
	}

	@Test
	public void testUpdateBalance() {
		Assert.assertTrue(am.updateBalance(1, 50.0));
	}
}
