package application.controller;

import java.io.IOException;

import application.Main;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class AddEventController {
	
	public static User currentUser;
	
	@FXML
	private Label StatusLabel;
	@FXML
	private Button CreateNewEventButton;
	@FXML
	private Button AddEventCategoryButton;
	@FXML
	private Button ReturnHomeButton;
	@FXML
	private ChoiceBox<String> EventCategoryChoiceBox;
	@FXML
	private RadioButton ExpenseRadioButton;
	@FXML
	private RadioButton IncomeRadioButton;
	@FXML
	private TextField EventNameTextField;
	@FXML
	private TextField NewEventCategoryTextField;
	@FXML
	private TextField EventAmountTextField;
	@FXML
	private DatePicker StartingDatePicker;
	@FXML
	private TextField EventTimeIntervalTextField;
	@FXML
	private RadioButton EveryMonthRadioButton;
	@FXML
	private RadioButton EveryWeekRadioButton;
	@FXML
	private RadioButton EveryDayRadioButton;

	public void initializeUser(User user) {
		currentUser = user;
		
		EventCategoryChoiceBox.setItems(convertSetToList(currentUser.getEventCategories()));
		EventCategoryChoiceBox.setTooltip(new Tooltip("Select an Event Category"));
		
		setToggleGroups();
	}
	
	public void setToggleGroups() {
		ToggleGroup ExpenseGroup = new ToggleGroup();
		
		ExpenseRadioButton.setToggleGroup(ExpenseGroup);
		ExpenseRadioButton.setSelected(true);
		
		IncomeRadioButton.setToggleGroup(ExpenseGroup);
		
		ToggleGroup IntervalGroup = new ToggleGroup();
		
		EveryMonthRadioButton.setToggleGroup(IntervalGroup);
		EveryMonthRadioButton.setSelected(true);
		
		EveryWeekRadioButton.setToggleGroup(IntervalGroup);
		
		EveryDayRadioButton.setToggleGroup(IntervalGroup);
	}
	
	private ObservableList<String> convertSetToList(ObservableSet<String> set) {
	        ObservableList<String> list = FXCollections.observableArrayList(set);

	        set.addListener((SetChangeListener<String>) change -> {
	            if (change.wasAdded()) {
	                String added = change.getElementAdded();
	                list.add(added);
	            } else {
	                String removed = change.getElementRemoved();
	                list.remove(removed);
	            }
	        });

	        return list;
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
