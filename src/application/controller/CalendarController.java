package application.controller;

import java.io.IOException;
//import java.time.DayOfWeek;
import java.time.LocalDate;

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
	private Button[] ButtonList;
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
		initializeButtonArray();
		initializeAll(currentUser);
	}
	
	public void initializeButtonArray() {
		ButtonList = new Button[35];
		ButtonList[0] = DayButton1;
		ButtonList[1] = DayButton2;
		ButtonList[2] = DayButton3;
		ButtonList[3] = DayButton4;
		ButtonList[4] = DayButton5;
		ButtonList[5] = DayButton6;
		ButtonList[6] = DayButton7;
		ButtonList[7] = DayButton8;
		ButtonList[8] = DayButton9;
		ButtonList[9] = DayButton10;
		ButtonList[10] = DayButton11;
		ButtonList[11] = DayButton12;
		ButtonList[12] = DayButton13;
		ButtonList[13] = DayButton14;
		ButtonList[14] = DayButton15;
		ButtonList[15] = DayButton16;
		ButtonList[16] = DayButton17;
		ButtonList[17] = DayButton18;
		ButtonList[18] = DayButton19;
		ButtonList[19] = DayButton20;
		ButtonList[20] = DayButton21;
		ButtonList[21] = DayButton22;
		ButtonList[22] = DayButton23;
		ButtonList[23] = DayButton24;
		ButtonList[24] = DayButton25;
		ButtonList[25] = DayButton26;
		ButtonList[26] = DayButton27;
		ButtonList[27] = DayButton28;
		ButtonList[28] = DayButton29;
		ButtonList[29] = DayButton30;
		ButtonList[30] = DayButton31;
		ButtonList[31] = DayButton32;
		ButtonList[32] = DayButton33;
		ButtonList[33] = DayButton34;
		ButtonList[34] = DayButton35;
	}
	
	public void initializeAll(User user) {
		LocalDate date = LocalDate.now();
		int currentDay = date.getDayOfMonth();
		//DayOfWeek dayOfWeek = date.getDayOfWeek();
		
		int monthCount = date.getMonthValue();
		int count = 0;
		for(int i = 0; i < 35; i++) {
			ButtonList[currentDay - 1 + count].setText("" + (currentDay + count));
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
		String day = ((Button)event.getSource()).getText();
		String month = Integer.toString(LocalDate.now().getMonthValue());
		int dayNum = Integer.parseInt(day);
		int dayMonth = LocalDate.now().getMonthValue();
		int dayYear = LocalDate.now().getYear();
		if (dayNum < 10)
			day = "0" + dayNum;
		if(dayMonth < 10)
			month = "0" + dayMonth;
		
		String date = dayYear + "-" + month + "-" + day;
		
		FXMLLoader dayLoader = new FXMLLoader();
		dayLoader.setLocation(Main.class.getResource("controller/DayView.fxml"));
		
		Parent dayRoot = dayLoader.load();
		Scene dayScene = new Scene(dayRoot);
		
		DayController dayController = dayLoader.getController();
		dayController.initializeUser(currentUser, date);
		
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
