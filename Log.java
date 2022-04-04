import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Log implements LogInterface {
	public static void writeToLog(String payMeth, String prodType) throws IOException {
		final String CSVFILEPATH = "Log.csv";
		final String HEADER = "TRANSACTION_ID,DATE,TIME,PAYMENT_METHOD,PRODUCT_TYPE,LOCATION,MACHINE_ID";
		final String MACHINE_ID = "209";
		ID id = new ID();

		// construct string to be written
		String transactionID = String.valueOf(id.getid());
		id.addtoid();

		String date = DateTime.getDate();
		String time = DateTime.getTime();
		String location = "(" + Location.getXcoord() + "," + Location.getYcoord() + ")";

		String LineToWrite = transactionID + "," + date + "," + time + "," + payMeth + "," + prodType + "," + location
				+ "," + MACHINE_ID + "\n";

		// write to file
		FileWriter myWriter = new FileWriter(CSVFILEPATH);
		myWriter.write(HEADER + "\n");

		// writes previous data
		ArrayList<String> lines = readPrevious();
		for (int i = 0; i < lines.size(); i++) {
			myWriter.write(lines.get(i));
			myWriter.write("\n");
		}

		myWriter.write(LineToWrite);

		// close writer
		myWriter.close();

	}

	public static void sendToServer() throws IOException {
		final String HEADER = "TRANSACTION_ID,DATE,TIME,PAYMENT_METHOD,PRODUCT_TYPE,LOCATION,MACHINE_ID";
		final String CSVFILEPATH = "Log.csv";

		// write to file
		FileWriter myWriter = new FileWriter(CSVFILEPATH);
		myWriter.write(HEADER + "\n");

		// writes previous data
		ArrayList<String> lines = readPrevious();
		for (int i = 0; i < lines.size(); i++) {
			myWriter.write(lines.get(i));
			myWriter.write("\n");
		}

		// write line to say its being uploaded
		myWriter.write("-----------FILE UPLOADED TO SERVER----------");
		myWriter.close();

		// writes whole file to a backup file
		final String NEWFILEPATH = "ServerLog.csv";
		FileWriter newWriter = new FileWriter(NEWFILEPATH);
		
		newWriter.write(HEADER + "\n");

		for (int i = 0; i < lines.size(); i++) {
			newWriter.write(lines.get(i));
			newWriter.write("\n");
		}
		newWriter.close();

	}

	public static void transactionMade(String payMeth, String prodType) throws IOException {
		ID id = new ID();
		// call to write
		writeToLog(payMeth, prodType);
		// check transaction ID
		// if ID is multiple of 10 send to server
		if (id.getid() % 10 == 0) {
			sendToServer();
		}
	}

	private static ArrayList<String> readPrevious() throws IOException {
		ID id = new ID();
		final String CSVFILEPATH = "Log.csv";

		// read in from file to not overwrite previous entries
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(CSVFILEPATH));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// consumes header line
		br.readLine();

		// reads each line to an arrayList
		ArrayList<String> lines = new ArrayList<String>();
		String line;
		ArrayList<Integer> IDS = new ArrayList<>();
		try {
			while ((line = br.readLine()) != null) {
				System.out.println("made it here lol");
				lines.add(line);
				String[] values = line.split(",");
				IDS.add(Integer.parseInt(values[0]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get last id and set it if not null
		if (IDS.size() != 0) {
			id.setid(IDS.get(IDS.size() - 1) + 1);
		}
		
		System.out.println(lines);

		return lines;

	}
}
