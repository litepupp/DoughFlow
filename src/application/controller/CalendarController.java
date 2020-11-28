package application.controller;

import java.io.IOException;

import application.Main;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CalendarController {
	
	public static User currentUser;
	
	@FXML
	private Button NextMonthButton;
	@FXML
	private Button PrevMonthButton;
	@FXML
	private Button ReturnHomeButton;
	@FXML
	private Button DayButton1, DayButton2, DayButton3, DayButton4, DayButton5, DayButton6, DayButton7,
	DayButton8, DayButton9, DayButton10, DayButton11, DayButton12, DayButton13, DayButton14, DayButton15,
	DayButton16, DayButton17, DayButton18, DayButton19, DayButton20, DayButton21, DayButton22, DayButton23,
	DayButton24, DayButton25, DayButton26, DayButton27, DayButton28, DayButton29, DayButton30, DayButton31,
	DayButton32, DayButton33, DayButton34, DayButton35;
	
	public void initializeUser(User user) {
		currentUser = user;
		
	}
	
	public void viewDay(ActionEvent event) throws IOException{
		//(Button)event.getSource();
		
		FXMLLoader dayLoader = new FXMLLoader();
		dayLoader.setLocation(Main.class.getResource("controller/DayView.fxml"));
		
		Parent dayRoot = dayLoader.load();
		Scene dayScene = new Scene(dayRoot);
		
		DayController dayController = dayLoader.getController();
		dayController.initializeUser(currentUser);
		
		Stage dayStage = new Stage();
		dayStage.setScene(dayScene);
		dayStage.show();
	}
	
	public void returnHome(ActionEvent event) throws IOException {
		Stage deleteEventStage = (Stage)ReturnHomeButton.getScene().getWindow();
		deleteEventStage.close();
	    
		FXMLLoader mainLoader = new FXMLLoader();
		mainLoader.setLocation(Main.class.getResource("controller/MainView.fxml"));
		
		Parent mainRoot = mainLoader.load();
		Scene mainScene = new Scene(mainRoot);
		
		MainController mainController = mainLoader.getController();
		mainController.initializeUser(currentUser);
		
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();
	}
}
