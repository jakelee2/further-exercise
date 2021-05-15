package KitchenOrders;

public class Order {

	String id, name, temp;
	long shelfLife;
	double decayRate, orderAge;
	
	Order(String id, String name, String temp, long shelfLife, double decayRate) {
		this.id = id;
		this.name = name;
		this.temp = temp;
		this.shelfLife = shelfLife;
		this.decayRate = decayRate;
	}
	
}
