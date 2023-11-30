package asuHelloWorldJavaFX;

public class Account {
		String Username;
		String Password;
		int accountID;
		Boolean isSup;

public Account() {
	
}
		
public Account(String user, String pass, boolean sup, int accID) {
	this.accountID = accID;
	this.isSup = sup;
	this.Password = pass;
	this.Username = user;
}
}