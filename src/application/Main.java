package application;

import java.io.IOException;

import application.controller.LoginController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws IOException {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("DoughFlow");
		
		FXMLLoader loginLoader = new FXMLLoader();
		loginLoader.setLocation(Main.class.getResource("controller/LoginView.fxml"));
		
		Parent root = loginLoader.load();
		Scene scene = new Scene(root);
		
		LoginController loginController = loginLoader.getController();
		
		loginController.RegisterButton.disableProperty().bind(
			    Bindings.isEmpty(loginController.NameField.textProperty())
			    .or(Bindings.isEmpty(loginController.UsernameField.textProperty()))
			    .or(Bindings.isEmpty(loginController.PasswordField.textProperty()))
			);
		
		loginController.LoginButton.disableProperty().bind(
			    Bindings.isEmpty(loginController.UsernameField.textProperty())
			    .or(Bindings.isEmpty(loginController.PasswordField.textProperty()))
			);
		
		primaryStage.setScene(scene);
//		scene.getStylesheets().add("Style.css");
		primaryStage.show();
		

		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
