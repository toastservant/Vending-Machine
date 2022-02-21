import java.util.ArrayList;

public interface IMachine {
	public void purchaseItem(int itemId);
	public boolean addItem(String name, double price, int quantity);
	ArrayList<Item> getItemList();
}