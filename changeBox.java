import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class changeBox {
	
	//Variable Declaration
	Scanner sc = new Scanner(System.in);
	public static int[] dispensedCoins = new int[5];
	
	//Reads and writes to the ChangeTubes.CSV file
	public static void dispenseChange(double remainingMoney) {
		String line = "";
		
	    try {
	    	//Reads available coins from CSV file
	    	BufferedReader br = new BufferedReader(new FileReader("ChangeTubes.csv"));
	    	int[] availableCoins = new int[5];
	    	while ((line = br.readLine()) != null)
	    	{
	    		String[] coinAmount = line.split(",");
	    	
	    		for(int i = 0; i < 5; i++) {
	    			availableCoins[i] = Integer.parseInt(coinAmount[i]);
	    		}
	    		//Calls checkChange to calculate the number of coins to be dispensed, and updates availableCoins
	    		availableCoins = checkChange(availableCoins, remainingMoney);
	    	}
	    	br.close();
	    	
	    	//Writes to the CSV the remaining coins in the machine after giving change
	    	BufferedWriter wr = new BufferedWriter(new FileWriter("D:\\ChangeTubes.csv"));
			wr.write(String.format("%s,%s,%s,%s,%s", availableCoins[0],availableCoins[1],availableCoins[2],availableCoins[3],availableCoins[4]));
			wr.close();
	    }
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	//Calculates coins to dispense in relation to available coins and change amount
	private static int[] checkChange(int[] availableCoins, double change) {
		//Cascades down coin values highest > lowest to determine number of coins required for change output
		if(change >= 2) {
			dispensedCoins[0] = (int) (change / 2); //Divides change by coin value to determine number of corresponding coins
			dispensedCoins[0] = Math.min(dispensedCoins[0], availableCoins[0]); //Dispenses target amount if coins available, else as many as it can
			availableCoins[0] -= dispensedCoins[0]; //Updates availableCoins
			change -= dispensedCoins[0] * 2; //Decrease the change amount by dispensedCoin multiplied by coin value
		}
		if(change >= 1) {
			dispensedCoins[1] = (int) (change / 1);
			dispensedCoins[1] = Math.min(dispensedCoins[1], availableCoins[1]);
			availableCoins[1] -= dispensedCoins[1];
			change -= dispensedCoins[1] * 1;
			}
		if(change >= 0.5) {
			dispensedCoins[2] = (int) (change / 0.5);
			dispensedCoins[2] = Math.min(dispensedCoins[2], availableCoins[2]);
			availableCoins[2] -= dispensedCoins[2];
			change -= dispensedCoins[2] * 0.5;
		}
		if(change >= 0.2) {
			dispensedCoins[3] = (int) (change / 0.2);
			dispensedCoins[3] = Math.min(dispensedCoins[3], availableCoins[3]);
			availableCoins[3] -= dispensedCoins[3];
			change -= dispensedCoins[3] * 0.2;   
		}
		if(change >= 0.1) {
			dispensedCoins[4] = (int) (change / 0.1);
			dispensedCoins[4] = Math.min(dispensedCoins[4], availableCoins[4]);
			availableCoins[4] -= dispensedCoins[4];
			change -= dispensedCoins[4] * 0.1;
		}
		return availableCoins;
	}
}
