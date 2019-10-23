package designPattern.singleton;



class MyThread extends Thread {	
	   private int id;
	   private double t1, t2, t3;
	   private ThreadSafeLock lock;

	   MyThread(int id, ThreadSafeLock lock) {
	      this.id = id;
		  this.lock = lock;
		}
	 
	   MyThread(int id) {
		   this.id = id;
	   }

	   public void run() {
			try {   
	                Thread.sleep(200);  
	            }  
	            catch (InterruptedException e ) { } 

			synchronized (lock){
				for (int j = 0 ; j < 10 ; j++)
				{
					this.t1 = System.currentTimeMillis(); 
					doA(t1, id);

					this.t2 = System.currentTimeMillis(); 
					doB(t2, id);

					this.t3 = System.currentTimeMillis(); 
					doC(t3, id);
				}
			}
		}

		private void doA(double t1, int id)
		{
			System.out.println("Method name is doA(), Time of invocation: " +t1 + ", Calling thread id is: " + id);
		}

		private void doB(double t2, int id)
		{
			System.out.println("Method name is doB(), Time of invocation: " +t2 + ", Calling thread id is: " + id);
		}
		private void doC(double t3, int id)
		{
			System.out.println("Method name is doC(), Time of invocation: " +t3 + ", Calling thread id is: " + id);
		}
} //end class MyThread


/* This is a singleton class that guards against
 * more than one instance being created.
*/

class ThreadSafeLock 
{
    private static ThreadSafeLock instance = null;

    /* This constructor is declared private so that an object
       of this class cannot be created using the new operator.
       Note: The public default constructor will be overridden.
    */    
    private ThreadSafeLock( ) {  }     // the constructor

    /* This is the method to call to get an instance (the only
       instance of an object of this class): only the first 
       call to this method will create an instance.
       @return reference to the singleton object.
    */     
    public static ThreadSafeLock getInstance() {
        if(instance == null) {
        	synchronized(ThreadSafeLock.class){
//           	synchronized(lock){
                if(instance == null) {
                	instance = new ThreadSafeLock();
                }
        	}
        }
        return instance;
    }
}

public class ThreadSafe
{	
	public static void main (String[] args)
	{		
	   // spawn 4 threads
		int count = 4;
		ThreadSafeLock lock = ThreadSafeLock.getInstance();

		for (int i = 0; i < count; i++){
			MyThread p = new MyThread(i, lock);
//			MyThread p = new MyThread(i);
			p.start();
		}
	}	 
}// end class ThreadSafe

