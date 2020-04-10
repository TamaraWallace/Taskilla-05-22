package Application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Task;

public class EditTaskController implements Initializable {


	@FXML 
	private TextField name,notes; //associated with text object for task name & notes in FXML
	@FXML
	private DatePicker date; //associated with date picker object for task due date in FXML
	
	@FXML 
	private Label dateLabel,nameLabel;
	
	@FXML
	private Button backBtn; //associated with the back button object in FXML
	
	@FXML
	private ImageView taskilla_logo,backBtnImg,nameNotesIcon,penIcon,calenderIcon,doneBtnImg;
	
	private static Task taskToEdit; //the task to be edited
	
	// Initialize Method, implemented from the Initializable Class
	// When the class is started it sets the values of the fields to the task information
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		System.out.println("\nEdit Task Scene");
		
		name.setText(taskToEdit.getName());
		date.setValue(taskToEdit.getDueDate());;
		notes.setText(taskToEdit.getNotes());
		
		
		// Initialize the images for scene
		backBtnImg.setImage(new Image("Back.png"));
		taskilla_logo.setImage(new Image("taskilla_logo.jpg"));
		nameNotesIcon.setImage(new Image("NameNotesIcon.png"));
		penIcon.setImage(new Image("Pen Icon.png"));
		calenderIcon.setImage(new Image("CalenderIcon.png"));
		doneBtnImg.setImage(new Image("doneBTN.jpg"));
		
	}

	// Method Name: doneButton
	// Parameters: event, an Action event thrown by the button associated with this method
	// Return: void
	// Functionality: when the done button is pressed, the information entered is checked
	//				  and if it is valid input, the values of the selected task are changed
	public void doneButton(ActionEvent event) throws IOException {
		
		// get name & notes as strings
		String taskName = name.getText();
		String taskNotes = notes.getText();
		// validating taskName is not empty
		if (taskName.isEmpty()) {
			nameLabel.setText("TASK NAME CAN'T BE EMPTY");
			taskName = name.getText();
		} else {
			nameLabel.setText("");
		}			
		// validating the taskDate is not empty 
		LocalDate taskDate = date.getValue();
		if (taskDate == null) {
			dateLabel.setText("TASK DATE CAN'T BE EMPTY");
		} else {
			dateLabel.setText("");
		}
		// if everything is good, edit the task:
		if (taskDate != null && !taskName.isEmpty()) {
			System.out.println("the name is " + taskName + "the date is" + taskDate + "the notes are" + taskNotes);
			taskToEdit.setName(taskName);
			taskToEdit.setNotes(taskNotes);
			taskToEdit.setDueDate(taskDate);
			GuiBasedApp.getTasks().sortTasks();
			
			//send user to home screen
			GuiBasedApp.launchHomeScreenScene();	
		}
	}
	
	// Method Name: backButton
	// Parameters: event, an Action event thrown by the button associated with this method
	// Return: void
	// Functionality: when the back button is pressed, the user is returned to 
	//				  the previous screen 
	public void back(ActionEvent event) {
		GuiBasedApp.launchTaskMenuScene();
	}
	
	// Method Name: setTaskToEdit
	// Parameters: a task t
	// Return: void
	// Functionality: sets the task being edited to the task t provided
	public static void setTaskToEdit(Task t) {
		taskToEdit = t;
	}
}
