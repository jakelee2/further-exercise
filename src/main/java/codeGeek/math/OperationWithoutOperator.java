package codeGeek.math;

public class OperationWithoutOperator {

	public static int multiply(int x, int y){
	
		if(x == 0 || y == 0){
			return 0;
		}else if(y > 0){
			return x + multiply(x, y-1);
		}
		else {// if(y < 0)
			return -x + multiply(x, y+1);
		}
	}
	
	// '%' operation
	public static int remainder(int x, int y){
	
		if(x < y){
			return x;
		}else if(x == y){
			return 0;
		}else {	// if(x >y) 
			return remainder(x - y, y);
		}
	}	
	
	public static int divide(int x, int y){
		
		if(x < y){
			return 0;
		}else if(x == y){
			return 1;
		}else {	// if(x >y) 
			return 1 + divide(x - y, y);
		}
	}	
	
	public static void main(String args[]){
		System.out.println("== multiply ==");
		System.out.println(multiply(5,7));
		System.out.println(multiply(5,-7));
		System.out.println(multiply(-5,-7));
		System.out.println(multiply(-5,7));

		System.out.println("== remainder ==");
		System.out.println(remainder(12,5));
		System.out.println(remainder(32,4));
		System.out.println(remainder(32,11));

		System.out.println("== divide ==");
		System.out.println(divide(12,5));
		System.out.println(divide(32,4));
		System.out.println(divide(56,11));
		System.out.println(divide(124,16));
		

	}

}
