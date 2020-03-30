package Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import main.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	private int attempts =0;
	
	@FXML
	private TextField lgnName;
	
	@FXML
	private PasswordField lgnPassword;
	
	@FXML
	private Button newUsr;
	
	@FXML
	private Button lgnButton;
	
	@FXML
	private Label lgnValidPassLbl;
	
	@FXML
	private Label lgnValidUsrLbl;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("\nLogin Scene");
	}
	
	// ----------------------- EVENT HANDLERS -----------------------
	
	@FXML
	public void login(ActionEvent event) throws IOException {
		String usrName = lgnName.getText();
		String usrPassword = lgnPassword.getText();
		boolean loggedIn = false;
		attempts++;
		
		
		System.out.println("Login Attempt: " + attempts);
		
		User u = GuiBasedApp.getUsers().validateUsernameAndPassword(usrName,usrPassword);
		
		if (u == null) {
			System.out.println("Invalid login credentials");
			String style = lgnPassword.getStyle();
			lgnPassword.setStyle(style + ("-fx-border-color: #ff0000; -fx-border-width: 5px; "));
			lgnPassword.setText("");
			lgnValidPassLbl.setText("Invalid Password");	
		} else {
			attempts = 0;
			GuiBasedApp.loginUser(u);
			
			loggedIn = true;
		}
		
		if (!loggedIn && attempts == 3) {
			Alert alert = new Alert(AlertType.WARNING);
			DialogPane dialogPane = alert.getDialogPane();			
			dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
			
			alert.setTitle("Warning");
			alert.setHeaderText(attempts+" Password Attempts");
			alert.setContentText("Last attempt or the program will close");
			alert.showAndWait();
		} else if (!loggedIn && attempts > 3) {
			Alert alert = new Alert(AlertType.ERROR);
			DialogPane dialogPane = alert.getDialogPane();	
			dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
			
			dialogPane.setStyle("-fx-border-color: red;");
			alert.setTitle("Warning");
			alert.setHeaderText(attempts+" Password Attempts!! TOO MANY!");
			alert.setContentText("Program will close !");
		
			alert.showAndWait();
			System.exit(0);
		}
		
		if (loggedIn) {
			System.out.println(u.getUsrName() + " has successfully logged in.");
			System.out.println(u.toString());
			
			GuiBasedApp.launchHomeScreenScene();
		}
	}
	
	@FXML
	public void newUser(ActionEvent event) throws IOException {
		GuiBasedApp.launchCreateUserScene();
	}
	
	@FXML
	public void lgnNameKeyPressed( KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			lgnPassword.requestFocus();
		}
	}
	
	@FXML
	public void lgnPasswordKeyPressed( KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			lgnButton.fire();		
		}
	}
}