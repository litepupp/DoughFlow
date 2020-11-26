package application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
	
	private String Name;
	private String Username;
	private String Password;
	private ObservableList<Event> Events;
	
	public void loadEvents(String path) {
		Path pathToEvents = Paths.get(path);
		
		try (BufferedReader br = Files.newBufferedReader(pathToEvents, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            
            while (line != null) {

            	String[] data = line.split(",");
                
                Event tempEvent = new Event();
                tempEvent.setEventName(data[0]);
                tempEvent.setType(data[1]);
                tempEvent.setCategory(data[2]);
                tempEvent.setAmount(Integer.parseInt(data[3]));
                tempEvent.setDateStart(data[4]);
                tempEvent.setMonthsInterval(Integer.parseInt(data[5]));
                tempEvent.setDaysInterval(Integer.parseInt(data[6]));
                
                
                if (this.Username.equals(data[7])) {
                	System.out.println(tempEvent.getEventName() + " added to: " + this.Username);
                	Events.add(tempEvent);
                }
                
                line = br.readLine();
            }

		} catch (IOException IOe) {
			IOe.printStackTrace();
        }
	}
	
	public void writeNewEventFile(String path) {
		
	}
	
	public void deleteEventFile(String path, String name) {
		
	}
	
	public String getName() {
		return this.Name;
	}
	
	public void setName(String name) {
		this.Name = name;
	}
	
	public String getUsername() {
		return this.Username;
	}
	
	public void setUsername(String username) {
		this.Username = username;
	}
	
	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}
	
	public ObservableList<Event> getEvents() {
		return this.Events;
	}

	User() {
		Events = FXCollections.observableArrayList();
	}
}
