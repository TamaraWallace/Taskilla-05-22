package Application;

import java.awt.desktop.UserSessionEvent;
import java.io.IOException;
import java.util.UUID;

import main.*;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
	
	private int attempts =0;
	
	@FXML
	private TextField lgnName;
	private String lgnNameStyle = null; 
	
	@FXML
	private PasswordField lgnPassword;
	
	@FXML
	private Button newUsr;
	
	@FXML
	private Button lgnButton;
	private String lgnButtonStyle;
		
	@FXML
	public void login(ActionEvent event) throws IOException {
		
		if (lgnNameStyle == null) {
			lgnNameStyle = lgnName.getStyle();
		}
		
		String usrName = lgnName.getText();
		
		String usrPassword = lgnPassword.getText();
		
		boolean loggedIn = false;
		
		//System.out.println("Trying to Log you in:");
		
		if (GuiBasedApp.getUsers().findUser(usrName) == null) {
			String style = lgnName.getStyle();
			//System.out.println(style);
			lgnName.setStyle(style + ("-fx-border-color: #ff0000; -fx-border-width: 5px; "));
			attempts = 0;
			
		}else {
			lgnName.setStyle(lgnNameStyle);
		}
		
		if (attempts <3) {
			
			//System.out.println("login text based");
			UUID userID = TextBasedApp.login(usrName,usrPassword);
			System.out.println("userID: "+userID);
			if (userID == null) {
				attempts ++;
				
				String style = lgnPassword.getStyle();
				lgnPassword.setStyle(style + ("-fx-border-color: #ff0000; -fx-border-width: 5px; "));
				
			} else {
				attempts = 0;
				GuiBasedApp.setUserID(userID);
				loggedIn = true;
			}
		}else if (attempts == 3) {
			
			Alert alert = new Alert(AlertType.WARNING);
			DialogPane dialogPane = alert.getDialogPane();
						
			dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
			//dialogPane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID ,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
			alert.setTitle("Warning");
			alert.setHeaderText(attempts+" Password Attempts");
			alert.setContentText("Last attempt or the program will close");
		
			alert.showAndWait();
			attempts++;
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			DialogPane dialogPane = alert.getDialogPane();
						
			dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
			//dialogPane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID ,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
			dialogPane.setStyle("-fx-border-color: red;");
			alert.setTitle("Warning");
			alert.setHeaderText(attempts+" Password Attempts!! TOO MANY!");
			alert.setContentText("Program will close !");
			
			//Stage window = (Stage) (dialogPane.getScene().getWindow());
			
			alert.showAndWait();
			System.exit(0);
		}
		
		if (loggedIn) {
			
			System.out.println("Logged In: new scene");
			//Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			//AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
			
			//Scene createUserScene = new Scene(pane);
			
			//createUserScene.getStylesheets().add(getClass().getResource("CreateUser.css").toExternalForm());
			
			//GuiBasedApp.setPrevScene(window.getScene());
			//window.setScene(createUserScene);
			//window.show();
		}
		
		
	}
	
	@FXML
	public void newUser(ActionEvent event) throws IOException{
			
		//System.out.println("New user everyone");
		
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
		
		Scene createUserScene = new Scene(pane);
		
		createUserScene.getStylesheets().add(getClass().getResource("CreateUser.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(createUserScene);
		window.show();

		
		
	}
	
	@FXML
	public void lgnNameKeyPressed( KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			lgnPassword.requestFocus();
			
		}else {
			//System.out.println(event.getCode());
		}
	}
	@FXML
	public void lgnPasswordKeyPressed( KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			lgnButton.fire();
			
		}
	}
	@FXML
	public void anchorPane( KeyEvent event) {
		// nothing yet
	}
	
	@FXML
	public void mouseEv(MouseEvent event) {
		
		//System.out.println("track mouse: "+ event.getEventType());
	}
	
	
}
