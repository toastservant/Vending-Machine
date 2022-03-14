import java.io.FileWriter;
import java.io.IOException;

public class Log implements LogInterface{
	public static void writeToLog() throws IOException {
		final String CSVFILEPATH = "Log.csv";
		final String HEADER = "TRANSACTION_ID,DATE,TIME,PAYMENT_METHOD,PRODUCT_TYPE,LOACTION,MACHINE_ID";
		
		FileWriter myWriter = new FileWriter(CSVFILEPATH);
		myWriter.write(HEADER);
	}
	
	public static void sendToServer() {
		
	}
	
	public static void transactionMade() throws IOException {
		//call to write
		writeToLog();
		//check transaction ID
		
		//if ID is multiple of 10 send to server
	}
}
