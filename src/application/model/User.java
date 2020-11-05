package application.model;

import java.util.*;

public class User {
	private String username;
	private String password;
	private Dictionary Logins;
	
	private void RegisterUser(String username, String password) {
		this.Logins.put(username, password);
	}
	
	User() {
		Logins = new Hashtable();
	}
}
