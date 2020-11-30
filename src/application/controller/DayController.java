package application.controller;


import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
	
	public void initializeUser(User user, String eventDate) {
		currentUser = user;
		initializeAll(currentUser, eventDate);
	}
	
	public void initializeAll(User user, String eventDate) {
		try {
			ObservableList<String> listViewData = FXCollections.observableArrayList();
			String row, eventFormat;
			dateSet(eventDate);
			BufferedReader br = new BufferedReader(new FileReader("data/events.csv"));
			while((row = br.readLine()) != null) {
				String[] data = row.split(",");
				if(data[7].equals(currentUser.getUsername()) && data[4].equals(eventDate)){
					if(data[1].equals("false")) {
						eventFormat = "" + data[0] + " - $" + data[3] + " - Income";
					} else{
						eventFormat = "" + data[0] + " - $" + data[3] + " - Expense";
					}
					listViewData.add(eventFormat);
				}
			}
			EventListView.setItems(listViewData);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String dateSet(String eventDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(eventDate);
		String strDate = dtf.format(date);
		dateLabel.setText(strDate);
		return strDate;
	}
	
	public void returnToCalendar() {
		Stage deleteDayStage = (Stage)ReturnButton.getScene().getWindow();
		deleteDayStage.close();
	}
}
