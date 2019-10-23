package designPattern.singleton;

/**
 * Thread safe Singleton
 * @author Jake
 *
Declare a private static instance of the same class
 */

public class OneSingleton {
	private static OneSingleton instance = null;
	
	// "private" constructor to avoid client applications to use constructor
	// Override the private constructor to avoid any new object creation with new operator.
	private OneSingleton() {}
	
/*	Provide a public static method that will return the singleton class instance variable. 
	If the variable is not initialized (is null) then initialize it or 
	else simply return the instance variable.*/
	public static OneSingleton getInstance(){
		if(instance == null) {
			instance = new OneSingleton();
		}
		return instance;
	}
}
/* In the above code, getInstance() method is not thread safe 
 * i.e multiple threads can access it at the same time and 
 * for the first few threads when the instance variable is not initialized, 
 * multiple threads can enters the "if" loop and create multiple instances and 
 * break our singleton implementation.
*/

/**
 * There are three ways through which we can achieve thread safety.
 * https://github.com/dongliang3571/Java-Notes
 */

/*
1. Create the instance variable at the time of class loading:
Pros:
	Thread safety without synchronization
	Easy to implement

Cons:
	Early creation of resource that might not be used in the application.
	The client application can’t pass any argument, so we can’t reuse it. For example, having a generic singleton class for database connection where client application supplies database server properties.
*/
class FirstSingleton {
	private static FirstSingleton instance = new FirstSingleton(); //<==**++
	private FirstSingleton(){}
	
	public static FirstSingleton getInstance() {
		if(instance == null) {
			instance = new FirstSingleton();
		}
		return instance; 
	}
}

class FirstSingleton1 {

	private FirstSingleton1(){}

	private static class SingletonHolder { //<==**++
        public static final FirstSingleton1 instance = new FirstSingleton1();
    }
    
	public static FirstSingleton1 getInstance() {

		return SingletonHolder.instance; 
	}
}

/*
2. Synchronize the getInstance() method:
Pros:
	Thread safety is guaranteed.
	Client application can pass parameters
	Lazy initialization achieved

Cons:
	Slow performance because of locking overhead.
	Unnecessary synchronization that is not required once the instance variable is initialized.
*/
class SecondSingleton {
	private static SecondSingleton instance = null;
	private SecondSingleton(){}
	
	public static synchronized SecondSingleton getInstance() {  // Notice keyword `synchronized`
		if (instance == null) {
			instance = new SecondSingleton();
		}
		return instance; 
	}
}

/*
3. Use synchronized block inside the if loop:
Pros:
	Thread safety is guaranteed
	Client application can pass arguments
	Lazy initialization achieved
	Synchronization overhead is minimal and applicable only for first few threads when the variable is null.
Cons:
	Extra if condition

	Looking at all the three ways to achieve thread safety, I think third one is the best option and in that case the modified class will look like:
*/
class ThirdSingleton {
	private static ThirdSingleton instance = null;
	// 'lock' has to be static (belong to class Singleton). 
	// If it is not static, client applications can create multiple Asingleton object and use it for access. 
	private ThirdSingleton(){}
	
	private static Object lock = new Object();	
	public static ThirdSingleton getInstance(){
		if(instance == null) {
			synchronized(lock) {
				if (instance == null) 
					instance = new ThirdSingleton();
			}
		}
		return instance; 
	}
}

/*
We can use synchronized keyword in two ways, 
one is to make a complete method synchronized and other way is to create synchronized block.
When a non-static method is synchronized, it locks the Object, if method is static it locks the Class, 
so it’s always best practice to use synchronized BLOCK to lock the only sections of method that needs synchronization.

While creating synchronized block, we need to provide the resource on which lock will be acquired, 
it can be XYZ.class or any Object field of the class.
synchronized(this) will lock the Object before entering into the synchronized block.

When you synchronize a method, you are effectively synchronizing to the object itself. 
In the case of a static method, you're synchronizing to the class of the object. 
So the following two pieces of code execute the same way:

public synchronized int getCount() {
    // ...
}
This is just like you wrote this.

public int getCount() {
    synchronized (this) {
        // ...
    }
}
If you want to control synchronization to a specific object, or 
you only want part of a method to be synchronized to the object, then specify a synchronized block. 
If you use the synchronized keyword on the method declaration, it will synchronize the whole method to the object or class.

 */ 


