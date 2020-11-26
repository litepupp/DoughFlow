package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.EventObject;

import application.Main;
import application.model.*;

public class MainController {
	
	public static Login currentLogin;
	public static User currentUser;
	
	@FXML
	private static Label WelcomeLabel;
	@FXML
	private static Label currentCycle;
	@FXML
	private Button refreshButton;
	@FXML
	private Button logoutButton;
	@FXML
	private Button AddEventButton;
	@FXML
	private Button DeleteEventButton;
	
	public void initializeAll(Login login, User user) throws IOException {
		currentLogin = login;
		currentUser = user;
		
		WelcomeLabel.setText("Welcome: " + currentUser.name + "/" + currentUser.username);
		dateSet();
		
	}
	
	public static void initializeUser(User user) throws IOException {
		currentUser = user;
		
		WelcomeLabel.setText("Welcome: " + currentUser.name + "/" + currentUser.username);
		dateSet();
		
	}
	
	public void refreshCycle(ActionEvent actionEvent) {
		System.out.println("Refresh Button Pressed");
		dateSet();
	}
	
	public void setAccordion() {
		
	}
	
	public void AddNewEvent(ActionEvent actionEvent) {
		System.out.println("Add New Event Pressed");
		
		
	}
	
	public void DeleteEvent(ActionEvent actionEvent) {
		System.out.println("Delete Event Pressed");
		
		
	}
	
	public static void dateSet() {
		LocalDate date = LocalDate.now();
        int month = date.getMonthValue();

        date = date.withMonth(month);

        //start of month :
        LocalDate firstDay = date.withDayOfMonth(1);
        System.out.println("firstDay=" + firstDay);

        //end of month
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDay=" + lastDay);
        
		currentCycle.setText("Current Cycle:\n" + firstDay + " > " + lastDay);
	}
	
	public void logout(ActionEvent event) throws IOException{
		System.out.println("Logging out: " + currentUser.username);
		LoginController.currentLogin = currentLogin;
		
		FXMLLoader loginLoader = new FXMLLoader();
		loginLoader.setLocation(Main.class.getResource("controller/LoginView.fxml"));
		
		Parent loginRoot = loginLoader.load();
		Scene loginScene = new Scene(loginRoot);
		
		LoginController loginController = loginLoader.getController();
		
		Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		loginStage.setScene(loginScene);
		loginStage.show();
	}
	
}
