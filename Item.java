public class Item implements interfaceItem {
	private String name;
	private double price;
	private int quantity;

	public Item(String newName, double newPrice, int newQuantity) {
		setName(newName);
		setPrice(newPrice);
		setQuantity(newQuantity);
	}
	
	private void setName(String name) {
		this.name = name;
	}

	private void setPrice(double price) {
		this.price = price;
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public void purchase() {
		quantity -= 1;
	}
	
}
