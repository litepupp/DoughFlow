package application.model;

import java.time.LocalDate;

public class Event {
	
	private String EventName;
	private boolean IsExpense;
	private String Category;
	private double Amount;
	private LocalDate DateStart;
	private int Interval;
	private String IntervalType;
	
	public String toString() {
		String choiceBoxView = "Name: " + this.EventName + " Category: " + this.Category;
		return choiceBoxView;
	}
	
	public String getFullInfo() {
		String textAreaInfo = "Event Name:     " + this.EventName + "\n";
		
		if (this.IsExpense == true) {
			  textAreaInfo += "Event Type:     Expense\n";
		}
		else {
			  textAreaInfo += "Event Type:     Income\n";
		}
			
			  textAreaInfo += "Event Category: " + this.Category + "\n";
			  textAreaInfo += "Amount:         $" + this.Amount + "\n";
			  textAreaInfo += "Starting Date:  " + this.DateStart + "\n";
			  textAreaInfo += "Every           " + this.Interval + " " + this.IntervalType + "(s)\n";
		
		return textAreaInfo;
	}
	
	/*Takes in calendar date pressed
	 * checks using DateStart and interval/type mean
	 * that event exists on onDate
	*/
	public boolean isOnDate(LocalDate onDate) {
		LocalDate temp = this.DateStart;
		if (onDate.isBefore(this.DateStart)) {
			return false;
		}
		else if (onDate.isEqual(this.DateStart)) {
			return true;
		}
		else {
			if (this.IntervalType.equals("Month")) {
				while(!(temp.isEqual(onDate))) {
					if (temp.isAfter(onDate)) {
						return false;
					}
					else {
						temp = temp.plusMonths(this.Interval);
					}
				}
				
				return true;
			}
			else if (this.IntervalType.equals("Week")) {
				while(!(temp.isEqual(onDate))) {
					if (temp.isAfter(onDate)) {
						return false;
					}
					else {
						temp = temp.plusWeeks(this.Interval);
					}
				}
				
				return true;
			}
			else {
				while(!(temp.isEqual(onDate))) {
					if (temp.isAfter(onDate)) {
						return false;
					}
					else {
						temp = temp.plusDays(this.Interval);
					}
				}
				
				return true;
			}
		}
	}
	
	public String getEventName() {
		return this.EventName;
	}
	
	public void setEventName(String eventName) {
		this.EventName = eventName;
	}
	
	public boolean getIsExpense() {
		return this.IsExpense;
	}
	
	public void setIsExpense(boolean isExpense) {
		this.IsExpense = isExpense;
	}
	
	public String getCategory() {
		return this.Category;
	}
	
	public void setCategory(String category) {
		this.Category = category;
	}

	public double getAmount() {
		return this.Amount;
	}
	
	public void setAmount(double amount) {
		this.Amount = amount;
	}

	public LocalDate getDateStart() {
		return this.DateStart;
	}
	
	public void setDateStart(LocalDate dateStart) {
		this.DateStart = dateStart;
	}

	public int getInterval() {
		return this.Interval;
	}
	
	public void setInterval(int interval) {
		this.Interval = interval;
	}

	public String getIntervalType() {
		return this.IntervalType;
	}

	public void setIntervalType(String intervalType) {
		this.IntervalType = intervalType;
	}

	Event() {

	}
}
