package application.controller;


import application.model.Event;
import application.model.User;
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
	private ListView<Event> EventListView;
	@FXML
	private Label dateLabel;
	
	
	
	public void initializeUser(User user) {
		currentUser = user;
		initializeAll(currentUser);
	}
	
	public void initializeAll(User user) {
		
	}
	
	public void dateSet() {
		
	}
	
	public void returnToCalendar() {
		Stage deleteDayStage = (Stage)ReturnButton.getScene().getWindow();
		deleteDayStage.close();
	}
}
