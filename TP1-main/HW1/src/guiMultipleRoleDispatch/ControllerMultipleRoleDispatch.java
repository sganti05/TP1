package guiMultipleRoleDispatch;

public class ControllerMultipleRoleDispatch {

	/*-********************************************************************************************

	User Interface Actions for this page
	
	This controller is not a class that gets instantiated.  Rather, it is a collection of protected
	static methods that can be called by the View (which is a singleton instantiated object) and 
	the Model is often just a stub, or will be a singleton instantiated object.

	 */
	
	
	/**********
	 * <p> Method: performRole() </p>
	 * 
	 * <p> Description: This method directs the execution to one of the various home pages based on
	 * the current value of the SelectRole ComboBox. </p>
	 * 
	 */
	static protected void performRole() {
		
		// Fetch the role from the SelectRole ComboBox
		String role = guiMultipleRoleDispatch.ViewMultipleRoleDispatch.combobox_SelectRole.getValue();

		// See if Admin
		if (role.compareTo("Admin") == 0) {
			
			// It is Admin Role
			guiAdminHome.ViewAdminHome.displayAdminHome(
					guiMultipleRoleDispatch.ViewMultipleRoleDispatch.theStage, 
					guiMultipleRoleDispatch.ViewMultipleRoleDispatch.theUser);
			
		// See if Role1
		} else if (role.compareTo("Role1") == 0) {

			// It is Role1 Role
			guiRole1.ViewRole1Home.displayRole1Home(
					guiMultipleRoleDispatch.ViewMultipleRoleDispatch.theStage, 
					guiMultipleRoleDispatch.ViewMultipleRoleDispatch.theUser);
			
		// See if Role2
		} else if (role.compareTo("Role2") == 0) {
			
			// It is Role 2 Role
			guiRole2.ViewRole2Home.displayRole2Home(
					guiMultipleRoleDispatch.ViewMultipleRoleDispatch.theStage, 
					guiMultipleRoleDispatch.ViewMultipleRoleDispatch.theUser);
			
		} else {
			// Invalid role
			System.out.println("*** ERROR *** GUIMultipleRoleDispatch was asked to dispatch to " +
					"a role, " + role + ", that is not supported!");
			System.exit(0);
		}
	}
	
	
	/**********
	 * <p> Method: performLogout() </p>
	 * 
	 * <p> Description: This method logs out the current user and proceeds to the normal login
	 * page where existing users can log in or potential new users with a invitation code can
	 * start the process of setting up an account. </p>
	 * 
	 */
	static protected void performLogout() {
		guiUserLogin.ViewUserLogin.displayUserLogin(
				guiMultipleRoleDispatch.ViewMultipleRoleDispatch.theStage);
	}
	
	
	/**********
	 * <p> Method: performQuit() </p>
	 * 
	 * <p> Description: This method terminates the execution of the program.  It leaves the
	 * database in a state where the normal login page will be displayed when the application is
	 * restarted.</p>
	 * 
	 */
	static protected void performQuit() {
		System.exit(0);
	}

}
