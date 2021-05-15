package KitchenOrders;

public class OverflowShelf extends Shelf{

	public OverflowShelf(int id, String name, String temperature, int capacity) {
		super(id, name, temperature, capacity);
	}
	public boolean putOrder(Order order) {
			
		if (list.size() < capacity) {
			list.add(order);		
			return true;
		} else {
			// try move to overflowShelf
			return false;
		}
		
	}
}
