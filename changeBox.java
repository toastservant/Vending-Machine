import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class changeBox {
	
	public static void main(String[] args) {
		dispenseChange(0.7);
	}
	
	//Variable Declaration
	Scanner sc = new Scanner(System.in);
	public static int[] dispensedCoins = new int[5];
	static int[] availableCoins = new int[5];
	private static int overflow;
	private static Scanner input = new Scanner(System.in);
	
	//Reads and writes to the ChangeTubes.CSV file
	public static void dispenseChange(double remainingMoney) {
	    	readChangeTube();
    		//Calls checkChange to calculate the number of coins to be dispensed, and updates availableCoins
    		availableCoins = checkChange(availableCoins, remainingMoney);
    		writeChangeTube();
	}
	
	public static void readChangeTube() {
		try {
			//Reads available coins from CSV file
	    	BufferedReader br = new BufferedReader(new FileReader("ChangeTubes.csv"));
	    	String line = "";
	    	while ((line = br.readLine()) != null)
	    	{
	    		String[] coinAmount = line.split(",");
	    	
	    		for(int i = 0; i < 5; i++) {
	    			availableCoins[i] = Integer.parseInt(coinAmount[i]);
	    		}
	    		overflow = Integer.parseInt(coinAmount[5]);
	    	}
	    	br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeChangeTube() {
		try {
			//Writes to the CSV the remaining coins in the machine after giving change
	    	BufferedWriter bw = new BufferedWriter(new FileWriter("ChangeTubes.csv"));
			bw.write(String.format("%s,%s,%s,%s,%s,%s", availableCoins[0],availableCoins[1],availableCoins[2],availableCoins[3],availableCoins[4],overflow));
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();		
		}
	}
	
	public static void storeCoin(double coinValue) {
		readChangeTube();
		double[] acceptedCoins = {2.0, 1.0, 0.5,0.2,0.1};
		int coinIndex = -1;
		for(int i = 0; i < acceptedCoins.length; i++) {
			if(coinValue == acceptedCoins[i]) {
				coinIndex = i;
				break;
			}
		}
		if(coinIndex != -1) {
			if(availableCoins[coinIndex] < 20) {
				availableCoins[coinIndex]++;
			}
			else{
				overflow += acceptedCoins[coinIndex] * 100;
			}
			writeChangeTube();
		}
	}
	
	public static double round2dp(double value) {
	    return Math.round(value * 100d) / 100d;
	}
	
	public static void emptyChangebox() {
		readChangeTube();
		System.out.println("Change Emptied: �" + (overflow/100d));
		overflow = 0;
		writeChangeTube();
	}
	
	public static void refillChangeTubes() {
		readChangeTube();
		for(int i = 0; i < availableCoins.length; i++) {
			System.out.println(availableCoins[i]);
		}
		System.out.println("Would you like to refill? \n1: Refill \n-1: Exit");
		input.nextInt();
		if(input.nextInt() == 1) {
			for(int i = 0; i < availableCoins.length; i++) {
				availableCoins[i] = 20;
			}
		}
	}
	
	//Calculates coins to dispense in relation to available coins and change amount
	public static int[] checkChange(int[] availableCoins, double change) {
		//Cascades down coin values highest > lowest to determine number of coins required for change output
		if(change >= 2) {
			dispensedCoins[0] = (int) Math.floor(change / 2); //Divides change by coin value to determine number of corresponding coins
			dispensedCoins[0] = Math.min(dispensedCoins[0], availableCoins[0]); //Dispenses target amount if coins available, else as many as it can
			availableCoins[0] -= dispensedCoins[0]; //Updates availableCoins
			change = round2dp(change - (dispensedCoins[0] * 2)) ; //Decrease the change amount by dispensedCoin multiplied by coin value
		}
		if(change >= 1) {
			dispensedCoins[1] = (int) Math.floor(change / 1);
			dispensedCoins[1] = Math.min(dispensedCoins[1], availableCoins[1]);
			availableCoins[1] -= dispensedCoins[1];
			change = round2dp(change - (dispensedCoins[1] * 1));
			}
		if(change >= 0.5) {
			dispensedCoins[2] = (int) Math.floor(change / 0.5);
			dispensedCoins[2] = Math.min(dispensedCoins[2], availableCoins[2]);
			availableCoins[2] -= dispensedCoins[2];
			change = round2dp(change - (dispensedCoins[2] * 0.5));
		}
		if(change >= 0.2) {
			dispensedCoins[3] = (int) Math.floor(change / 0.2);
			dispensedCoins[3] = Math.min(dispensedCoins[3], availableCoins[3]);
			availableCoins[3] -= dispensedCoins[3];
			change = round2dp(change - (dispensedCoins[3] * 0.2));   
		}
		if(change >= 0.1) {
			dispensedCoins[4] = (int) Math.floor(change / 0.1);
			dispensedCoins[4] = Math.min(dispensedCoins[4], availableCoins[4]);
			availableCoins[4] -= dispensedCoins[4];
			change = round2dp(change - (dispensedCoins[4] * 0.1));
		}
		return availableCoins;
	}
}
