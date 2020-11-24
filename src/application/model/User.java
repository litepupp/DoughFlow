package application.model;

import java.util.*;

import application.model.*;

public class User {
	
	private String name;
	private String username;
	private String password;
	private ArrayList<Event> Events;

	User(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
		Events = new ArrayList<Event>();
	}
}
