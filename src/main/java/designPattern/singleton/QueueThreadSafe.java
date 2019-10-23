package designPattern.singleton;

import org.junit.Test;

public class QueueThreadSafe {

	private static final int capacity = 5;  
	int arr[] = new int[capacity];  
	int size = 0;  
	int top = -1;  
	int rear = 0;  
	  
	public synchronized void push(int pushedElement) {  
		if (top < capacity - 1) {  
			top++;  
			arr[top] = pushedElement;  
			System.out.println("Element " + pushedElement + " is pushed to Queue !");  
			display();  
		} else {  
			System.out.println("Overflow !");  
		}  
	}  
	  
	public synchronized void pop() {  
		if (top >= rear) {  
			rear++;  
			System.out.println("Pop operation done !");  
			display();  
		} else {  
			System.out.println("Underflow !");  
		}  
	 }
	  
	public void display() {  
		if (top >= rear) {  
			System.out.println("Elements in Queue : ");  
			for (int i = rear; i <= top; i++) {  
				System.out.println(arr[i]);  
			}  
		}  
	}  
	
	@Test
	public void testing() {  
		QueueThreadSafe queueThreadSafe = new QueueThreadSafe();  
		queueThreadSafe.pop();  
		queueThreadSafe.push(23);  
		queueThreadSafe.push(2);  
		queueThreadSafe.push(73);  
		queueThreadSafe.push(21);  
		queueThreadSafe.pop();  
		queueThreadSafe.pop();  
		queueThreadSafe.pop();  
		queueThreadSafe.pop();  
	}
	  
} 


