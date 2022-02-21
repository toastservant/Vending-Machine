import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Stock {
	
	static Scanner input = new Scanner(System.in);
	
	public static void setPrice(Item item) {
		
	}
	
	public static void checkPrice(Item item) {
		
	}
	
	public static void changeName(Item item) {
		
	}
	
	public static void Quantity(Item item) {
		
	}
	
	public static void emptyChange() {
		
	}
	
	public static void insertChange() {
		
	}
	
	public static boolean addItem() throws IOException {
		System.out.println("Add a new item:\nEnter name: ");
		String name = "";
		while (name == "") {
			name = input.nextLine();
		}
		System.out.println("Enter price: ");
		double price = Double.parseDouble(input.nextLine());
		System.out.println("Enter quantity: ");
		int quantity = Integer.parseInt(input.nextLine());
		System.out.println("Enter code: ");
		String code = input.nextLine();
		String newitem = (code + "," + name + "," + Double.toString(price) + "," + Integer.toString(quantity));
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("Stock.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			if (name.equalsIgnoreCase(values[1])) {
				return false;
			}
			if (code.equalsIgnoreCase(values[0])) {
				System.out.println("Item already in position. Press y to confirm, n to cancel");
				if (!input.next().equalsIgnoreCase("y")) {
					return false;
				}
				//TODO Replace line in question
			}
		}
		FileWriter csvWriter = new FileWriter("Stock.csv",true); 
		csvWriter.append("\n" + newitem);
		csvWriter.flush();
		csvWriter.close();
		return true;
	}
}
