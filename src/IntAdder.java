/*************************************************************************************************************************************************************
 * A simple pseudocode for this class (i.e its function):												     *
 * 	1. Take the integers A and B passed from class-SimulatorProject.										     *
 * 	2. Convert the integers into binary form using the utility class-BinOperations.									     *
 * 	3. Convert the binary array to a boolean array.													     *													 *
 * 	4. Pass each element of binary array to class-FullAdder (this class will do the operation i.e bitwise operation).				     *
 * 	5. Store each bitwise result in an array in boolean form.											     *
 * 	6. Convert the results's array to binary form and convert the binary array to a final integer value (i.e the sum of A and B) using the utility class.*
 * 	7. Finally display the result (A+B) in integer and binary form based on user's output choice.							     *
 * 	8. Extra(s)- an overloaded method is present for subtraction (see class-Subtractor for more info).						     *
 *************************************************************************************************************************************************************/



import java.io.*;

public class IntAdder {
	
	// Creating member variables:
	private int A, B;
    public FullAdder[] FA;
    
    // Creating fulladders of order n:
	public IntAdder(int numOfBits, int A, int B) {
		 FA = new FullAdder[numOfBits];	
		 for(int i=0; i< numOfBits; i++)
			 FA[i] = new FullAdder();
		 
		 setA(A);
		 setB(B);
	}
	
	// Accessors:
	public int getA () {
		return A;
	}
	
	public int getB () {
		return B;
	}
		
	// Mutators:
	public void setA (int A) {
		this.A = A; 
	}
	
	public void setB (int B) {
		this.B = B; 
	}
		
	// Addition operation:
	public void add(int outputStreamChoice, int numOfBits) {
		
		// Creating all required arrays of integer and boolean data types:
		int[] arrayA = new int[numOfBits], arrayB = new int[numOfBits], arraySum_BooleanToInteger = new int[numOfBits + 1]; 
		boolean[] arraySum = new boolean[numOfBits + 1], arrayA_Boolean = new boolean[numOfBits], arrayB_Boolean = new boolean[numOfBits];
		int integerAnswerOfAdder;
		
		// Converting A and B into binary:
		BinOperations.int2Bin(arrayA, A); // Storing binary form of integer A into arrayA
		BinOperations.int2Bin(arrayB, B); // Storing binary form of integer B into arrayB
		
		// Converting arrayA & arrayB into boolean form:
		for(int i= 0; i<numOfBits; i++) {
			
			// For arrayA:
			if(arrayA[i] == 1) 
				arrayA_Boolean[i] = true;
			else 
				arrayA_Boolean[i] = false;
				
		        // For arrayB:
			if(arrayB[i] == 1)
				arrayB_Boolean[i] = true;
			else
				arrayB_Boolean[i] = false;	
		}
				
		// Setting first adder manually:
		FA[0].setA(arrayA_Boolean[0]);
		FA[0].setB(arrayB_Boolean[0]);
		FA[0].run();
		arraySum[0] = FA[0].getS();
		
		// Automating the fullAdder references starting from 2nd adder:
		for (int i=1; i<numOfBits; i++) {
			FA[i].setA(arrayA_Boolean[i]);
			FA[i].setB(arrayB_Boolean[i]);
			FA[i].setCin(FA[i-1].getCout());
			FA[i].run();
			arraySum[i] = FA[i].getS();
		}	
		arraySum[numOfBits] = FA[numOfBits-1].getCout(); // Getting the last Cout value from the last fullAdder
		
		// Converting arraySum of boolean form to binary form:
		for(int j=0; j<arraySum.length; j++) {
			if(arraySum[arraySum.length-j-1] == true)
				arraySum_BooleanToInteger[j] = 1;
			else
				arraySum_BooleanToInteger[j] = 0;
		}
		
		integerAnswerOfAdder = BinOperations.bin2Int(arraySum_BooleanToInteger, numOfBits+1); // Converting the binary array to get an integer
		
		
		/***
		 * Now we are going to output the results based on user's choice.
		 * If user wants to print on monitor/screen, we'll use the 1st if statement 
		 * otherwise we'll use else if statement to write in the output file.
		 * 
		 ***/
		
		if(outputStreamChoice == 3) {
	
			// Printing on monitor the integers A, B & the sum in integer form:
			System.out.println(getA() + " + " + getB() + " = " + integerAnswerOfAdder);
			System.out.println();
		
			// Printing on monitor the integers A, B & the sum in binary form:
			System.out.println("Integer " + getA() + " in binary form is:");
			for(int k= arrayA.length-1; k>=0; k--)
				System.out.print(arrayA[k] + " ");
		
			System.out.println();
			System.out.println("Integer " + getB() + " in binary form is:");
			for(int k= arrayB.length-1; k>=0; k--)
				System.out.print(arrayB[k] + " ");
		
			System.out.println();
			System.out.println("Their sum in binary form is: ");
			for(int k=0; k<arraySum_BooleanToInteger.length; k++)
				System.out.print(arraySum_BooleanToInteger[k] + " ");		
		
			System.out.println("\n");
		}		
		else if(outputStreamChoice == 4) {
			try {
				FileOutputStream output = new FileOutputStream("OutputAdder.txt");
				PrintWriter write = new PrintWriter(output);
				write.println(getA() + " + " + getB() + " = " + integerAnswerOfAdder);
				write.println("Integer " + getA() + " in binary form is: ");
				for(int k= arrayA.length-1; k>=0; k--)
					write.print(arrayA[k] + " ");
				write.println();
				write.println("Integer " + getB() + " in binary form is: ");
				for(int k= arrayB.length-1; k>=0; k--)
					write.print(arrayB[k] + " ");
				write.println();
				write.println("Their sum in binary form is: ");
				for(int k=0; k<arraySum_BooleanToInteger.length; k++)
					write.print(arraySum_BooleanToInteger[k] + " ");	
				
				write.close();
			}
			catch(FileNotFoundException e) {
				System.out.println("Output file cannot be created.");
				System.out.println("Program will terminate now due to en exceptional error.");
				System.out.println("You may re-run the program if you want to re-use it.");
				System.exit(0);
			}
			
			System.out.println("The output file is created successfully. You may check it now.");
			System.out.println();
		}
	}
	
	// For subtraction:
	public boolean[] add(boolean[] arrayB_Boolean_OnesCompliment, boolean[] arrayA_Boolean, boolean[] arrayB_Boolean, boolean[] arraySub, int numOfBits) {
		
		// Now we'll use intAdder:
		FA[0].setCin(true);
		FA[0].setA(arrayA_Boolean[0]);
		FA[0].setB(arrayB_Boolean_OnesCompliment[0]);
		FA[0].run();
		arraySub[0] = FA[0].getS();
				
		for (int i=1; i<numOfBits; i++) {
			FA[i].setA(arrayA_Boolean[i]);
			FA[i].setB(arrayB_Boolean_OnesCompliment[i]);
			FA[i].setCin(FA[i-1].getCout());
			FA[i].run();
			arraySub[i] = FA[i].getS();
		}	
				
		arraySub[numOfBits] = FA[numOfBits-1].getCout(); // Storing last carry of full adder in the array.
		
		return arraySub;	
	}
	
}


