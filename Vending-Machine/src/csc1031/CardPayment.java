package csc1031;

import java.io.IOException;
import java.util.Scanner;

public class CardPayment {

	static Scanner input = new Scanner(System.in);
	static Machine machine = new Machine();
	private static double userTotal;

	private String bankName;
	private double balance;
	private int pin;
	private static String bankInput;

	public CardPayment(String bankName, double balance, int pin) {
		this.bankName = bankName;
		this.balance = balance;
		this.pin = pin;
	}

	public void setBankname(String bankName) {
		this.bankName = bankName;
	}

	/*
	 * @Param model
	 * 
	 * @Return
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/*
	 * @Param price
	 * 
	 * @Return
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getBankName() {
		return bankName;
	}

	/*
	 * @Return
	 */
	public double getBalance() {
		return balance;
	}

	/*
	 * @Return
	 */
	public int getPin() {
		return pin;
	}

	static CardPayment card1 = new CardPayment("Halifax", 133.90, 8830);
	static CardPayment card2 = new CardPayment("Santander", 0.00, 1267);
	static CardPayment card3 = new CardPayment("American Express", 10.00, 4902);
	static CardPayment card4 = new CardPayment("Mastercard", 550.34, 5983);
	static CardPayment card5 = new CardPayment("Tesco Bank", -12.50, 3232);

	public static double payment() throws IOException {
		String bankName = "";
		double balance = 0;
		int pin = 0;
		CardPayment cardUsed = new CardPayment(bankName, balance, pin);
		System.out.println("What bank card are you using? ");
		bankInput = input.nextLine();
		if (bankInput.equals("Halifax") || bankInput.equals("halifax")) {
			// cardUsed = new InsertCoin (card1.getBankName(), card1.getBalance(),
			// card1.getPin());
			cardUsed.setBankname(card1.getBankName());
			cardUsed.setBalance(card1.getBalance());
			cardUsed.setPin(card1.getPin());
			System.out.println(cardUsed.balance);
		} else if (bankInput.equals("Santander") || bankInput.equals("santander")) {
			cardUsed.setBankname(card2.getBankName());
			cardUsed.setBalance(card2.getBalance());
			cardUsed.setPin(card2.getPin());
			System.out.println(cardUsed.balance);
		} else if (bankInput.equals("American Express") || bankInput.equals("american express")) {
			cardUsed.setBankname(card3.getBankName());
			cardUsed.setBalance(card3.getBalance());
			cardUsed.setPin(card3.getPin());
			System.out.println(cardUsed.balance);
		} else if (bankInput.equals("Master Card") || bankInput.equals("master card")) {
			cardUsed.setBankname(card4.getBankName());
			cardUsed.setBalance(card4.getBalance());
			cardUsed.setPin(card4.getPin());
			System.out.println(cardUsed.balance);
		} else if (bankInput.equals("Tesco Bank") || bankInput.equals("tesco bank")) {
			cardUsed.setBankname(card5.getBankName());
			cardUsed.setBalance(card5.getBalance());
			cardUsed.setPin(card5.getPin());
			System.out.println(cardUsed.balance);
		} else {
			System.out.println("Card doesn't exist");
			Menu.application();
		}

		System.out.println("Please enter the 4-digit pin of your card: ");
		int pinCode = input.nextInt();
		input.nextLine();
		//double newBalance;

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
}

