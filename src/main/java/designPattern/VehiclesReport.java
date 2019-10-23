package designPattern;

class Vehicles 
{
	public int weight_pounds, weight_kilograms, num_tires;

	public Vehicles(int weight_pounds, int weight_kilograms, int num_tires)	
	{
		this.weight_pounds = weight_pounds;
		this.weight_kilograms = weight_kilograms;
		this.num_tires = num_tires;
	}

	public void setWeight_pounds(int weight_pounds)
	{
		this.weight_pounds = weight_pounds;
	}
	public void setWeight_kilograms(int weight_kilograms)
	{
		this.weight_kilograms = weight_kilograms;
	}
	public void setnum_tires(int num_tires)
	{
		this.num_tires = num_tires;
	}


	public int getWeight_pounds()
	{
		return weight_pounds;
	}
	public int getWeight_kilograms()
	{
		return weight_kilograms;
	}

	public int getnum_tires()
	{
		return num_tires;
	}
}


class Car extends Vehicles
{
	public int weight_pounds, weight_kilograms, num_tires;

	public Car(int weight_pounds, int weight_kilograms, int num_tires)	
	{
		super(weight_pounds, weight_kilograms, num_tires);
	}
}


class Truck  extends Vehicles
{
	public int weight_pounds, weight_kilograms, num_tires;

	public Truck(int weight_pounds, int weight_kilograms, int num_tires)	
	{
		super(weight_pounds, weight_kilograms, num_tires);
	}
}


class Motorcycle  extends Vehicles
{
	public int weight_pounds, weight_kilograms, num_tires;

	public Motorcycle(int weight_pounds, int weight_kilograms, int num_tires)	
	{
		super(weight_pounds, weight_kilograms, num_tires);
	}
}


public class VehiclesReport 
{
	public static void main(String[] args) 
	{		
		Car myCar = new Car(200, 88, 4);
		Truck myTruck = new Truck(300, 132, 8);
		Motorcycle myCycle = new Motorcycle(100, 44, 2);

		System.out.println("myCar's weight is: " + myCar.getWeight_pounds() + "(pounds) and "+ myCar.getWeight_kilograms()+ "(kg), and number of tires is: " + myCar.getnum_tires());
		System.out.println("myTruck's weight is: " + myTruck.getWeight_pounds() + "(pounds) and "+ myTruck.getWeight_kilograms()+ "(kg), and number of tires is: " + myTruck.getnum_tires());
		System.out.println("myCycle's weight is: " + myCycle.getWeight_pounds() + "(pounds) and "+ myCycle.getWeight_kilograms()+ "(kg), and number of tires is: " + myCycle.getnum_tires());
		System.out.println();

		myCar.setWeight_pounds(250);	// weight(pounds) change
		myCar.setWeight_kilograms(110);	// weight(kg) change
		myCar.setnum_tires(7);			// number of tire change
		System.out.println("myCar's weight is: " + myCar.getWeight_pounds() + "(pounds) and "+ myCar.getWeight_kilograms()+ "(kg), and number of tires is: " + myCar.getnum_tires());

		myTruck.setWeight_pounds(300);		// weight(pounds) change
		myTruck.setWeight_kilograms(132);	// weight(kg) change
		myTruck.setnum_tires(16);			// number of tire change
		System.out.println("myTruck's weight is: " + myTruck.getWeight_pounds() + "(pounds) and "+ myTruck.getWeight_kilograms()+ "(kg), and number of tires is: " + myTruck.getnum_tires());
	}
}
