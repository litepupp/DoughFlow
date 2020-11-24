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

import application.model.*;

public class LoginController {
	
	Login currentLogin;
	boolean alreadyLoad;
	
	@FXML
	private Scene loginScene;
	@FXML
	private Label ErrorLabel;
	@FXML
	private TextField NameField;
	@FXML
	private PasswordField PasswordField;
	@FXML
	private TextField UsernameField;
	@FXML
	private Button LoginButton;
	@FXML
	private Button RegisterButton;
	
	public void initializeLogins() {
		currentLogin = new Login();
		currentLogin.loadLogins("data/logins.csv");
	}
	
	public void LoginPress(ActionEvent event) throws IOException {
		
		String username = UsernameField.getText();
		String password = PasswordField.getText();
		
		
		ErrorLabel.setText("Error not really");
		
	}
	
	public void RegisterPress(ActionEvent event) throws IOException {
		
		String name = NameField.getText();
		String username = UsernameField.getText();
		String password = PasswordField.getText();
			
		if (alreadyLoad == false) {
			initializeLogins();
			this.alreadyLoad = true;
		}
		
		if (currentLogin.registerUser(name, username, password) == true) {
			ErrorLabel.setText("User \"" + name + "/" + username + "\" registered!");
			currentLogin.printAllUsers();
		}
		else {
			ErrorLabel.setText("Error: User \"" + name + "/" + username + "\" already exists");
		}
		
	}
	
	/*
	public void setPersonnelScene(Scene scene) {
		personnelScene = scene;
		
	}
	*/
}