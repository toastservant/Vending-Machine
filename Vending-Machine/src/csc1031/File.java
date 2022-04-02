package csc1031;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class File {
	static Machine machine = new Machine();
	public static ArrayList<Item> readFile() throws IOException {
		final String CSVFILEPATH = "Stock.csv";
		// reads CSV file
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(CSVFILEPATH));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// consumes header line
		br.readLine();

		// splits each line and adds an item to the machine instance arraylist
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
			return machine.getItemList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void writeArrayToFile() {
		// Writes to Stock.csv when program is terminated
		final String CSVFILEPATH = "Stock.csv";
		try {
			FileWriter myWriter = new FileWriter(CSVFILEPATH);
			myWriter.write("Location,Product Name,Price,Quantity");
			for (Item item : machine.getItemList()) {
				myWriter.write("\n");
				myWriter.write(item.getCode() + "," + item.getName() + "," + String.format("%.2f", item.getPrice())
						+ "," + item.getQuantity());

			}
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
