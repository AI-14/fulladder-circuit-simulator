/*********************************************************************************************************************************************************
 * A simple pseudocode for this class (i.e its function):											         *											  *
 * 	1. We'll first take two integer numbers based on which one is greater than the other.							         *
 *  	2. Execute similar steps as we executed in class-IntAdder									                 *									  
 * 	3. We'll use IntAdder object to run IntAdder and compute the subtraction using addition.						         *
 * 	4. In the end step i.e output part - we'll print result of case A<B by just computing for case A>B and then displaying a -ve sign in the result. *
 * 	5. Note: Refer class-IntAdder pseudocode for details. 											         *
 *********************************************************************************************************************************************************/



import java.io.*;

public class Subtractor {
	
	private int A, B;

	public Subtractor(IntAdder ia, int A, int B, int numOfBits) {
		ia = new IntAdder(numOfBits, A, B);
		setA(A);
		setB(B);
	}
	
	// Accessors:
	public int getA() {
		return A;
	}
	
	public int getB() {
		return B;
	}
	
	// Mutators:
	public void setA(int A) {
		this.A = A;
	}
	
	public void setB(int B) {
		this.B = B;
	}
	
	// Perform subtraction
	public void sub (IntAdder ia, int outputStreamChoice, int numOfBits, String tempChoice) {
		
		// Creating all required arrays of integer and boolean data types:
		int[] arrayA = new int[numOfBits], arrayB = new int[numOfBits], arraySub_BooleanToBinary = new int[numOfBits+1];
		boolean[] arrayA_Boolean = new boolean[numOfBits], arrayB_Boolean = new boolean[numOfBits], 
				  arrayB_Boolean_OnesCompliment = new boolean[numOfBits], arraySub = new boolean[numOfBits+1];
		int integerAnswerOfSubtraction;
		
		// Converting A and B into binary:
		BinOperations.int2Bin(arrayA, getA());
		BinOperations.int2Bin(arrayB, getB());
		
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
		
		// Finding ones compliment of arrayB_Boolean:
		for(int i=0; i<numOfBits; i++) {
			arrayB_Boolean_OnesCompliment[i] = BinOperations.notGate(arrayB_Boolean[i]);
		}
		
		ia.add(arrayB_Boolean_OnesCompliment, arrayA_Boolean, arrayB_Boolean, arraySub, numOfBits); // Using intAdder 
		
		// Checking for condition (if last value is 1/true):
		if(arraySub[numOfBits] == true) 
			arraySub[numOfBits] = false;
		
		// Converting araySub to binary:
		for(int j=0; j<arraySub.length; j++) {
			if(arraySub[arraySub.length-j-1] == true)
				arraySub_BooleanToBinary[j] = 1;
			else
				arraySub_BooleanToBinary[j] = 0;
		}
			
		integerAnswerOfSubtraction = BinOperations.bin2Int(arraySub_BooleanToBinary, numOfBits+1); // Converting to an integer answer
			
		// Printing +ve or -ve answer based on A>B or B>A:
		if(tempChoice.equals("A>B")) {
			if(outputStreamChoice == 3) {
				System.out.println(getA() + " - " + getB() + " = " + integerAnswerOfSubtraction);
				System.out.println();
					
				System.out.println("Integer " + getA() + " in binary form is:");
				for(int k= arrayA.length-1; k>=0; k--)
					System.out.print(arrayA[k] + " ");
				System.out.println();
					
				System.out.println("Integer " + getB() + " in binary form is:");
				for(int k= arrayB.length-1; k>=0; k--)
					System.out.print(arrayB[k] + " ");
				System.out.println();
					
				System.out.println("Their difference in binary form is: ");
				for(int k=0; k<arraySub_BooleanToBinary.length; k++)
					System.out.print(arraySub_BooleanToBinary[k] + " ");		
				System.out.println("\n");
			}
			else if(outputStreamChoice == 4) {
				try {
					FileOutputStream output = new FileOutputStream("OutputSubtractor.txt");
					PrintWriter write = new PrintWriter(output);
					write.println(getA() + " - " + getB() + " = " + integerAnswerOfSubtraction);
					write.println("Integer " + getA() + " in binary form is: ");
					for(int k= arrayA.length-1; k>=0; k--)
						write.print(arrayA[k] + " ");
					write.println();
					write.println("Integer " + getB() + " in binary form is: ");
					for(int k= arrayB.length-1; k>=0; k--)
						write.print(arrayB[k] + " ");
					write.println();
					write.println("Their difference in binary form is: ");
					for(int k=0; k<arraySub_BooleanToBinary.length; k++)
						write.print(arraySub_BooleanToBinary[k] + " ");	
						
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
		else if(tempChoice.equals("A<B")) {
			if(outputStreamChoice == 3) {
				System.out.println(getB() + " - " + getA() + " = -" + integerAnswerOfSubtraction);
				System.out.println();
				
				System.out.println("Integer " + getB() + " in binary form is:");
				for(int k= arrayA.length-1; k>=0; k--)
					System.out.print(arrayB[k] + " ");
				System.out.println();
					
				System.out.println("Integer " + getA() + " in binary form is:");
				for(int k= arrayB.length-1; k>=0; k--)
					System.out.print(arrayA[k] + " ");
				System.out.println();
					
				System.out.println("Their difference in binary form is: ");
				System.out.print("- ");
				for(int k=0; k<arraySub_BooleanToBinary.length; k++)
					System.out.print(arraySub_BooleanToBinary[k] + " ");		
				System.out.println("\n");
			}
			else if(outputStreamChoice == 4) {
				try {
					FileOutputStream output = new FileOutputStream("OutputSubtractor.txt");
					PrintWriter write = new PrintWriter(output);
					write.println(getB() + " - " + getA() + " = -" + integerAnswerOfSubtraction);
					write.println("Integer " + getB() + " in binary form is: ");
					for(int k= arrayA.length-1; k>=0; k--)
						write.print(arrayB[k] + " ");
					write.println();
					write.println("Integer " + getA() + " in binary form is: ");
					for(int k= arrayB.length-1; k>=0; k--)
						write.print(arrayA[k] + " ");
					write.println();
					write.println("Their difference in binary form is: ");
					write.print("- ");
					for(int k=0; k<arraySub_BooleanToBinary.length; k++)
						write.print(arraySub_BooleanToBinary[k] + " ");	
						
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
	}
	
}

