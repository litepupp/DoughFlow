package application.model;

import java.util.ArrayList;
import java.util.HashMap;

import application.model.*;

public class Login {
	
	public HashMap<String, User> logins;
	
	public boolean registerUser(String name, String username, String password) {
		User temp = new User(name, username, password);
		
		if (ifUserExists(name, temp) == false) {
			this.logins.put(name, temp);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean ifUserExists(String name, User temp) {
		for (String i : this.logins.keySet()) {
			  if (i.equals(name)) {
				  return true;
			  }
		}
		return false;
	}
	
	public void loadLogins(String path) {
		
	}
	
	private void writeNewUser(String path) {
		
	}
	
	public void printAllUsers() {
		for (String i : this.logins.keySet()) {
			  System.out.println("Name: " + i + " DATA: " + this.logins.get(i));
			}
	}
	
	public Login() {
		logins = new HashMap<String, User>();
	}
}
