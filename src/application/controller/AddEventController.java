package application.controller;

import java.io.IOException;
import java.time.LocalDate;

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
	
	String eventName;
	String selectedCategory;
	boolean isExpense;
	double eventAmount;
	LocalDate dateStart;
	int interval;
	String intervalType;
	
	@FXML
	private Label StatusLabel;
	@FXML
	public Button CreateNewEventButton;
	@FXML
	public Button AddEventCategoryButton;
	@FXML
	private Button ReturnHomeButton;
	@FXML
	public ChoiceBox<String> EventCategoryChoiceBox;
	@FXML
	private RadioButton ExpenseRadioButton;
	@FXML
	private RadioButton IncomeRadioButton;
	@FXML
	public TextField EventNameTextField;
	@FXML
	public TextField NewEventCategoryTextField;
	@FXML
	public TextField EventAmountTextField;
	@FXML
	public DatePicker StartingDatePicker;
	@FXML
	public TextField TimeIntervalTextField;
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
		IncomeRadioButton.setToggleGroup(ExpenseGroup);
		
		ToggleGroup IntervalGroup = new ToggleGroup();
		
		EveryMonthRadioButton.setToggleGroup(IntervalGroup);
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
	
	public void addEventCategory(ActionEvent event) throws IOException {
		
		String newEventCat = NewEventCategoryTextField.getText();
		
		currentUser.addEventCategory(newEventCat);
		StatusLabel.setText("Status: New Category Added!");
	}
	
	public void dateSelected(ActionEvent event) throws IOException{
		dateStart = StartingDatePicker.getValue();
		System.out.println("Date selected: " + dateStart);
	}
	
	public void expenseSelected(ActionEvent event) throws IOException {
		isExpense = true;
	}
	
	public void incomeSelected(ActionEvent event) throws IOException {
		isExpense = false;
	}
	
	public void monthSelected(ActionEvent event) throws IOException {
		intervalType = "Month";
	}
	
	public void weekSelected(ActionEvent event) throws IOException {
		intervalType = "Week";
	}
	
	public void daySelected(ActionEvent event) throws IOException {
		intervalType = "Day";
	}
	
	public void addEvent(ActionEvent event) throws IOException {
		
		eventName = EventNameTextField.getText();
		selectedCategory = EventCategoryChoiceBox.getSelectionModel().getSelectedItem();
		eventAmount = Double.parseDouble(EventAmountTextField.getText());
		interval = Integer.parseInt(TimeIntervalTextField.getText());
		
		currentUser.createNewEvent(eventName, selectedCategory, isExpense, eventAmount, dateStart, interval, intervalType);
		currentUser.updateEventCategories();
		
		StatusLabel.setText("Status: New Event Added!");
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
