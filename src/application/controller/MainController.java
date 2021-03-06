package application.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Accordion;

import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextArea;
import com.jfoenix.controls.JFXButton;

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
	private JFXButton refreshButton;
	@FXML
	private JFXButton logoutButton;
	@FXML
	private JFXButton AddEventButton;
	@FXML
	private JFXButton DeleteEventButton;
	@FXML
	private JFXButton ViewCalendarButton;
	@FXML
	private PieChart PieChart;
	@FXML
	private TextArea StatsTextArea;
	@FXML
	private Accordion EventAccordion;

	
	public void initializeAll(Login login, User user) throws IOException {
		currentLogin = login;
		currentUser = user;
		currentUser.updateEventCategories();
		
		WelcomeLabel.setText(currentUser.getName());
		dateSet();
		setPie();
		setAccordion();
		setStats();
	}
	
	public void initializeUser(User user) throws IOException {
		currentUser = user;
		currentUser.updateEventCategories();
		
		WelcomeLabel.setText(currentUser.getName());
		dateSet();
		setPie();
		setAccordion();
		setStats();
	}
	
	public void refreshPage(ActionEvent actionEvent) {
		System.out.println("Refresh Button Pressed");
		currentUser.updateEventCategories();
		
		dateSet();
		setPie();
		setAccordion();
		setStats();
	}
	
	public void setAccordion() {
		
	}
	
	public void setPie() {
		 
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
		
		for (String i : currentUser.getEventCategories()) {
			
			for (Event j : currentUser.getEvents()) {
				
				if (i.equals(j.getCategory())) {
					
					if (j.getIsExpense() == true) {
						pieChartData.add(new PieChart.Data(j.getEventName(), j.getAmount()));
					}
				}
			}
		}
		
		PieChart.setData(pieChartData);
		PieChart.setTitle("Expenses");
		
		pieChartData.forEach(data ->
        data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), ": $", data.pieValueProperty()
                )));
		
	}
	
	public void setStats() {
		String stats = "Total Events:   " + currentUser.getEventCategories().size() + "\n";
		      stats += "Num Expenses:   " + currentUser.getNumExpense() + "\n";
		      stats += "Num Incomes:    " + currentUser.getNumIncome() + "\n";
		      stats += "--------------------------------\n";
		      stats += "Total Income:   " + currentUser.getTotalIncome() + "\n";
		      stats += "Total Expenses: " + currentUser.getTotalExpense() + "\n";
		      stats += "Total BALANCE:  " + currentUser.getBalance() + "\n";
		
		StatsTextArea.setText(stats);
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
			    .or(Bindings.isEmpty(addEventController.TimeIntervalTextField.textProperty()))
			);
		
		addEventController.AddEventCategoryButton.disableProperty().bind(
			    Bindings.isEmpty(addEventController.NewEventCategoryTextField.textProperty())
			);
		
		//This causes the return button to consistently create new home pages
		Stage addEventStage = new Stage();
		//This Stage declaration fixes that
		//Stage addEventStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
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
		//Same as above with AddNewEvent()
		//Stage deleteEventStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		deleteEventStage.setScene(deleteEventScene);
		deleteEventStage.show();
	}
	
	public void CalendarPress(ActionEvent event) throws IOException {
		System.out.println("View Calendar Pressed");
		
		FXMLLoader calendarLoader = new FXMLLoader();
		calendarLoader.setLocation(Main.class.getResource("controller/CalendarView.fxml"));
		
		Parent calendarRoot = calendarLoader.load();
		Scene calendarScene = new Scene(calendarRoot);
		
		CalendarController calendarController = calendarLoader.getController();
		calendarController.initializeUser(currentUser);
		
		//Stage calendarStage = new Stage();
		//Same as above with AddNewEvent() and DeleteEvent()
		Stage calendarStage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
		
		LoginController loginController = loginLoader.getController();
		
		loginController.RegisterButton.disableProperty().bind(
			    Bindings.isEmpty(loginController.NameField.textProperty())
			    .or(Bindings.isEmpty(loginController.UsernameField.textProperty()))
			    .or(Bindings.isEmpty(loginController.PasswordField.textProperty()))
			);
		
		loginController.LoginButton.disableProperty().bind(
			    Bindings.isEmpty(loginController.UsernameField.textProperty())
			    .or(Bindings.isEmpty(loginController.PasswordField.textProperty()))
			);
		
		Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		loginStage.setScene(loginScene);
		loginStage.show();
	}
	
}