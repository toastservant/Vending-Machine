import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class productList {

	private static String[] readFileToArray() {
		String CSVFILEPATH = "stock.csv";
		File myFile = new File(CSVFILEPATH);

		// declares scanner
		Scanner mySc = null;
		try {
			mySc = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// creates an array to hold the data
		String[] products = new String[26];

		int count = 0;

		// reads in the data
		while (mySc.hasNextLine()) {
			products[count] = mySc.nextLine();
			count++;
		}

		return products;
	}

	public static void displayList() {
		String[] products = readFileToArray();

		// prints out the data
		String[] Header = products[0].split(",");
		System.out.println(Header[0] + " " + Header[1] + " " + Header[2] + " " + Header[3]);
		for (int i = 1; i < products.length; i++) {
			String[] parts = products[i].split(",");
			System.out.println(parts[0] + " " + parts[1] + " " + parts[2] + "p Currently " + parts[3] + " in Stock.");
		}
	}

	public static void editName(String location, String newName) {
		String[] products = readFileToArray();
		String[] productLocation = new String[25];

		// creates a new array that is a list of all locations
		for (int i = 1; i < products.length; i++) {
			productLocation[i - 1] = products[i].substring(0, 2);
		}

		// search for what position the chosen location is
		int position = 0;
		String newString = "";
		String oldString = "";
		for (int i = 0; i < productLocation.length; i++) {
			if (productLocation[i].equals(location)) {
				oldString = products[i + 1];
				position = i;
			}
		}

		// split string into parts
		String[] parts = oldString.split(",");

		// create a new string with the new name and replace that in the products array
		newString = parts[0] + "," + newName + "," + parts[2] + "," + parts[3];
		products[position + 1] = newString;

		// write the products array to the file now amended with the new product name
		writeArrayToFile(products);
	}

	private static void writeArrayToFile(String[] products) {
		String CSVFILEPATH = "stock.csv";
		try {
			FileWriter myWriter = new FileWriter(CSVFILEPATH);

			for (int i = 0; i < products.length; i++) {
				myWriter.write(products[i]);
				myWriter.write("\n");
			}
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void reduceQuantity(String location) {
		String[] products = readFileToArray();
		String[] productLocation = new String[25];

		// creates a new array that is a list of all locations
		for (int i = 1; i < products.length; i++) {
			productLocation[i - 1] = products[i].substring(0, 2);
		}

		// search for what position the chosen location is
		int position = 0;
		String newString = "";
		String oldString = "";
		for (int i = 0; i < productLocation.length; i++) {
			if (productLocation[i].equals(location)) {
				oldString = products[i + 1];
				position = i;
			}
		}
		
		// split string into parts
		String[] parts = oldString.split(",");
		
		// convert quantity to integer
		int q = Integer.parseInt(parts[3]);
		
		// check its not less than 1
		if (q < 1) {
			System.out.println("Cannot reduce quantity as it is currently empty");
		} else {
			q = q-1;
			// create new string and write it
			newString = parts[0] + "," + parts[1] + "," + parts[2] + "," + String.valueOf(q);
			products[position + 1] = newString;
			
			writeArrayToFile(products);
		}
		
		
	}

	public static void setQuantity(String location, String quantity) {
		String[] products = readFileToArray();
		String[] productLocation = new String[25];

		// creates a new array that is a list of all locations
		for (int i = 1; i < products.length; i++) {
			productLocation[i - 1] = products[i].substring(0, 2);
		}

		// search for what position the chosen location is
		int position = 0;
		String newString = "";
		String oldString = "";
		for (int i = 0; i < productLocation.length; i++) {
			if (productLocation[i].equals(location)) {
				oldString = products[i + 1];
				position = i;
			}
		}

		// split string into parts
		String[] parts = oldString.split(",");

		// create a new string with the new name and replace that in the products array
		newString = parts[0] + "," + parts[1] + "," + parts[2] + "," + quantity;
		products[position + 1] = newString;

		
		// write the products array to the file now amended with the new product name
		writeArrayToFile(products);


	}

	public static void editPrice(String location, String price) {
		String[] products = readFileToArray();
		String[] productLocation = new String[25];

		// creates a new array that is a list of all locations
		for (int i = 1; i < products.length; i++) {
			productLocation[i - 1] = products[i].substring(0, 2);
		}

		// search for what position the chosen location is
		int position = 0;
		String newString = "";
		String oldString = "";
		for (int i = 0; i < productLocation.length; i++) {
			if (productLocation[i].equals(location)) {
				oldString = products[i + 1];
				position = i;
			}
		}

		// split string into parts
		String[] parts = oldString.split(",");

		// create a new string with the new name and replace that in the products array
		newString = parts[0] + "," + parts[1] + "," + price + "," + parts[3];
		products[position + 1] = newString;

		
		// write the products array to the file now amended with the new product name
		writeArrayToFile(products);

	}

	public static String makeSelection() {
		displayList();
		System.out.print("\n");
		System.out.println("Please Enter the Location of the item you would like.");
		Scanner input = new Scanner(System.in);
		String choice = input.next();
		
		if (choice.charAt(0) == 'A' || choice.charAt(0) == 'B' || choice.charAt(0) == 'C' || choice.charAt(0) == 'D' || choice.charAt(0) == 'E'){
			if (choice.charAt(1) == '1' || choice.charAt(1) == '2' || choice.charAt(1) == '3' || choice.charAt(1) == '4' || choice.charAt(1) == '5') {
				return choice;
			} else {
				return "Invalid";
			}
		} else {
			return "Invalid";
		}
		
	}
}
