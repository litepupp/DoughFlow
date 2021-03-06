package application.controller;

//test
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;

import application.Main;
import application.model.*;

public class LoginController {
		
	public static Login currentLogin;
	public static User userToBeLogin;
	boolean alreadyLoad;
	
	@FXML
	private Scene loginScene;
	@FXML
	private Label ErrorLabel;
	@FXML
	public JFXTextField NameField;
	@FXML
	public JFXTextField UsernameField;
	@FXML
	public JFXPasswordField PasswordField;
	@FXML
	public JFXButton LoginButton;
	@FXML
	public JFXButton RegisterButton;
	
	public void initializeLogins() {
		currentLogin = new Login();
		currentLogin.loadLogins("data/logins.csv");
	}
	
	public void LoginPress(ActionEvent event) throws IOException {
		
		String username = UsernameField.getText();
		String password = PasswordField.getText();
		
		if (alreadyLoad == false) {
			initializeLogins();
			this.alreadyLoad = true;
		}
		
		boolean loginResult = currentLogin.validateLogin(username, password);
		
		if (loginResult == true) {
			System.out.println("Successful login, username: " + username);
			userToBeLogin = currentLogin.logins.get(username);
			
			FXMLLoader mainLoader = new FXMLLoader();
			mainLoader.setLocation(Main.class.getResource("controller/MainView.fxml"));
			
			Parent mainRoot = mainLoader.load();
			Scene mainScene = new Scene(mainRoot);
			
			MainController mainController = mainLoader.getController();
			mainController.initializeAll(currentLogin, userToBeLogin);
			
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			mainStage.setScene(mainScene);
			mainStage.show();
		}
		else
		{
			ErrorLabel.setText("Error: Incorrect username/password");
		}

	}
	
	public void RegisterPress(ActionEvent event) throws IOException {
		
		String name = NameField.getText();
		String username = UsernameField.getText();
		String password = PasswordField.getText();
			
		if (alreadyLoad == false) {
			initializeLogins();
			this.alreadyLoad = true;
		}
		
		boolean registerResult = currentLogin.registerUser(name, username, password);
		
		if (registerResult == true) {
			ErrorLabel.setText("User [" + name + "/" + username + "] registered!");
			currentLogin.printAllUsers();
		}
		
		else {
			ErrorLabel.setText("Error: User [" + name + "/" + username + "] already exists");
		}
		
	}
	
}