package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
	@FXML
	private Scene personnelScene;
	@FXML
	private Label ErrorLabel;
	@FXML
	private PasswordField PasswordField;
	@FXML
	private TextField UsernameField;
	
public void handle(ActionEvent event) throws IOException {
		
		String username = UsernameField.getText();
		String password = PasswordField.getText();
		
		System.out.println(username);
		System.out.println(password);
		
	}

	/*
	public void setPersonnelScene(Scene scene) {
		personnelScene = scene;
		
	}
	*/
}