import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class changeBoxTest1 {

	@Test
	//Tests expected behaviour of checkChange method with valid inputs
	void checkChange() {
		int[] availableCoins = {20,20,20,20,20};
		double change = 0.7;
		int[] results = changeBox.checkChange(availableCoins,change);
		
		assertArrayEquals(new int[] {20,20,19,19,20}, results);
	}
	
	@Test
	//Tests the checkChange method using a negative double
	//No coins are removed from the CSV as this is an invalid input
	void checkChangeNegativeChange() {
		int[] availableCoins = {20,20,20,20,20};
		double change = -0.7;
		int[] results = changeBox.checkChange(availableCoins,change);
		
		assertArrayEquals(availableCoins, results);
	}
}
