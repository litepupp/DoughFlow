package application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Login {
	
	public HashMap<String, User> logins; //<Username, User data>
	
	public void loadLogins(String path) {
		Path pathToLogins = Paths.get(path);
		
		try (BufferedReader br = Files.newBufferedReader(pathToLogins, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            
            while (line != null) {

            	String[] data = line.split(",");
                
                User tempUser = new User();
                tempUser.setName(data[0]);
                tempUser.setUsername(data[1]);
                tempUser.setPassword(data[2]);
                
                tempUser.loadEvents("data/events.csv");
                tempUser.updateEventCategories();
                
                this.logins.put(data[1], tempUser);
                
                System.out.println("    " + data[0] + "/" + data[1] + " Loaded!");
                
                line = br.readLine();
            }

		} catch (IOException IOe) {
			IOe.printStackTrace();
        }
	}
	
	public boolean registerUser(String name, String username, String password) {
		User temp = new User();
		temp.setName(name);
		temp.setUsername(username);
		temp.setPassword(password);
		
		if (ifAlreadyExists(username) == false) {
			this.logins.put(username, temp);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean ifAlreadyExists(String username) {
		for (String i : this.logins.keySet()) {
			  if (i.equals(username)) {
				  return true;
			  }
		}
		return false;
	}
	
	public void writeNewUserFile(String path) {
		
	}
	
	public void removeUserFile(String path, String name) {
		
	}
	
	public boolean validateLogin(String username, String password) {
		for (String i : this.logins.keySet()) {
			  if (i.equals(username)) {
				  User tempUser = this.logins.get(i);
				  if (tempUser.getPassword().equals(password)) {
					  return true;
				  }
			  }
		}
		return false;
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
