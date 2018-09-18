package codeGeek;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/** 
Given many coins of 3 different face values, print the combination sums of the coins up to 1000. Must be printed in order. 

eg: coins(10, 15, 55) 
print: 
10 
15 
25 
30 
. 
. 
. 
1000 
*/

public class CombinationSumsOfCoins {

	@Test
	public void testCombinationSumsOfCoins(){
		int[] value = {10, 15, 55, 100};
		int limit = 100;
		List<Integer> sums = new ArrayList<Integer>();
		
		sums.add(0); // very important!!!
		
		for(int num = 1; num <= limit; num++){
			if(sums.contains(num-value[0]) || sums.contains(num-value[1]) || sums.contains(num-value[2]) || sums.contains(num-value[3])){
				sums.add(num);
				System.out.println(num);
			}
		}
		System.out.println(sums);
			
	}
}
