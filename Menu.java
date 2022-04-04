

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	static String CSVFILEPATH = "Stock.csv";
	static double balance = 0;
	static double cardBalance = 0;
	static Machine machine = new Machine();
	private static String options[]; // array of strings representing user options
	private String title; // menu title
	private static Scanner input = new Scanner(System.in); // for KB input
	public static String choice;
	private static String paymentType;

	/**
	 * Constructor for class
	 * 
	 * @param title   - the menu title
	 * @param options - the options for user selection
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		read();
		application();
	}

	private static void read() {
		ArrayList<Item> items = null;
		try {
			items = File.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < items.size(); i++) {
			machine.addItem(items.get(i).getCode(), items.get(i).getName(), items.get(i).getPrice(),
					items.get(i).getQuantity(), items.get(i).getType());
		}

	}

	static void application() throws IOException {
		// Options for user
		String userOptions[] = new String[] { "Buy item", "Check prices", "Eject change", "Stock Mode (key required)",
				"Quit" };
		// Title + current balance as a header
		Menu userMenu = new Menu("\n\nUser Mode\tBalance: " + String.format("%.2f", balance), userOptions);
		int choice;
		int quitOption = userOptions.length;
		do {
			choice = userMenu.getChoice();
			processChoiceUser(choice);
			if (choice == quitOption) {
				break;
			}

		} while (true);
		// when quit option is selected
		System.out.print("Application Terminated.");
		File.writeArrayToFile();
		System.exit(1);
	}

	private static void listItems() throws IOException {
		// loop to print each item
		for (Item item : machine.getItemList()) {
			System.out.println(item.toString());
		}
	}

	private static boolean codeCheck(String choice) throws IOException {
		// Verifies code matches a place in the machine
		if (choice.charAt(0) == 'A' || choice.charAt(0) == 'B' || choice.charAt(0) == 'C' || choice.charAt(0) == 'D'
				|| choice.charAt(0) == 'E') {
			if (choice.charAt(1) == '1' || choice.charAt(1) == '2' || choice.charAt(1) == '3' || choice.charAt(1) == '4'
					|| choice.charAt(1) == '5') {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	private static boolean purchase() throws IOException {
		System.out.print("\n");
		System.out.println("Please Enter the Location of the item you would like.");
		input = new Scanner(System.in);
		choice = input.next();
		if (!codeCheck(choice)) {
			System.out.print("Invalid code\n");
			return true;
		}
		for (Item item : machine.getItemList()) {
			if (choice.equals(item.getCode()) /*
												 * && (InsertCoin.addCoins().paymentType.equals("Cash")) ||
												 * paymentType.equals("cash")
												 */) {
				System.out.println("Price: " + String.format("%.2f", item.getPrice()));
				System.out.println("Current balance: " + String.format("%.2f", balance));

				System.out.println("Do you want to pay by cash or card? ");
				String decision = input.next();
				// checks quantity
				if (item.getQuantity() == 0) {
					System.out.println("Item depleted");
					return true;
				}

				// if cash balance already exceeds price
				if (item.getPrice() <= balance) {
					balance -= item.getPrice();
					item.purchase();
					System.out.println("Item purchased");
					return true;
				}
				

				if (decision.equals("Cash") || decision.equals("cash")) {
					// prompts coin until balance matches price
					while (item.getPrice() > balance) {
						balance += InsertCoin.addCoins();
						System.out.println("Current balance: " + String.format("%.2f", balance));
					}
					balance -= item.getPrice();
					item.purchase();
					System.out.println("Item purchased");
					paymentType = "Cash";
					System.out.println(getPaymentType());
					System.out.println("Current balance: " + String.format("%.2f", balance));
					return true;
				} else if (decision.equals("Card") || decision.equals("card")) {
					cardBalance = CardPayment.payment();
					input.nextLine();
					paymentType = "Card";
					System.out.println(getPaymentType());
					// if card balance already exceeds price or not
					if (item.getPrice() <= cardBalance) {
						cardBalance -= item.getPrice();
						item.purchase();
						System.out.println("Item purchased");
						cardBalance = 0;
						return true;
					} else if (item.getPrice() > cardBalance){
						cardBalance = 0;
						System.out.println("Insufficent amount in bank account");
						return true;
					}
					return true;
				}

			}
		}
		return false;
	}

	private static String getPaymentType() {
		return paymentType;
	}

	private static void stockModeApplication() throws IOException {
		String stockOptions[] = new String[] { "Set item price", "Check prices", "Change item name",
				"Check/change item quantity", "Empty changebox", "Insert change", "Quit to user mode" };
		int choice;
		Menu sysMenu = new Menu("Stock Mode", stockOptions);
		do {
			choice = sysMenu.getChoice();
			processChoiceStock(choice);
		} while (true);
	}

	private static void key() throws IOException {
		// validates password for entry into stock mode
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

	private static void processChoiceUser(int selection) throws IOException {
		switch (selection) {
		case 1:
			listItems();
			if (purchase()) {
				System.out.println("Returning to menu...");
				application();
			}
			break;
		case 2:
			listItems();
			break;
		case 3:
			changeBox.dispenseChange(balance);
			break;
		case 4:
			key();
			break;
		case 5:
			System.out.println("Power off.");
			break;
		default:
			System.out.println("Error - Invalid Option.");
		}
	}

	private static void processChoiceStock(int selection) throws IOException {
		switch (selection) {
		case 1:
			if (editPrice())
				System.out.println("Success!\n");
			break;
		case 2:
			listItems();
			break;
		case 3:
			if (editName())
				System.out.println("Success!\n");
			break;
		case 4:
			if (editQuantity())
				System.out.println("Success!\n");
			break;
		case 5:
			changeBox.emptyChangebox();
			break;
		case 6:
			changeBox.refillChangeTubes();
			break;
		case 7:
			System.out.print("Switching to user mode...");
			application();
			break;
		default:
			System.out.println("Error - Invalid Option.");
			// possibly add choice for change password?
		}
	}

	private static boolean editPrice() throws IOException {
		String loc, price;
		listItems();
		System.out.println("\nEnter the location you want to change the price of");
		loc = input.next();
		if (!codeCheck(loc)) {
			System.out.println("Invalid code.");
			return false;
		}
		System.out.println("Enter the new price");
		price = input.next();
		double priced = Double.parseDouble(price);
		for (Item item : machine.getItemList()) {
			if (loc.equals(item.getCode())) {
				item.setPrice(priced);
				return true;

			}
		}
		return false;
	}

	private static boolean editName() throws IOException {
		String loc, name;
		listItems();
		System.out.println("\nEnter the location you want to change the name of");
		loc = input.next();
		if (!codeCheck(loc)) {
			System.out.println("Invalid code.");
			return false;
		}
		System.out.println("Enter the new name");
		name = input.next();
		for (Item item : machine.getItemList()) {
			if (loc.equals(item.getCode())) {
				item.setName(name);
				return true;

			}
		}
		return false;
	}

	private static boolean editQuantity() throws IOException {
		String loc, nq;
		listItems();
		System.out.println("\nEnter the location you want to change the quantity of");
		loc = input.next();
		if (!codeCheck(loc)) {
			System.out.println("Invalid code.");
			return false;
		}
		System.out.println("Enter the new quantity");
		nq = input.next();
		int quantity = Integer.parseInt(nq);
		if (quantity > 20) {
			System.out.println("Exceeds maximum quantity.");
			return false;
		}
		for (Item item : machine.getItemList()) {
			if (loc.equals(item.getCode())) {
				item.setQuantity(quantity);
				return true;

			}
		}
		return false;
	}

	public Menu(String title, String options[]) {
		this.title = title;
		copyOptions(options);
		// input = new Scanner(System.in);
	}

	/**
	 * will initialise the options array by copying contents of data
	 * 
	 * @param data - array of Strings - options for user to select from
	 */
	private void copyOptions(String data[]) {
		if (data != null) {
			options = new String[data.length];
			for (int index = 0; index < data.length; index++) {
				options[index] = data[index];
			}
		} else {
			options = null;
		}
	}

	/**
	 * displays the menu title followed by the user option for selection
	 */
	private void display() {
		if (title != null && options != null) {
			// title and options are valid
			// display title and underline with '+'
			System.out.println(title);
			for (int i = 0; i < title.length(); i++) {
				System.out.print("+");
			}
			System.out.println("\n");
			// display the menu options
			// prefix each with an int starting at 1
			int count = 1;
			for (String str : options) {
				System.out.println(count + ". " + str);
				count++;
			}
			System.out.println();
		} else {
			// title and/or options are not valid
			System.out.println("Menu not defined.");
		}
	}

	/**
	 * Requests and read a user choice
	 * 
	 * @return - the user choice
	 */
	public int getChoice() {
		display();
		System.out.print("Enter choice: ");
		int choice = input.nextInt();
		return choice; // needs validated for string entry.
	}
}