package csc1031;

import java.util.ArrayList;

public interface IMachine {
	void addItem(String code, String name, double price, int quantity);
	ArrayList<Item> getItemList();

}
