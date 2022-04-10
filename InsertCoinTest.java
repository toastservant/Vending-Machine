
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsertCoinTest {

	static Scanner input = new Scanner(System.in);
	private static double userTotal;
	static double coin;
	static double euroCoin;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	public static double addCoins() throws IOException {
		// double userTotal = 0;
		System.out.println("Please enter the currency that you would like to use (Sterling or Euros?): ");
		String choice = input.nextLine();
		if (choice.equals("Sterling") || choice.equals("sterling")) {
			System.out.println("Please input a coin in the format '£.pp': ");
			coin = input.nextDouble();
			input.nextLine();
			if (coin < 0.10 || (coin != 0.10 && coin != 0.20 && coin != 0.50 && coin != 1.00 && coin != 2.00 && coin != 5.00 && coin != 10.00 && coin != 20.00)) {
				//System.out.println("Invalid coin entered.\nReturning to menu...");
				return userTotal;
				// Resolve change in purchase method in menu so that that the user total can be
				// compared to price
			}
			userTotal = coin;
			return userTotal;
		} else if (choice.equals("Euros") || choice.equals("euros")) {
			System.out.println("Please input a coin in the format '€.cc': ");
			euroCoin = input.nextDouble();
			input.nextLine();
			if (euroCoin < 0.10 || (euroCoin != 0.10 && euroCoin != 0.20 && euroCoin != 0.50 && euroCoin != 1.00 && euroCoin != 2.00 && euroCoin != 5.00 && euroCoin != 10.00 && euroCoin != 20.00)) {
				//System.out.println("Invalid coin entered.\nReturning to menu...");
				return userTotal;
			} euroCoin = euroCoin * 0.84;
			userTotal = euroCoin;
		} 
		return userTotal;
	}
	
	@Test
	void addCoinTest1() throws IOException {
		System.out.println("About to test addCoins() when an acceptible or unacceptable value of Sterling is entered.");
		addCoins();
		if (coin == 0.10 || coin == 0.20 || coin == 0.50 || coin == 1.00 || coin == 2.00 || coin == 5.00 || coin == 10.00 || coin == 20.00) {
			System.out.println("Successful test");
			System.out.println();
		} else if (coin < 0.10 || (coin != 0.10 && coin != 0.20 && coin != 0.50 && coin != 1.00 && coin != 2.00 && coin != 5.00 && coin != 10.00 && coin != 20.00)){
		fail("Unacceptable coin");
		}
	}
	
	@Test
	void addCoinTest2() throws IOException {
		System.out.println("About to test addCoins() when an acceptible or unacceptable value of Euros is entered.");
		addCoins();
		if (euroCoin == 0.10 * 0.84 || euroCoin == 0.20 * 0.84 || euroCoin == 0.50 * 0.84 || euroCoin == 1.00 * 0.84 || euroCoin == 2.00 * 0.84 || euroCoin == 5.00 * 0.84 || euroCoin == 10.00 * 0.84 || euroCoin == 20.00 * 0.84) {
			System.out.println("Successful test");
			System.out.println();
		} else if (euroCoin < 0.10 * 0.84 || (euroCoin != 0.10 * 0.84 && euroCoin != 0.20 * 0.84 && euroCoin != 0.50 * 0.84 && euroCoin != 1.00 * 0.84 && euroCoin != 2.00 * 0.84 && euroCoin != 5.00 * 0.84 && euroCoin != 10.00 * 0.84 && euroCoin != 20.00 * 0.84)){
		fail("Unacceptable coin");
		}
	}

}
