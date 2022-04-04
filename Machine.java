import java.util.*;

public class Machine implements IMachine {
	private ArrayList<Item> itemData = new ArrayList<Item>();

	@Override
	public void addItem(String code, String name, double price, int quantity, Type type) {
		Item newitem = new Item(code, name, price, quantity, type);
		itemData.add(newitem);
	}
	
	@Override 
	public ArrayList<Item> getItemList() {
		return itemData;
	}
	
}