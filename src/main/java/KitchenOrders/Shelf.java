package KitchenOrders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Shelf {
	public int capacity;
	public String name, temperature;
	public int id;
	public List<Order> list;
	
	public Shelf(int id, String name, String temperature, int capacity) {
		this.id = id;
		this.name = name;
		this.temperature = temperature;
		this.capacity = capacity;
		list = new ArrayList<>();
	}
	public boolean putOrder(Order order) {
		
		if(availableToPut()) {
			list.add(order);
			displayMsg(order);
			return true;
		} else {
			return false;
		}
	}
	public int getNumberOfOrders() {
		return list.size();
	}
	public boolean availableToPut() {
		return list.size() < capacity;
	}
	private void displayMsg(Order order) {
		System.out.println("Order [id:" + order.id+ ", temp: " + order.temp + 
				"] was added to " + this.temperature + " Shelf");
	}
}
