import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Log implements LogInterface {
	public static int writeToLog(String payMeth, String prodType) throws IOException {
		final String CSVFILEPATH = "Log.csv";
//		final String HEADER = "TRANSACTION_ID,DATE,TIME,PAYMENT_METHOD,PRODUCT_TYPE,LOCATION,MACHINE_ID";
		final String MACHINE_ID = "209";
		ID id = new ID();

		// construct string to be written
		int lastID = 0;
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(CSVFILEPATH));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// consumes header line
		br.readLine();

		// saves last ID
		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			lastID = Integer.parseInt(values[0]);
		}
		
		// assign transactionID and finalID
		if (lastID == 0) {
			id.setid(1);
		} else {
			id.setid(lastID+1);
		}
		String transactionID = String.valueOf(id.getid());
		int finalID = id.getid();
		

		String date = DateTime.getDate();
		String time = DateTime.getTime();
		String location = "(" + Location.getXcoord() + "," + Location.getYcoord() + ")";

		String LineToWrite = transactionID + "," + date + "," + time + "," + payMeth + "," + prodType + "," + location
				+ "," + MACHINE_ID + "\n";

		// write to file
		FileWriter myWriter = new FileWriter(CSVFILEPATH, true);
		myWriter.append(LineToWrite);

		// close writer
		myWriter.flush();
		myWriter.close();
		
		return finalID;

	}

	public static void sendToServer() throws IOException {
//		final String HEADER = "TRANSACTION_ID,DATE,TIME,PAYMENT_METHOD,PRODUCT_TYPE,LOCATION,MACHINE_ID";
		// writes whole file to a backup file
		final String WRITEFILEPATH = "ServerLog.csv";
		FileWriter newWriter = new FileWriter(WRITEFILEPATH);
		final String READCSVFILEPATH = "Log.csv";

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(READCSVFILEPATH));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// reads each line to an arrayList
		ArrayList<String> lines = new ArrayList<String>();
		String line;
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}

		// writes lines to backup server file
		for (int i = 0; i < lines.size(); i++) {
			newWriter.write(lines.get(i));
			newWriter.write("\n");
		}
		newWriter.flush();
		newWriter.close();

	}

	public static void transactionMade(String payMeth, String prodType) throws IOException {
		// call to write
		int finalID = writeToLog(payMeth, prodType);
		// check transaction ID
		// if ID is multiple of 10 send to server
		if (finalID % 10 == 0) {
			sendToServer();
		}
	}

}
