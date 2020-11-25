package application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import application.model.*;

public class User {
	
	public String name;
	public String username;
	private String password;
	private ArrayList<Event> Events;
	
	public void loadEvents(String path) {
		Path pathToEvents = Paths.get(path);
		
		try (BufferedReader br = Files.newBufferedReader(pathToEvents, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            
            while (line != null) {

            	String[] data = line.split(",");
                
                Event tempEvent = new Event(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]));
                
                if (this.username.equals(data[7])) {
                	System.out.println(tempEvent.eventName + " added to: " + this.username);
                	Events.add(tempEvent);
                }
                
                line = br.readLine();
            }

		} catch (IOException IOe) {
			IOe.printStackTrace();
        }
	}
	
	public void writeNewEvent(String path) {
		
	}

	User(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
		Events = new ArrayList<Event>();
	}
}
