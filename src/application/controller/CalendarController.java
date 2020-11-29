package application.controller;

import java.io.IOException;
//import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

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
	private ArrayList<Button> ButtonList;
	@FXML
	private Button DayButton1, DayButton2, DayButton3, DayButton4, DayButton5, DayButton6, DayButton7,
	DayButton8, DayButton9, DayButton10, DayButton11, DayButton12, DayButton13, DayButton14, DayButton15,
	DayButton16, DayButton17, DayButton18, DayButton19, DayButton20, DayButton21, DayButton22, DayButton23,
	DayButton24, DayButton25, DayButton26, DayButton27, DayButton28, DayButton29, DayButton30, DayButton31,
	DayButton32, DayButton33, DayButton34, DayButton35;
	
	/*public enum Weekday { 
	      MONDAY, TUESDAY, WEDNESDAY, 
	      THURSDAY, FRIDAY, 
	      SATURDAY, SUNDAY };*/
	
	public void initializeUser(User user) {
		currentUser = user;
		initializeAll(currentUser);
	}
	
	public void initializeAll(User user) {
		LocalDate date = LocalDate.now();
		int currentDay = date.getDayOfMonth();
		//DayOfWeek dayOfWeek = date.getDayOfWeek();
		
		int monthCount = date.getMonthValue();
		int count = 0;
		for(int i = 0; i < 35; i++) {
			ButtonList.get(currentDay - 1 + count).setText(Integer.toString(currentDay + count));
			count++;
			if((monthCount==1 || monthCount==3 || monthCount==5 || monthCount==7 || monthCount==8 || monthCount==10 || monthCount==12)){
				if((currentDay + count) > 31){
					count = count - 31;
				}
			}
			if((monthCount==4 || monthCount==6 || monthCount==9 || monthCount==11)){
				if((currentDay + count) > 30){
					count = count - 30;
				}
			}
			if(monthCount==2)
				count = count - 28;
		}
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
