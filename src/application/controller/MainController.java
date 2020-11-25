package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import application.model.*;

public class MainController {
	
	public Login currentLogin;
	public User currentUser;
	
	@FXML
	private Label WelcomeLabel;
	@FXML
	TextArea currentCycle;
	@FXML
	Button refreshButton;
	
	public void initializeUser(Login login, User user) throws IOException {
		this.currentLogin = login;
		this.currentUser = user;
		
		WelcomeLabel.setText("Welcome: " + currentUser.name + "/" + currentUser.username);
	}
	
	public void refreshCycle(ActionEvent actionEvent) {
		System.out.println("Refresh Button Pressed");
		
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
	
}
