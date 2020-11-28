package application.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import application.Main;
import application.model.*;

public class MainController {
	
	public static Login currentLogin;
	public static User currentUser;
	
	@FXML
	private Label WelcomeLabel;
	@FXML
	private Label currentCycle;
	@FXML
	private Button refreshButton;
	@FXML
	private Button logoutButton;
	@FXML
	private Button AddEventButton;
	@FXML
	private Button DeleteEventButton;
	@FXML
	private Button ViewCalendarButton;
	
	public void initializeAll(Login login, User user) throws IOException {
		currentLogin = login;
		currentUser = user;
		
		WelcomeLabel.setText("Welcome: " + currentUser.getName() + "/" + currentUser.getUsername());
		dateSet();
		
		currentUser.printEventCategories();
	}
	
	public void initializeUser(User user) throws IOException {
		currentUser = user;
		
		WelcomeLabel.setText("Welcome: " + currentUser.getName() + "/" + currentUser.getUsername());
		dateSet();
		
		currentUser.printEventCategories();
	}
	
	public void refreshPage(ActionEvent actionEvent) {
		System.out.println("Refresh Button Pressed");
		
		dateSet();
	}
	
	public void setAccordion() {
		
	}
	
	public void setPie() {
		
	}
	
	public void setStats() {
		
	}
	
	public void AddNewEvent(ActionEvent actionEvent) throws IOException {
		System.out.println("Add New Event Pressed");
		
		FXMLLoader addEventLoader = new FXMLLoader();
		addEventLoader.setLocation(Main.class.getResource("controller/AddEventView.fxml"));
		
		Parent addEventRoot = addEventLoader.load();
		Scene addEventScene = new Scene(addEventRoot);
		
		AddEventController addEventController = addEventLoader.getController();
		addEventController.initializeUser(currentUser);
		
		addEventController.CreateNewEventButton.disableProperty().bind(
			    Bindings.isEmpty(addEventController.EventNameTextField.textProperty())
			    .or(Bindings.isEmpty(addEventController.EventAmountTextField.textProperty()))
			    .or(Bindings.isNull(addEventController.StartingDatePicker.valueProperty()))
			    .or(Bindings.isNull(addEventController.EventCategoryChoiceBox.valueProperty()))
			);
		
		Stage addEventStage = new Stage();
		addEventStage.setScene(addEventScene);
		addEventStage.show();
	}
	
	public void DeleteEvent(ActionEvent event) throws IOException {
		System.out.println("Delete Event Pressed");
		
		FXMLLoader deleteEventLoader = new FXMLLoader();
		deleteEventLoader.setLocation(Main.class.getResource("controller/DeleteEventView.fxml"));
		
		Parent deleteEventRoot = deleteEventLoader.load();
		Scene deleteEventScene = new Scene(deleteEventRoot);
		
		DeleteEventController deleteEventController = deleteEventLoader.getController();
		deleteEventController.initializeUser(currentUser);
		
		Stage deleteEventStage = new Stage();
		deleteEventStage.setScene(deleteEventScene);
		deleteEventStage.show();
	}
	
	public void Calendar(ActionEvent event) throws IOException {
		System.out.println("View Calendar Pressed");
		
		FXMLLoader calendarLoader = new FXMLLoader();
		calendarLoader.setLocation(Main.class.getResource("controller/CalendarView.fxml"));
		
		Parent calendarRoot = calendarLoader.load();
		Scene calendarScene = new Scene(calendarRoot);
		
		CalendarController calendarController = calendarLoader.getController();
		calendarController.initializeUser(currentUser);
		
		Stage calendarStage = new Stage();
		calendarStage.setScene(calendarScene);
		calendarStage.show();
	}
	
	public void dateSet() {
		LocalDate date = LocalDate.now();
        int month = date.getMonthValue();

        date = date.withMonth(month);

        //start of month :
        LocalDate firstDay = date.withDayOfMonth(1);

        //end of month
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        
		currentCycle.setText("Current Cycle:\n" + firstDay + " > " + lastDay);
	}
	
	public void logout(ActionEvent event) throws IOException{
		System.out.println("Logging out: " + currentUser.getUsername());
		LoginController.currentLogin = currentLogin;
		
		FXMLLoader loginLoader = new FXMLLoader();
		loginLoader.setLocation(Main.class.getResource("controller/LoginView.fxml"));
		
		Parent loginRoot = loginLoader.load();
		Scene loginScene = new Scene(loginRoot);
		
		Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		loginStage.setScene(loginScene);
		loginStage.show();
	}
	
}
