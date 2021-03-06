package main;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// User class creates User object with usrName, usrPassword and usrID attributes

public class User {

	private UUID usrID; // unique Id for each user
	private String usrName; // user name for each user
	private String usrPassword; // users password
	private String usrEmail; // user's email
	
	
	// Constructor Method: Creates a new User with new UUID
	// Parameters: 
	//		usrName: string, name of user
	//		usrPassword: string, user's password for login
	//		usrID: unique ID for each user, used to identify user's tasks
	public User(String newUsrName, String newUsrPassword, String newUsrEmail) {
		this.usrName = newUsrName;
		this.usrPassword = newUsrPassword;
		if (newUsrEmail.compareTo("") == 0) {
			this.usrEmail = null;
		} else this.usrEmail = newUsrEmail;
		this.usrID = UUID.nameUUIDFromBytes(newUsrName.getBytes());
	}

	// Constructor Method: Loads a user from a String from a text file
	// Parameters:
	//		saveString: String whose format is "UUID,name,password,email"
	public User(String saveString) {
		String[] vals = saveString.split(",");
		this.usrID = UUID.fromString(vals[0]);
		this.usrName = vals[1];
		this.usrPassword = vals[2];
		if (vals[3].compareTo("null") != 0) {
			this.usrEmail = vals[3];
		} else this.usrEmail = null;
	}

	// Creates a string representation of a User
	// Parameters: None
	// Returns: String of User's attributes
	@Override
	public String toString() {
		return "\tUsername: " + usrName +
				"\n\tPassword: " + usrPassword + 
				"\n\tEmail: " + usrEmail +
				"\n\tUserID: " + usrID;
	}
	
	// Creates a save string representation of a User whose format is "UUID,name,password,email"
	// Parameters: None
	// Returns: String that represents a User
	protected String toSaveString() {
		return usrID.toString()+ ","
				+ usrName + ","
				+ usrPassword + ","
				+ usrEmail + "\n";
	}
	
	// Checks whether a given String matches this User's name (case insensitive)
	// Parameters: 
	//		toCheck: String to check against this User's name
	// Returns: Boolean, true if toCheck matches this User's name, false otherwise	
	public boolean checkName(String toCheck){
		return usrName.equalsIgnoreCase(toCheck);
	}
	
	// Checks whether a given String matches this User's password (case sensitive)
	// Parameters: 
	//		toCheck: String to check against this User's name
	// Returns: Boolean, true if toCheck matches this User's name, false otherwise
	public boolean checkPassword(String toCheck){
		return usrPassword.equals(toCheck);
	}
	
	// Getter method for usrName
	public String getUsrName() {
		return usrName;
	}
	// Setter method for usrName
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	// Getter method for usrPassword
	public String getUsrPassword() {
		return usrPassword;
	}
	// Setter method for usrPassword
	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}
	
	// Getter method for usrID
	public UUID getUsrID() {
		return usrID;
	}

	public String getUsrEmail() {
		return usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
	
	public static boolean validateEmaill(String email){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email);
        
        if(m.find() && m.group().equals(email)){
            return true;
        }else{
            return false;            
        }
	}
}