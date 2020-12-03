package application.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.model.Event;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class DayController {

	public static User currentUser;
	
	@FXML
	private Button ReturnButton;
	@FXML
	private ListView<String> EventListView;
	@FXML
	private Label dateLabel;
	
	
	public void initializeAll(User user, LocalDate datePressed) {
		currentUser = user;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedString = datePressed.format(formatter);
		
		dateLabel.setText(formattedString);
		setEventsOnDate(datePressed);
	}
	
	public void setEventsOnDate(LocalDate datePressed) {

		ObservableList<String> listViewData = FXCollections.observableArrayList();
		String eventInfo;
		for (Event i : currentUser.getEvents()) {
			if (i.isOnDate(datePressed)) {
				if (i.getIsExpense()) {
					eventInfo = i.getEventName() + " - " + i.getCategory() + " - $" + i.getAmount() + " - Expense";
				}
				else {
					eventInfo = i.getEventName() + " - " + i.getCategory() + " - $" + i.getAmount() + " - Income";
				}
				listViewData.add(eventInfo);
			}
		}
		
		EventListView.setItems(listViewData);
	}
	
	
	public void returnToCalendar() {
		Stage deleteDayStage = (Stage)ReturnButton.getScene().getWindow();
		deleteDayStage.close();
	}
}
