package application.model;

import java.util.ArrayList;
import java.util.HashMap;

import application.model.*;

public class Login {
	
	public HashMap<String, User> logins;
	
	public void loadLogins(String path) {
		
	}
	
	public boolean registerUser(String name, String username, String password) {
		User temp = new User(name, username, password);
		
		if (ifAlreadyExists(username, temp) == false) {
			System.out.println(name + " does not exist");
			this.logins.put(name, temp);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean ifAlreadyExists(String username, User temp) {
		for (String i : this.logins.keySet()) {
			  if (i.equals(username)) {
				  return true;
			  }
		}
		return false;
	}
	
	public void writeNewUser(String path) {
		
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
