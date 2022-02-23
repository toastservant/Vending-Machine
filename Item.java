public class Item implements interfaceItem {
	private int id;
	static int nextId = 1;
	private String code;
	private String name;
	private double price;
	private int quantity;

	public Item(String newCode, String newName, double newPrice, int newQuantity) {
		setCode(newCode);
		setName(newName);
		setPrice(newPrice);
		setQuantity(newQuantity);
		id = nextId;
		nextId +=1;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
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

	public int getid() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String toString() {
		return this.getCode() + " " + this.getName() + " £" + String.format("%.2f", this.getPrice()) + "p Currently " + this.getQuantity() + " in Stock.";
	}
	
	
}
