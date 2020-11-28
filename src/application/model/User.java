package application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class User {
	
	private String Name;
	private String Username;
	private String Password;
	private ObservableList<Event> Events;
	private ObservableSet<String> EventCategories;
	
	public void loadEvents(String path) {
		Path pathToEvents = Paths.get(path);
		
		try (BufferedReader br = Files.newBufferedReader(pathToEvents, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            
            while (line != null) {

            	String[] data = line.split(",");
                
                Event tempEvent = new Event();
                tempEvent.setEventName(data[0]);
                tempEvent.setIsExpense(Boolean.parseBoolean(data[1]));
                tempEvent.setCategory(data[2]);
                tempEvent.setAmount(Integer.parseInt(data[3]));
                tempEvent.setDateStart(LocalDate.parse(data[4]));
                tempEvent.setMonthsInterval(Integer.parseInt(data[5]));
                tempEvent.setWeeksInterval(Integer.parseInt(data[6]));
                tempEvent.setDaysInterval(Integer.parseInt(data[7]));
                
                
                if (this.Username.equals(data[8])) {
                	System.out.println(tempEvent.getEventName() + " added to: " + this.Username);
                	Events.add(tempEvent);
                }
                
                line = br.readLine();
            }

		} catch (IOException IOe) {
			IOe.printStackTrace();
        }
	}
	
	public void updateEventCategories() {
		for (Event i : this.Events) {
			this.EventCategories.add(i.getCategory());
		}
	}
	
	public void writeNewEventFile(String path) {
		
	}
	
	public void deleteEventInFile(String path, String name) {
		
	}
	
	public void removeEvent(String name) {
		for (Iterator<Event> it = this.Events.iterator(); it.hasNext();) {
			Event currentEvent = it.next();
			if (currentEvent.getEventName().equals(name)) {
				removeEventCategory(currentEvent.getCategory());
				it.remove();
			}
		}
	}
	
	public void removeEventCategory(String name) {
		for (String i : this.EventCategories) {
			if (i.equals(name)) {
				this.EventCategories.remove(name);
				System.out.println(name + " Cat REMOVED");
			}
		}
	}
	
	public void printEventCategories() {
		for (String j : this.EventCategories) {
			System.out.println("EventCAT: " + j);
		}
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
	
	public ObservableSet<String> getEventCategories() {
		return this.EventCategories;
	}

	User() {
		Events = FXCollections.observableArrayList();
		EventCategories = FXCollections.observableSet();
	}
}
