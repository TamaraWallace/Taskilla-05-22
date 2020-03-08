package Application;

import java.net.URL;
import main.User;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Task;
import main.TaskCollection;

public class AddTaskController  implements Initializable{
	
		@FXML 
		private TextField name,notes;
		@FXML
		private DatePicker date;
		
		@FXML 
		private Label dateLabel,nameLabel;
		
		TaskCollection tasks = new TaskCollection();
		
		public void addButton() {
			
			try {
				// get name & notes as strings
				String taskName = name.getText();
				String taskNotes = notes.getText();
				System.out.println(taskName+taskNotes);
				// validating taskName is not empty
				if (taskName.isBlank()) {
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
					System.out.println(taskDate);
				}
				
				
				// if everything is good, make a task: 
				if ( taskDate != null && !taskName.isBlank()) {
				String userID = GuiBasedApp.getUserID();
				 Task task = new Task(userID, taskName, taskNotes, false, taskDate);
				 System.out.println(task.toString());
				 
				 TaskCollection.addTask(task);
				 tasks.display();
				
				// clearing out textfield boxes for name & notes 
				 if (task != null) {
					 name.clear();
					 notes.clear();
				 }
				
		
				// clear out the date picker as it doesn't do this automatically 
				date.setValue(null);
				
			}
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

			// TODO Auto-generated method stub
			
	
		}
		public void backButton() {
			// to do when main menu is made 
		}
}
