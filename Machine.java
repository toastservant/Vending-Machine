import java.util.*;

public class Machine implements IMachine {
	private ArrayList<Item> itemData = new ArrayList<Item>();

	@Override
	public void addItem(String code, String name, double price, int quantity) {
		Item newitem = new Item(code, name, price, quantity);
		itemData.add(newitem);
	}
	
	@Override 
	public ArrayList<Item> getItemList() {
		return itemData;
	}
	
}