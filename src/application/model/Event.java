package application.model;

public class Event {
	
	private String EventName;
	private String Type;
	private String Category;
	private int Amount;
	private String DateStart;
	private int MonthsInterval;
	private int WeeksInterval;
	private int DaysInterval;
	
	public String toString() {
		String choiceBoxView = "( " + this.EventName + " / " + this.Category + " )";
		return choiceBoxView;
	}
	
	public String getFullInfo() {
		String textAreaInfo = "Event Name:     " + this.EventName + "\n";
			  textAreaInfo += "Event Type:     " + this.Type + "\n";
			  textAreaInfo += "Event Category: " + this.Category + "\n";
			  textAreaInfo += "Amount:         " + this.Amount + "\n";
			  textAreaInfo += "Starting Date:  " + this.DateStart + "\n";
			  textAreaInfo += "Every " + this.MonthsInterval + " month(s)\n";
			  textAreaInfo += "Every " + this.WeeksInterval + "  week(s)\n";
			  textAreaInfo += "Every " + this.DaysInterval + "   day(s)\n";
		
		return textAreaInfo;
	}
	
	public String getEventName() {
		return this.EventName;
	}
	
	public void setEventName(String eventName) {
		this.EventName = eventName;
	}
	
	public String getType() {
		return this.Type;
	}
	
	public void setType(String type) {
		this.Type = type;
	}
	
	public String getCategory() {
		return this.Category;
	}
	
	public void setCategory(String category) {
		this.Category = category;
	}

	public int getAmount() {
		return this.Amount;
	}
	
	public void setAmount(int amount) {
		this.Amount = amount;
	}

	public String getDateStart() {
		return this.DateStart;
	}
	
	public void setDateStart(String dateStart) {
		this.DateStart = dateStart;
	}

	public int getMonthsInterval() {
		return this.MonthsInterval;
	}
	
	public void setMonthsInterval(int monthsInterval) {
		this.MonthsInterval = monthsInterval;
	}

	public int getWeeksInterval() {
		return WeeksInterval;
	}

	public void setWeeksInterval(int weeksInterval) {
		WeeksInterval = weeksInterval;
	}

	public int getDaysInterval() {
		return this.DaysInterval;
	}
	
	public void setDaysInterval(int daysInterval) {
		this.DaysInterval = daysInterval;
	}
	
	Event() {

	}
}
