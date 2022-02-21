import java.util.*;

public class Machine implements IMachine {
	private ArrayList<Item> itemData = new ArrayList<Item>();

	@Override
	public void purchaseItem(int itemId) {
		for (Item item : itemData) {
		    if (item.getid() == itemId) {
		      item.purchase();
		    }
		}
	}

	@Override
	public boolean addItem(String name, double price, int quantity) {
		Item newitem = new Item(name, price, quantity);
		for (Item item : itemData) {
			if (newitem.getName().equals((item).getName())) {
				return false;
			}
		}
		itemData.add(newitem);
		return true;
	}
	
	@Override 
	public ArrayList<Item> getItemList() {
		return itemData;
	}
	
}
