import java.util.Scanner;

public class Menu {
	private String options[];	// array of strings representing user options
	private String title;		// menu title
	private Scanner input;		// for KB input
	
	/**
	 * Constructor for class
	 * @param title - the menu title
	 * @param options - the options for user selection
	 */
	public Menu(String title, String options[]) {
		this.title = title;
		copyOptions(options);
		input = new Scanner(System.in);
	}
	
	/**
	 * Requests and read a user choice
	 * @return - the user choice
	 */
	public int getChoice() {
		display();
		System.out.print("Enter choice: ");
		int choice = input.nextInt();
		return choice;
	}
	
	/**
	 * displays the menu title followed by the user
	 * option for selection
	 */
	private void display() {
		if (title != null && options !=null) {
			// title and options are valid
			// display title and underline with '+'
			System.out.println(title);
			for(int i=0;i<title.length();i++) {
				System.out.print("+");
			}
			System.out.println("\n");
			// display the menu options
			// prefix each with an int starting at 1
			int count = 1;
			for(String str : options) {
				System.out.println(count+". "+str);
				count++;
			}
			System.out.println();
		}
		else {
			// title and/or options are not valid
			System.out.println("Menu not defined.");
		}
	}
	
	/**
	 * will initialise the options array by copying
	 * contents of data
	 * @param data - array of Strings - options for user to select from
	 */
	private void copyOptions(String data[]) {
		if ( data != null) {
			options = new String[data.length];
			for(int index=0;index<data.length;index++) {
				options[index] = data[index];
			}
		}
		else {
			options = null;
		}
	}
	
	private static void processChoice(int selection) {
		switch( selection ) {
		case 1 :	
			//addNewEmployee(); 
			break;
		case 2 :	
			//listEmployees(); 
			break;
		default :	
			System.out.println("Error - Invalid Option.");
		}
	}
	
	/*public userMode (String, String address) {
		this(name, address, 20000);
	}*/
	
	//could use in main method for defining each option
	private void application () {
		String option[] = {"Buy item", "Check prices", "Cancel", "Quit"};
		int choice;
		int quitOption = options.length;
		Menu sysMenu = new Menu("Employee Application", options);
		
		do {
			choice = sysMenu.getChoice();
			if ( choice == quitOption ) {
				break;
			}
			processChoice(choice);
		}
		while(true);
		
		System.out.print("Exit Application");
	}

	private void stockModeApplication () {
		String option[] = {"Set item price", "Check prices", "Empty changebox", "Insert change", "Quit to user mode"};
		int choice;
		int quitOption = options.length;
		Menu sysMenu = new Menu("Employee Application", options);
		
		do {
			choice = sysMenu.getChoice();
			if ( choice == quitOption ) {
				break;
			}
			processChoice(choice);
		}
		while(true);
		
		System.out.print("Exit Application");
	}

}
