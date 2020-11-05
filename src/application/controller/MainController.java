package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class MainController {
	
	@FXML
	TextArea currentCycle;
	@FXML
	Button refreshButton;
	
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
