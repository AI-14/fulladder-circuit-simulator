/*************************************************************************************
 * Information and Computer Science (ICS) Department                                 *
 * College of Computer Sciences and Engineering (CCSE)                               *
 * King Fahd University of Petroleum and Minerals - KFUPM                            *
 * Dhahran, Saudi Arabia                                                             *
 *                                                                                   *
 * Course - ICS 102 / Term 183                                                       *
 *                                                                                   *
 * @authors Amaan Izhar (ID: 201781130) and Abdurrahman Siddiqui (ID: 201736190)     *
 * @Instructor Prof. El-Sayed El-Alfy                                                *
 *************************************************************************************/



/*******************************************************************************************
 * Quick summary of the program:                                                           *
 * The program's main aim is to act as a simulator and perform addition & subtraction      *
 * of two positive integer numbers using the full adder circuit (n bits).                  *                                                                                       *
 *******************************************************************************************/



/***********************************************************************************
 * A short pseudocode for this class:						   *
 * 	1. Display the menu at the start and store the choices.			   *
 * 	2. Validate the choices and ask for required integers.			   *
 * 	3. Use the classes as per user's choice and finally display the summary.   *
 ***********************************************************************************/



import java.util.*;
import java.io.*;

public class SimulatorProject {

	public static void main(String[] args) {
		
		// Creating keyboard input Scanner:
		Scanner keyBoard = new Scanner(System.in);
		
		// Initial messages to display:
		System.out.println("*** WELCOME TO THE FULL-ADDER SIMULATOR ***\n");
		System.out.println("Select an option (1., 2., 3. or 4.):");
		System.out.println("(Note: You are required to select Settings first to set up your choices)\n");
		
		// Creating variables for menu:
		int menuChoice = 0, inputStreamChoice = 0, outputStreamChoice = 0;
		int A = 0, B = 0, numOfBits = 0, countChoice1 = 0, countChoice2 = 0, countChoice3 = 0;
		boolean sentinelMenu = true;
		String inputFilePath = "";
		
		// Validating the menu choice and looping in case of invalidation:
		while(sentinelMenu) {			
			System.out.printf("%d. Settings%n%d. Adder%n%d. Subtractor%n%d. Exit%n", 1, 2, 3, 4);
			System.out.print("Option: ");
			try {
				menuChoice = keyBoard.nextInt();
				System.out.println();
			}
			catch(InputMismatchException e) {
				System.out.println("Error: InputMismatch."); 
				keyBoard.next(); // catching the key that would cause an infinite loop
			}
			
			// Settings choice:
			if(menuChoice == 1) { 
				try {
					System.out.println("You selected \"Settings\" !");
					System.out.println("Please select a way of input stream (1. or 2.):");
					System.out.printf("%d. Keyboard%n%d. File%n", 1, 2);
					System.out.print("Input Stream: ");
					inputStreamChoice = keyBoard.nextInt();
					if(inputStreamChoice != 1 && inputStreamChoice != 2) {
						System.out.println("Error: Invalid choice of input stream. Program will terminate now. ");
						System.exit(0);
					}
					System.out.println();
					System.out.println("Please select a way of output stream (3. or 4.): ");
					System.out.printf("%d. Monitor%n%d. File%n", 3, 4);
					System.out.print("Output Stream: ");
					outputStreamChoice = keyBoard.nextInt();
					if(outputStreamChoice != 3 && outputStreamChoice != 4) {
						System.out.println("Error: Invalid choice of output stream. Program will terminate now. ");
						System.exit(0);
					}
					System.out.println();
				}
				catch(InputMismatchException e){
					System.out.println("Error: Input mismatch in stream choice . Program will terminate now.");
					System.exit(0);
				}
				switch(inputStreamChoice) {
					case 1: try {
								System.out.println("Enter two integers A and B: ");
								System.out.print("A: ");
								A = keyBoard.nextInt();
								System.out.print("B: ");
								B = keyBoard.nextInt();
								System.out.println("Enter number of bits: ");
								System.out.println("(Make sure that the bits correspond to the integers correctly)");
								System.out.print("Number of bits: ");
								numOfBits = keyBoard.nextInt();
							
								// Validating integers:
								if(A >= Math.pow(2, numOfBits) || B >= Math.pow(2, numOfBits) || A < 0 || B < 0) {
									System.out.println("Invalid integers (> 2^" + numOfBits + " or < 0). Program will terminate now.");
									System.exit(0);
								}
							
								System.out.println();
								System.out.println("**************************************************************");
								System.out.println("Now you may select Adder/Subtractor/Exit/Settings.");
							}
					        catch(InputMismatchException e) {
					        	System.out.println("Error: Input Mismatch in integers and/or number of bits");
					        	System.out.println("Program will terminate now.");
					        	System.exit(0);
					        }
							break;
			
					case 2: try {
								System.out.println("Enter file path: ");
								System.out.println("(Make sure that the bits correspond to the integers correctly in the file)");
								keyBoard.nextLine();
								inputFilePath = keyBoard.nextLine();
								Scanner file = new Scanner(new FileInputStream(inputFilePath));
								System.out.println("File found.");
								A = file.nextInt();
								B = file.nextInt();
								numOfBits = file.nextInt();
								file.close();
								
								// Validating integers in the file:
								if(A >= Math.pow(2, numOfBits) || B >= Math.pow(2, numOfBits) || A < 0 || B < 0) {
									System.out.println("Invalid integers (> 2^" + numOfBits + " or < 0). Program will terminate now.");
									System.exit(0);
								}
								
								System.out.println();
								System.out.println("**************************************************************");
								System.out.println("Now you may select Adder/Subtractor/Exit/Settings.");		
							}
							catch(FileNotFoundException e) {
								System.out.println("File not found. Program will terminate now.");
								System.exit(0);
							}
					 		catch(InputMismatchException e) {
					 			System.out.println("Error: Input Mismatch in integers and/or number of bits.");
					 			System.out.println("Program will terminate now.");
					 			System.exit(0);
					 		}
							break;
				}
									
				countChoice1++;
			}
			
			// Adder choice:
			else if(menuChoice == 2) {
				System.out.println("You selected \"Adder\" !");
				
				IntAdder ia = new IntAdder(numOfBits, A, B);
				ia.add(outputStreamChoice, numOfBits);
				
				System.out.println("**************************************************************");
				System.out.println("Now you may select Adder/Subtractor/Exit/Settings.");
				countChoice2++;
			}
			
			// Subtractor choice:
			else if(menuChoice == 3) {
				System.out.println("You selected \"Subtractor\" !");
				String tempChoice;
				
				if(A >= B) {
					tempChoice = "A>B";
					IntAdder ia = new IntAdder(numOfBits, A, B);
					Subtractor sb = new Subtractor(ia, A, B, numOfBits);
					sb.sub(ia, outputStreamChoice, numOfBits, tempChoice);
				}
				else if(A < B) {
					tempChoice = "A<B";
					IntAdder ia = new IntAdder(numOfBits, A, B);
					Subtractor sb = new Subtractor(ia, B, A, numOfBits);
					sb.sub(ia, outputStreamChoice, numOfBits, tempChoice);
				}
				
				System.out.println("**************************************************************");
				System.out.println("Now you may select Adder/Subtractor/Exit/Settings.");
				countChoice3++;
			}
			
			// Exit choice:
			else if(menuChoice == 4) {
				System.out.println("You decided to EXIT the program !");
				System.out.println("-----------------------SUMMARY REPORT-------------------------");
				System.out.println("Your current settings is: ");
				
				// Summary for input stream:
				if(inputStreamChoice == 1)
					System.out.println("Input Stream: KeyBoard");
				else if(inputStreamChoice == 2)
					System.out.println("Input Stream: File");
				else
					System.out.println("Input Stream: No choice"); 

				// Summary for output stream:
				if(outputStreamChoice == 3)
				System.out.println("Output Stream: Monitor/Screen");
				else if(outputStreamChoice == 4)
					System.out.println("Output Stream: File");
				else
					System.out.println("Output Stream: No choice");
				
				// Summary of number of times every operation is used:
				System.out.println("The number of times Settings is changed: " + countChoice1);
				System.out.println("The number of times Adder is used: " + countChoice2);
				System.out.println("The number of times Subtractor is used: " + countChoice3);
				System.out.println();
				System.out.println("**************************************************************");
				
				System.exit(0);	
			}
			
			// Any other key:
			else {
				System.out.println("Error: Wrong Key. Try Again.");
				System.out.println();
			}		
		}
		
		keyBoard.close();
	}

}

