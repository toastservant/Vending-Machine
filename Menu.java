import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.io.*;

public class Menu {
	static Machine machine = new Machine();
	private static String options[];	// array of strings representing user options
	private String title;		// menu title
	private static Scanner input = new Scanner(System.in);		// for KB input
	
	/**
	 * Constructor for class
	 * @param title - the menu title
	 * @param options - the options for user selection
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		application();
		//stockModeApplication();
	}
	
	public Menu(String title, String options[]) {
		this.title = title;
		copyOptions(options);
		//input = new Scanner(System.in);
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
	
	private static void processChoiceUser(int selection) throws IOException {
		switch( selection ) {
		case 1 :	
			// method to buy item; 
			// method to reduce item quantity
			break;
		case 2 :	
			// method to check prices; 
			break;
		case 3:
			// method to cancel
			break;
		case 4 :
			key();
			break;
		case 5:
			System.out.println("Power off."); 
			break;
		default :	
			System.out.println("Error - Invalid Option.");
		}
	}
	
	/*public userMode (String, String address) {
		this(name, address, 20000);
	}*/
	
	//could use in main method for defining each option
	private static void application () throws IOException {

		//reads CSV file
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("Stock.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// consumes header line
		br.readLine();
		
		/* splits each line and adds an item to the machine instance arraylist
		 * 
		 * (skipped value[0], the code, for now - there's an incremental id for each item at the moment if you
		 * need to use that.
		*/
		String line;
		try {
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String code = values[0];
				String name = values[1];
				double price = Double.parseDouble(values[2]);
				int quantity = Integer.parseInt(values[3]);
		        machine.addItem(code, name, price, quantity);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ArrayList of items that has just been populated
		ArrayList<Item> itemList = machine.getItemList();
		
		/* Use the below line to test each item. The number is the index of the item, then you can 
		 * use an item method e.g. getName, getPrice getQuantity to get a field.
		System.out.println(itemList.get(5).getName());
		*/
		
		String userOptions[] = new String[]{"Buy item", "Check prices", "Cancel", "Stock Mode (key required)", "Quit"};
		
		Menu sysMenu = new Menu("\n\nUser Mode", userOptions);
		int choice;
		int quitOption = options.length;
		do {
			choice = sysMenu.getChoice();
			if ( choice == quitOption ) {
				break;
			}
			processChoiceUser(choice);
		}
		while(true);
		
		System.out.print("Exit Application");
	}

	//validates password for entry into stock mode
	private static void key() throws IOException {
		String password = "1";
		System.out.println("Please enter the key: ");
		input.nextLine();
		String inputPassword = input.nextLine();
		
		if (inputPassword.equals(password)) {
			stockModeApplication();
		} else {
			System.out.print("Wrong key");
			application();
		}		
	}

	private static void processChoiceStock(int selection) throws IOException {
		switch( selection ) {
		case 1 :	
			// method to set item price
			break;
		case 2 :	
			// method to check item prices 
			break;
		case 3:
			// method to change item name
			break;
		case 4:
			// method to check/change item quantity
			break;
		case 5:
			// method to empty changebox
			break;
		case 6:
			// method to insert change by the owner
			break;
		case 7:
			if (!Stock.addItem()); {
				break;
			}
				
		case 8:
			System.out.print("Switching to user mode...");
			application();
			break;
		default :	
			System.out.println("Error - Invalid Option.");
		//possibly add choice for change password?
		}
	}
	
	private static void stockModeApplication () throws IOException {
		String stockOptions[] = new String[]{"Set item price", "Check prices", "Change item name", "Check/change item quantity", "Empty changebox", "Insert change", "Add Item", "Quit to user mode"};
		int choice;
		int quitOption = options.length;
		Menu sysMenu = new Menu("Stock Mode", stockOptions);
		
		do {
			choice = sysMenu.getChoice();
			if ( choice == quitOption ) {
				break;
			}
			processChoiceStock(choice);
		}
		while(true);
   }
}
