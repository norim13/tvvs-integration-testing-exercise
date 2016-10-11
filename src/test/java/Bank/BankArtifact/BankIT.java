package Bank.BankArtifact;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Bank.BankArtifact.Bank;
import Bank.BankArtifact.Client;

public class BankIT {
	public final float floatTolerance = 0.0001f;
	private Bank bank;
	
	@Before
    public void setUp() {
		// this is a simple abstraction from what would be a database connection
		// testing several systems with one simple test
		
		bank = new Bank();
		Client carlos = new Client("Carlos");
		Client melo = new Client("Melo");
		Client rui = new Client("Rui");
		
		bank.addClient(carlos);	bank.addClient(melo); bank.addClient(rui);
    }
	

	@Test
	public void testDepositAmount() {
		// use the functions depositAccount(Client,float) & getClientByName(String) from Bank
		Client c = bank.getClientByName("Rui");
		bank.depositAccount(c, 12500f);
		assertEquals(c.getAccount().getAmount(),12500f, 0.00001f);		
	}
	
	@Test	
	public void testWithdrawAmount() {	
		Client c = bank.getClientByName("Melo");
		bank.depositAccount(c, 500f);
		assertEquals(c.getAccount().getAmount(),500f, 0.00001f);
		bank.withdrawClientAccount(c, 300f);
		assertEquals(c.getAccount().getAmount(),200f, 0.00001f);
	}
	
	@Test
	public void testTransactionBetweenUsers() {
		// use the functions transfer(Client,Client,float) & getClientByName(String) from Bank
		Client c1 = bank.getClientByName("Rui");
		bank.depositAccount(c1, 12500f);
		Client c2 = bank.getClientByName("Carlos");
		bank.depositAccount(c2, 500f);
		
		bank.transfer(c1, c2, 10000f);
		
		assertEquals(c1.getAccount().getAmount(), 2500f, 0.00001f);
		assertEquals(c2.getAccount().getAmount(), 10500f, 0.00001f);
	}

}
