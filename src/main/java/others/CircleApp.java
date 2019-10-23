package others;

import java.util.*;
import java.text.*;
import java.lang.Math;

public class CircleApp {

	public static void main(String[] args) {

		//Declare variables
		Scanner sc = new Scanner(System.in);
		double radius = 0;
		String choice = "y";
		
		
		// Greetings
		System.out.println("Welcome to the Circle Tester.");
		System.out.println();
		
		//Get Input
		while(choice.equalsIgnoreCase("y")){
			
			//Get radius
			System.out.println("Enter the radius: ");
			radius = sc.nextDouble();
			
			//Creating new object with given radius
			Circle circle = new Circle(radius);
			System.out.println("circumference: " + circle.getFormattedCircumference(circle.getCircumference()));
			System.out.println("Area: " + circle.getFormattedArea(circle.getArea()));
			
			//Asking if the user continue
			System.out.println("Continue? (y/n)");
			choice = sc.next();
			sc.nextLine();
			System.out.println();
		}// end while
		
		sc.close();
		
	}//end Main
	
}//end class


class Circle {
	
	//Declare variables
	private static final double pi = Math.PI;
	private double radius = 0.0;
	private double circumference = 0.0;
	private double area = 0.0;
	protected static int count = 0;
	
	// Constructor
	public Circle(double radius){
		setRadius(radius);
	}
	
	//Set and Get for Instance Variabls
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public void setCircumference(double circumference){
	
		this.circumference = circumference;
	}
	
	
	public void setArea(double area){

		this.area = area;
	}
	
	public double getRadius() {
		return radius;
	}
	
	
	public double getCircumference() {
		return 2.0 * pi * radius;
	}
	
	public String getFormattedCircumference(double circumference){
	
		NumberFormat number = NumberFormat.getNumberInstance();
		number.setMaximumFractionDigits(2);
		return number.format(circumference);
	}

	public double getArea() {
		return pi * radius * radius;
	}
	
	public String getFormattedArea(double area){
	
		NumberFormat number = NumberFormat.getNumberInstance();
		number.setMaximumFractionDigits(2);
		return number.format(area);
	}
	
	public static int getObjectCount() {
	
	    return count;
	}

}//end class

class Validator {

	// validate data for radius
	public static double getDouble(Scanner sc, String prompt){
		double d = 0;
		boolean isValid = false;
		while (isValid == false){
			System.out.print(prompt);
			if(sc.hasNextDouble()){
				d = sc.nextDouble();
				isValid = true;
			} // end if
			else {
				System.out.println("Error: Invalid decimal value. Try again.");
				//isValid = false;
			} // end else
			sc.nextLine();
		}
		return d;
	}
}
