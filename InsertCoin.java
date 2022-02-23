import java.io.IOException;
import java.util.Scanner;

public class InsertCoin {

	static Scanner input = new Scanner(System.in);
	private static double userTotal;
	
	public static void main(String[] args) throws IOException {
		addCoins();
	}

	public static double addCoins() throws IOException {
		System.out.println("Please input a coin in the format '£.pp': ");
		double coin = input.nextDouble();
		if (coin < 0.10 || (coin != 0.10 && coin != 0.20 && coin != 0.50 && coin != 1.00 && coin != 2.00)) {
			System.out.println("Invalid coin entered.\nReturning to menu...");
			Menu.application();
			return userTotal;
			//Resolve change in purchase method in menu so that that the user total can be compared to price	
		}	
		userTotal = coin;
		return userTotal;	
	}
}