package format;

import org.junit.Test;

public class MaskFormatOfIPAddress {
	
	@Test
	public void testMaskFormatOfIPAddress() {
		// 255.255.255.0
		int maskNum = 24;
		int[] unit = new int[4];
		
		for(int i = 0; i < maskNum; i++){
				unit[i/8] |= (1 << (7-i%8));
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int j=0; j < unit.length; j++) {
			sb.append(String.valueOf(unit[j]));
			if (j != unit.length-1)
				sb.append(".");
		}
		System.out.println(sb.toString());
		
	}

}
