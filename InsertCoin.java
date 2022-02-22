import java.util.Scanner;

public class InsertCoin {

	static Scanner input = new Scanner(System.in);
	private static double userTotal;
	
	public static void main(String[] args) {
		addCoins();
	}

	public static double addCoins() {
		boolean correctCoin = true;
		//double userTotal = 0;
		while (correctCoin = true) {
			System.out.println("Please input a coin in the format '£.pp': ");
			double coin = input.nextDouble();
			if (coin < 0.10 || (coin != 0.10 && coin != 0.20 && coin != 0.50 && coin != 1.00 && coin != 2.00)) {
				System.out.println("Invalid coin entered.");
				//System.out.println("Total to be returned: " + String.format("%.2f", userTotal));
				changeBox.dispenseChange(userTotal);
				correctCoin = false;
				break;
			} else {
				userTotal += coin;
				//System.out.println(userTotal);
				//changeTubes(coin);
			}

			System.out.println("Current user total: " + String.format("%.2f", userTotal));
		}
		return userTotal;
	}
	
	/*public static double changeTubes(double coin) {
		if (coin == 2) {
			changeBox.checkChange();
		} else if (coin == 1) {
			// £1 tube code
		} else if (coin == 0.5) {
			// 50p tube code
		} else if (coin == 0.2) {
			// 20p tube code
		} else if (coin == 0.1) {
			// 10p tube code
		}
		return coin;
	}*/
	
}

