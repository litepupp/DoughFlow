package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
	@FXML
	private Scene loginScene;
	@FXML
	private Label ErrorLabel;
	@FXML
	private PasswordField PasswordField;
	@FXML
	private TextField UsernameField;
	@FXML
	private Button LoginButton;
	@FXML
	private Button RegisterButton;
	
	public void LoginPress(ActionEvent event) throws IOException {
		
		String username = UsernameField.getText();
		String password = PasswordField.getText();
		
		System.out.println(username);
		System.out.println(password);
		
		ErrorLabel.setText("error");
		
	}
	
	public void RegisterPress(ActionEvent event) throws IOException {
		
		String username = UsernameField.getText();
		String password = PasswordField.getText();
		
		System.out.println(username);
		System.out.println(password);
		
		ErrorLabel.setText("Error");
		
	}

	/*
	public void setPersonnelScene(Scene scene) {
		personnelScene = scene;
		
	}
	*/
}