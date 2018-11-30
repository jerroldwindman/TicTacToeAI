

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
 * This is the GameBoard class which allows for the creation GameBoard object.
 * Each GameBoard object contains an array representation of the GameBoard consisting of
 * Box enumerations. The size of the array is contained in a final variable boardSize.
 *
 */
public class GameBoard implements Cloneable {
	private Box[] board;
	private final int boardSize = 9;

	/**
	 * The default constructor for the GameBoard class.
	 * Creates a board of size boardSize with each Box in the board array set to the
	 * EMPTY configuration.
	 */
	public GameBoard(){
		board = new Box[boardSize];
		for(int i = 0; i < boardSize; i++){
			board[i] = Box.EMPTY;
		}
	}
	
	/**
	 * A getter method for the boardSize
	 * @return
	 * The size of the GameBoard object's board array
	 */
	public int getBoardSize(){
		return boardSize;
	}
	
	/**
	 * A getter method for the enumeration of the particular board space of
	 * a GameBoard object.
	 * @param space
	 * The index of the board array of the GameBoard object
	 * @return
	 * The enumeration type of the specified Box in the board array of the GameBoard object
	 * 
	 */
	public Box getSpace(int space){
		return board[space];
	}
	
	/**
	 * A setter method for a particular space in the board array.
	 * @param position
	 * The index of the board array where a Box is supposed to be changed.
	 * @param player
	 * The Box type that will be placed at that position in the board array
	 * <dt><b>Postconditions</b><dd>
	 * The position of the board array at index position is now set equal to the Box passed in
	 * with the Box variable player.
	 */
	public void setSpace(int position, Box player){
		board[position - 1] = player;
		
	}
	
	/**
	 * A method to check whether the current board array is filled with X's and O's.
	 * @return
	 * False if any of the indices of the board array contains an EMPTY box
	 * True otherwise/
	 */
	public boolean isFull(){
		for(int i = 0; i < boardSize; i++){
			if(board[i] == Box.EMPTY){
				return false;
			}
			else{
				continue;
			}
		}
		return true;
	}
	
	/**
	 * A method to override the default clone method. This method clones
	 * the board object it is called on and returns the clone.
	 */
	public GameBoard clone(){
		GameBoard result = new GameBoard();
		try{
			result = (GameBoard) super.clone();
		}catch(CloneNotSupportedException e){
			throw new RuntimeException();
		}
		result.board = (Box[]) board.clone();
		return result;
	}
}
