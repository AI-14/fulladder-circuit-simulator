/*********************************************************************************************************************
 * A simple pseudocode for this class (i.e its function):							     *							 *
 *	1. Take the values of A & B in boolean form from class-IntAdder.					     *
 *	2. Perform the full adder operation on A and B and get the sum S in boolean form.			     *
 *  	3. Note - Cout becomes the Cin for the next full adder operation (it'll be taken care by class-IntAdder).    *
 *********************************************************************************************************************/



public class FullAdder {
	
	// Creating member variables:
	private boolean A, B, Cin = false, Cout, S; // Initially Cin value is 0 i.e false in case of addition only
	
	// A no-argument constructor:
	public FullAdder() {
		
	}
	
	// Accessors:
	public boolean getCin() {
		return Cin;
	}
	
	public boolean getCout () {
		return Cout;
	}
	
	public boolean getS() {
		return S;
	}
	
	
	// Mutators:
	public void setA (boolean A) {
		this.A = A;
	}
	
	public void setB (boolean B) {
		this.B = B;
	}
	
	
	public void setCin (boolean Cout) {
		this.Cin = Cout;
	}
	
	public void setS(boolean S) {
		this.S = S;
	}
	
	// Perform full adder operation:
	public void run() {
		S = BinOperations.xorGate(BinOperations.xorGate(A,B), getCin());
		setS(S);
		
		// Calculating carry Cout:
		Cout = BinOperations.orGate(getCin() && BinOperations.xorGate(A, B), A && B );
	}

}

