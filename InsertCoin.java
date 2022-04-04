import java.io.IOException;
import java.util.Scanner;

public class InsertCoin {

	static Scanner input = new Scanner(System.in);
	private static double userTotal;

	public static void main(String[] args) throws IOException {
		addCoins();
	}

	public static double addCoins() throws IOException {
		// double userTotal = 0;
		System.out.println("Please enter the currency that you would like to use (Sterling or Euros?): ");
		String choice = input.nextLine();
		if (choice.equals("Sterling") || choice.equals("sterling")) {
			System.out.println("Please input a coin in the format '£.pp': ");
			double coin = input.nextDouble();
			input.nextLine();
			if (coin < 0.10 || (coin != 0.10 && coin != 0.20 && coin != 0.50 && coin != 1.00 && coin != 2.00 && coin != 5.00 && coin != 10.00 && coin != 20.00)) {
				System.out.println("Invalid coin entered.\nReturning to menu...");
				Menu.application();
				return userTotal;
				// Resolve change in purchase method in menu so that that the user total can be
				// compared to price
			}
			userTotal = coin;
			changeBox.storeCoin(coin);
			
			return userTotal;
		} else if (choice.equals("Euros") || choice.equals("euros")) {
			System.out.println("Please input a coin in the format '€.cc': ");
			double euroCoin = input.nextDouble();
			input.nextLine();
			if (euroCoin < 0.10 || (euroCoin != 0.10 && euroCoin != 0.20 && euroCoin != 0.50 && euroCoin != 1.00 && euroCoin != 2.00 && euroCoin != 5.00 && euroCoin != 10.00 && euroCoin != 20.00)) {
				System.out.println("Invalid coin entered.\nReturning to menu...");
				Menu.application();
				return userTotal;
				// Resolve change in purchase method in menu so that that the user total can be
				// compared to price
			} euroCoin = euroCoin * 0.84;
			userTotal = euroCoin;
		} 
		return userTotal;
	}

}
