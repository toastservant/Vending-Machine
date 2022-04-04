import java.util.ArrayList;

public interface IMachine {
	void addItem(String code, String name, double price, int quantity, Type type);
	ArrayList<Item> getItemList();

}
