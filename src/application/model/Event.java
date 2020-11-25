package application.model;

public class Event {
	
	String eventName;
	String type;
	String category;
	int amount;
	String dateStart;
	int monthsInterval;
	int daysInterval;
	
	
	Event(String eventName, String type, String category, int amount, String dateStart, int monthsInterval, int daysInterval) {
		this.eventName = eventName;
		this.type = type;
		this.category = category;
		this.amount = amount;
		this.dateStart = dateStart;
		this.monthsInterval = monthsInterval;
		this.daysInterval = daysInterval;
	}
}
