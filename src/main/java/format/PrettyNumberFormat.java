package format;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Pretty Number Formatting function
 * 
 * Author: Jake Jong Seo Lee
 **/

public class PrettyNumberFormat {
	
	@Test
	public void testPrettyNumberFormat (String args[])
	{
	    int testCases[] =
	    { 
	        0, 
	        10, 
	        22, 
	        100,	        
	        341, 
	        1000,
	        34200 ,
	        5910000, 
	        1000000000, // 1 Gigabyte
	        1000000001,
	        -500,
	        34100, 
	        34000, 
	        999900, 
	        54123, 
	        1000, 
	        1001, 
	        1014, 
	        1015, 
	        1200, 
	        999500, 
	        999400, 
	        999500000, 
	        999400000, 
	        4000000,
	        4000100,
	        4001000,
	        4010000,
	        41849000,
	        41850000,
	        41889000,
	        54123,
		    952000,	//	==> 952
		    95200,	//	==> 95.2 
		    9520	//	==> 9.52
	    };

	    for (int number : testCases){
	    	System.out.println(number + " --- " + toPrettyFormat((Integer)number));
	    }
	}
	
	
	static String toPrettyFormat(Integer number)
	{
		
	    char unit[] = {'B', 'K', 'M', 'G', 'T'};	// unit symbol
	    int len, dotPositionFromRight, dotPositionFromLeft, kUnit, fourthDigitNum, first3DigitsNum;
	    String numberStr, first3Digits, fourthDigit;
	   
	    if (number < 0 || number > 1000000000 || number == null){
	        return "Invalid number input (number should be positive number, and less than or equal to 1 Gigabyte)";
	    }	    

	    if (number < 1000) {
	    	return Integer.toString(number) + unit[0];
	    }
	    else {
	    	numberStr = Integer.toString(number);
	    	
	    	// First 3 digit numbers
	        first3Digits = numberStr.substring(0, 3);  	
	        
	        // 4th digit number
	        fourthDigit = numberStr.substring(3, 4);	
	        
	        fourthDigitNum = Integer.parseInt(fourthDigit);
	        first3DigitsNum = Integer.parseInt(first3Digits);

	        // When the fourth digit is equal to or bigger than 5
	        if (fourthDigitNum >= 5)	
	        {
	        	// Increase first digit numbers
	        	first3DigitsNum ++;			
	        	first3Digits = Integer.toString(first3DigitsNum);
	        	
	        	// Redefine numberStr
	        	numberStr = first3Digits.concat(numberStr.substring(3));
	        	
	        	// Get first 3 digit numbers again
		        first3Digits = numberStr.substring(0, 3);  	
	        }

	        // Length of digits
	    	len = numberStr.length();
	    	
	    	// K unit for finding unit symbol from unit[]
	    	kUnit = ((len-1)/3);					
	    	dotPositionFromLeft = kUnit * 3;			
	    	
	    	dotPositionFromRight = len - dotPositionFromLeft;
	    	
	    	// When we need to insert comma between numbers in first3Digits
	    	if (dotPositionFromRight >= 1 && dotPositionFromRight <= 2)
	    		first3Digits = first3Digits.substring(0, dotPositionFromRight) 
	    						+"." + first3Digits.substring(dotPositionFromRight);
	    	
	    	/*
	    	 * If a dollar sign ($) is at the end of the entire regular expression, it matches the end of a line.
			 * In this case, the replaceAll function look for "0" ("0" regex) at the end of the address string and replace it with empty string.
	    	 * It repeats two times to prevent numbers like "1.00K" or "1.0K"
	    	 * If there is no "0" at the end of the string, it doesn't change anything. 
	    	 */
	    	for (int i = 0; i < 2; i++)	
	    		first3Digits = first3Digits.replaceAll("0$", StringUtils.EMPTY);  
	    	
	    	/*
	    	 * In this case, the replaceAll function look for "." (comma regex) at the end of the address string and replace it with empty string.
	    	 * This is to prevent expression like "1.K" or "5.M"
	    	 * If there is no "." at the end of the string, it doesn't change anything.
	    	 */
	    	first3Digits = first3Digits.replaceAll("\\.$", StringUtils.EMPTY);   
	    	
	    	return first3Digits + unit[kUnit];
	    }
	}
}
