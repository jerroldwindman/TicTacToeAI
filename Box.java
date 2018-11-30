
/**
 * Jerrold Windman 
 * (109070054)
 * Homework #5
 * CSE 214 Spring 2017 Recitation 14
 * TAs: Tayo Amuneke, Yiwen Wang
 * Grading TA: Anand Aiyer
 *
 */

/**
 *
 * This is an enumeration type called Box which contains the different
 * configurations each Box of TicTacToe board can be.
 * 
 *
 */
public enum Box {
	X('X'), O('O'), EMPTY('_'), DRAW(' ') ;
	
	private char value;
	private String spaceType;
	
	
	/**
	 * The constructor for the Box enumeration.
	 * @param value
	 * The char value that this enumeration represents.
	 */
	private Box(char value){
		this.value = value;
	}
	
	/**
	 * A getter method for the char that the Box represents
	 * @return
	 * The char representation of the Box
	 */
	public int getBox(){
		return value;
	}
	
}
