package designPattern.singleton;
/**
** Eager initialization
In eager initialization, the instance of Singleton Class is created at the time of class loading, 
this is the easiest method to create a singleton class but it has a drawback that 
instance is created even though client application might not be using it.

 */
public class EagerInitializedSingleton {
	//instance is created even though client application might not be using it
	private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();
	
	// "private" constructor to avoid client applications to use constructor
	private EagerInitializedSingleton(){}
	
	public static EagerInitializedSingleton getInstance(){
		return instance;
	}
}

/*
If your singleton class is not using a lot of resources, this is the approach to use. 
But in most of the scenarios, Singleton classes are created for resources such as 
File System, Database connections etc and we should avoid the instantiation 
until unless client calls the getInstance method. 
Also this method doesn’t provide any options for exception handling.
*/

/**
 * Lazy Initialization
 *
 * Lazy initialization method to implement 
 * Singleton pattern creates the instance in the global access method. 
 */
class LazyInitializedSingleton {
	 
    private static LazyInitializedSingleton instance;
     
    private LazyInitializedSingleton(){}
     
    public static LazyInitializedSingleton getInstance(){
        if(instance == null){
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}

/*
 works fine in case of single threaded environment 
 but when it comes to multithreaded systems, it can cause issues if multiple threads are inside the "if" loop at the same time

 */







