/***********************************************************************************************************
 * This is the Utility class.										   *				   *
 * We'll use this class to perform logical operations and conversion of binary to integer and vice versa.  *
 * The logical operations (also called logic gates) used are: AND/OR/NOT/XOR. 				   *
 ***********************************************************************************************************/
 


public class BinOperations {

	public static boolean andGate (boolean value1, boolean value2) {
		return value1 && value2;	
	}
	
	public static boolean orGate (boolean value1, boolean value2) {
		return value1 || value2;
	}

	public static boolean notGate (boolean value1) {
		return !value1;	
	}
	
	public static boolean xorGate (boolean value1, boolean value2) {	
		boolean andvalue1 = andGate(notGate(value1), value2);
		boolean andvalue2 = andGate(notGate(value2), value1);
		boolean orvalue = orGate(andvalue1, andvalue2);
		boolean xorvalue = orvalue;
		
		return xorvalue;
	}
	
	// Converting the binary array into its corresponding integer number and returning it:
	public static int bin2Int (int[] binaryArray, int size) {
		int value = 0;
		for (int i=size-1, j=0; i>=0; i--, j++) 
			value += binaryArray[i]*Math.pow(2, j);
				
		return value;
	}
	
	// Converting integers to its binary form and returning the array in binary form: 
	public static void int2Bin (int[] array, int value) {		
		for (int i=0; value != 0; i++) {
			array[i] = value%2;
			value = value/2;
		}	
	}
	
}

