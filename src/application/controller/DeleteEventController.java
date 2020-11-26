package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

import application.model.*;

public class DeleteEventController {
	
	public static User currentUser;
	
	@FXML
	private Label completionLabel;
	@FXML
	private Button DeleteButton;
	@FXML
	private Button ReturnHomeButton;
	@FXML
	private ChoiceBox EventChoiceBox;
	@FXML
	private TextArea EventTextArea;
	
	public void returnHome(ActionEvent actionEvent) throws IOException {
		Stage deleteEventStage = (Stage)ReturnHomeButton.getScene().getWindow();
		deleteEventStage.close();
	    
	    MainController.initializeUser(currentUser);
	}
	
}
