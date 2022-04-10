package csc1031;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardPaymentTesting {

	static Scanner input = new Scanner(System.in);
	static Machine machine = new Machine();
	private static double userTotal;
	private static String bankInput = "";

	private static String bankName;
	private static double balance;
	private static int pin;
	private static int pinCode;
	private static CardPayment cardUsed = new CardPayment(bankName, balance, pin);

	static CardPayment card1 = new CardPayment("Halifax", 133.90, 8830);
	static CardPayment card2 = new CardPayment("Santander", 0.00, 1267);
	static CardPayment card3 = new CardPayment("American Express", 10.00, 4902);
	static CardPayment card4 = new CardPayment("Mastercard", 550.34, 5983);
	static CardPayment card5 = new CardPayment("Tesco Bank", -12.50, 3232);

	@BeforeEach
	void setUp() throws Exception {

	}

	public static double payment() throws IOException {
		System.out.println("What bank card are you using? ");
		bankInput = input.nextLine();
		if (bankInput.equals("Halifax") || bankInput.equals("halifax")) {
			// cardUsed = new InsertCoin (card1.getBankName(), card1.getBalance(),
			// card1.getPin());
			cardUsed.setBankname(card1.getBankName());
			cardUsed.setBalance(card1.getBalance());
			cardUsed.setPin(card1.getPin());
		} else if (bankInput.equals("Santander") || bankInput.equals("santander")) {
			cardUsed.setBankname(card2.getBankName());
			cardUsed.setBalance(card2.getBalance());
			cardUsed.setPin(card2.getPin());
		} else if (bankInput.equals("American Express") || bankInput.equals("american express")) {
			cardUsed.setBankname(card3.getBankName());
			cardUsed.setBalance(card3.getBalance());
			cardUsed.setPin(card3.getPin());
		} else if (bankInput.equals("Master Card") || bankInput.equals("master card")) {
			cardUsed.setBankname(card4.getBankName());
			cardUsed.setBalance(card4.getBalance());
			cardUsed.setPin(card4.getPin());
		} else if (bankInput.equals("Tesco Bank") || bankInput.equals("tesco bank")) {
			cardUsed.setBankname(card5.getBankName());
			cardUsed.setBalance(card5.getBalance());
			cardUsed.setPin(card5.getPin());
		} else {
			System.out.println("Card doesn't exist");
			Menu.application();
		}

		System.out.println("Please enter the 4-digit pin of your card: ");
		pinCode = input.nextInt();
		input.nextLine();
		// double newBalance;

		if (pinCode == cardUsed.getPin()) {
			System.out.println("Pin code accepted");
			userTotal = cardUsed.getBalance();
		} else {
			System.out.println("Incorrect pin code");
			Menu.application();
			userTotal = 0;
		}
		return userTotal;
	}

	@Test
	void cardPaymentTest1() throws IOException {
		System.out.println("About to test when a credit card is accepted by the machine");
		payment();
		if (bankInput.equals("Halifax") || bankInput.equals("halifax") || bankInput.equals("Santander")
				|| bankInput.equals("santander") || bankInput.equals("American Express")
				|| bankInput.equals("american express") || bankInput.equals("Mastercard")
				|| bankInput.equals("mastercard") || bankInput.equals("Tesco Bank") || bankInput.equals("tesco bank")) {
			if (pinCode == cardUsed.getPin()) {
				System.out.println("Successful test");
			} else {
				fail("Incorrect pin code");
			}
		} else {
			fail("Name of credit card does not exist");
		}
	}
}
