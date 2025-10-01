package guiFirstAdmin;

import java.sql.SQLException;
import database.Database;
import entityClasses.User;
import javafx.stage.Stage;

public class ControllerFirstAdmin {
	/*-********************************************************************************************

	The controller attributes for this page
	
	This controller is not a class that gets instantiated.  Rather, it is a collection of protected
	static methods that can be called by the View (which is a singleton instantiated object) and 
	the Model is often just a stub, or will be a singleton instantiated object.
	
	*/
	
	private static String adminUsername = "";
	private static String adminPassword1 = "";
	private static String adminPassword2 = "";		
	protected static Database theDatabase = applicationMain.FoundationsMain.database;		

	/*-********************************************************************************************

	The User Interface Actions for this page
	
	*/
	
	
	/**********
	 * <p> Method: setAdminUsername() </p>
	 * 
	 * <p> Description: This method is called when the user adds text to the username field in the
	 * View.  A private local copy of what was last entered is kept here.</p>
	 * 
	 */
	protected static void setAdminUsername() {
		adminUsername = ViewFirstAdmin.text_AdminUsername.getText();
	}
	
	
	/**********
	 * <p> Method: setAdminPassword1() </p>
	 * 
	 * <p> Description: This method is called when the user adds text to the password 1 field in
	 * the View.  A private local copy of what was last entered is kept here.</p>
	 * 
	 */
	protected static void setAdminPassword1() {
		adminPassword1 = ViewFirstAdmin.text_AdminPassword1.getText();
		ViewFirstAdmin.label_PasswordsDoNotMatch.setText("");
	}
	
	
	/**********
	 * <p> Method: setAdminPassword2() </p>
	 * 
	 * <p> Description: This method is called when the user adds text to the password 2 field in
	 * the View.  A private local copy of what was last entered is kept here.</p>
	 * 
	 */
	protected static void setAdminPassword2() {
		adminPassword2 = ViewFirstAdmin.text_AdminPassword2.getText();		
		ViewFirstAdmin.label_PasswordsDoNotMatch.setText("");
	}
	
	
	// Method to validate the username
	// Validate username
	protected static boolean validateAdminUsername() {
	    // Regex: must have at least one uppercase letter, one number, and one of @ - _ . as a special character
	    // Special characters are restricted to: @, -, _, and .
	    String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@\\-_\\.]).{8,}$"; 
	    String usernameRegex = "^[a-zA-Z0-9].{7,}$"; // Start with alphanumeric and ensure length >= 8

	    // Check if username starts with alphanumeric character and is at least 8 characters long
	    if (!adminUsername.matches(usernameRegex)) {
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText("Username must start with alphanumeric and be at least 8 characters.");
	        return false;
	    }

	    // Check if username matches the strong username pattern
	    if (!adminUsername.matches(regex)) {
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText("Username must contain a capital letter, number, and special character (@, -, _, .).");
	        return false;
	    }

	    // Check for consecutive special characters (e.g., @@, --, etc.)
	    if (adminUsername.matches(".*[@\\-_\\.]{2,}.*")) {
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText("Username cannot contain consecutive special characters.");
	        return false;
	    }
	    return true;
	}


	// Method to validate the password
	// Validate password
	protected static boolean validateAdminPassword() {
	    // Regex: password must have at least one uppercase letter, one number, and one special character
	    // Must be between 8 and 32 characters
	    String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+`~\\[\\]{}\\\\|;:'\",.<>?/]).{8,32}$";
	    String passwordStartRegex = "^[a-zA-Z0-9].{7,31}$"; // Must start with alphanumeric and ensure length is between 8-32 characters

	    // Check if password starts with alphanumeric and has correct length (8-32)
	    if (!adminPassword1.matches(passwordStartRegex)) {
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText("Password must start with alphanumeric and be between 8 and 32 characters.");
	        return false;
	    }

	    // Check if password matches the strong password pattern
	    if (!adminPassword1.matches(passwordRegex)) {
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText("Password must contain a capital letter, number, and special character.");
	        return false;
	    }

	    // Check for consecutive special characters (e.g., !!, --, etc.)
	    if (adminPassword1.matches(".*[!@#$%^&*()\\-_=+`~\\[\\]{}\\\\|;:'\",.<>?/]{2,}.*")) {
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText("Password cannot contain consecutive special characters.");
	        return false;
	    }
	    return true;
	}


	/**********
	 * <p> Method: doSetupAdmin() </p>
	 * 
	 * <p> Description: This method is called when the user presses the button to set up the Admin
	 * account.  It start by trying to establish a new user and placing that user into the
	 * database.  If that is successful, we proceed to the UserUpdate page.</p>
	 * 
	 */
	protected static void doSetupAdmin(Stage ps, int r) {
	    // Make sure the two passwords are the same
	    if (adminPassword1.compareTo(adminPassword2) == 0) {
	        // Validate username and password
	        if (validateAdminUsername() && validateAdminPassword()) {
	            // Create the user and proceed
	            User user = new User(adminUsername, adminPassword1, "", "", "", "", "", true, false, false);
	            try {
	                // Create a new User object with admin role and register in the database
	                theDatabase.register(user);
	            } catch (SQLException e) {
	                System.err.println("*** ERROR *** Database error trying to register a user: " + e.getMessage());
	                e.printStackTrace();
	                System.exit(0);
	            }

	            // User was established in the database, so navigate to the User Update Page
	            guiUserUpdate.ViewUserUpdate.displayUserUpdate(ViewFirstAdmin.theStage, user);
	        }
	    } else {
	        // The passwords do not match, clear the passwords and display a warning
	        ViewFirstAdmin.text_AdminPassword1.setText("");
	        ViewFirstAdmin.text_AdminPassword2.setText("");
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText("The two passwords must match. Please try again!");
	    }
	}


	
	
	/**********
	 * <p> Method: performQuit() </p>
	 * 
	 * <p> Description: This method terminates the execution of the program.  It leaves the
	 * database in a state where the normal login page will be displayed when the application is
	 * restarted.</p>
	 * 
	 */
	protected static void performQuit() {
		System.out.println("Perform Quit");
		System.exit(0);
	}	
}

