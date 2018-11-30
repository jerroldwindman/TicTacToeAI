/**
 * 
 * Jerrold Windman 
 * (109070054)
 * Homework #5
 * CSE 214 Spring 2017 Recitation 14
 * TAs: Tayo Amuneke, Yiwen Wang
 * Grading TA: Anand Aiyer
 *
 */

/**
 * The GameBoardNode class which wraps around a GameBoard object and adds many functionalities
 * to the GameBoard. The config[] array is a GameBoardNode array the size of the board array passed
 * in through the constructor. Each of these represents the children of the GameBoardNode class.
 */
public class GameBoardNode implements Cloneable{
	private GameBoard board;
	private boolean isEnd;
	private Box currentTurn;
	private Box winner;
	private GameBoardNode config[] = new GameBoardNode[9];
	private double winProb;
	private double loseProb;
	private double drawProb;

	/**
	 * The constructor for the GameBoardNode object.
	 * @param board
	 * The current representation of the GameBoard
	 * @param currentTurn
	 * The Box who's turn it currently is in this GameBoardNode
	 * <dt><b>Preconditions</b><dd>
	 * The currentTurn parameter must not  be EMPTY and the board object must be initialized.
	 * @throws
	 * An IllegalArgumentException is thrown if either the currentTurn is set to EMPTY or the
	 * board array is null.
	 */
	public GameBoardNode(GameBoard board, Box currentTurn){
		if(currentTurn != Box.EMPTY && board != null){
			this.currentTurn = currentTurn;
			this.board = board;
			
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * A getter method for a particular GameBoardNode object contained in the config array.
	 * @param position
	 * The index of the config array to return
	 * @return
	 * The GameBoardNode contained at the index position
	 */
	public GameBoardNode getConfig(int position){
		return config[position];
	}
	
	/**
	 * A setter method for a particular GameBoardNode object contained in the config array
	 * @param newNode
	 * The new GameBoardNode to be placed in the config array
	 * @param position
	 * The index at which the GameBoardNode is placed in the config array
	 */
	public void setConfig(GameBoardNode newNode, int position){
		config[position] = newNode;
	}
	
	/**
	 * A setter method for the entire config array.
	 * @param nodeArray
	 * The array of GameBoardNode object to set the config array to
	 */
	public void setWholeConfig(GameBoardNode[] nodeArray){
		config = nodeArray;
	}
	
	/**
	 * A getter method for the entire config array
	 * @return
	 * The array of GameBoardNode objects contained in the GameBoardNode
	 */
	public GameBoardNode[] getWholeConfig(){
		return config;
	}
	
	/**
	 * A getter method for the probability that the user will win from the
	 * configuration of the current GameBoardNode
	 * @return
	 * A double probability representing the chance that X will win.
	 */
	public double getWinProb(){
		return winProb;
	}
	
	/**
	 * A getter method for the probability that the user will lose from the
	 * configuration of the current GameBoardNode
	 * @return
	 * A double probability representing the chance that X will lose.
	 */
	public double getLoseProb(){
		return loseProb;
	}
	
	/**
	 * A getter method for the probability that the user will draw from the
	 * configuration of the current GameBoardNode
	 * @return
	 * A double probability representing the chance that neither X or O will win.
	 */
	public double getDrawProb(){
		return drawProb;
	}
	
	
	/**
	 * A getter method for the board array contained in the GameBoardNode object
	 * @return
	 * The board object contained in the GameBoardNode object
	 */
	public GameBoard getBoard(){
		return board;
	}
	
	/**
	 * A setter method for the currentTurn variable of the GameBoardNode object
	 * @param turn
	 * The Box which the currentTurn is to be set equal to
	 */
	public void setCurrentTurn(Box turn){
		currentTurn = turn;
	}
	
	/**
	 * A getter method for the currentTurn variable of the GameBoardNode object
	 * @return
	 * The Box which the currentTurn is currently set equal to for this GameBoardNode object
	 */
	public Box getCurrentTurn(){
		return currentTurn;
	}
	
	/**
	 * A setter method for the winner variable of the GameBoardNode object
	 * @param win
	 * The Box which the winner is currently set equal to for this GameBoardNode object
	 */
	public void setWinner(Box win){
		winner = win;
	}
	
	/**
	 * A getter method for the winner variable of the GameBoardNode object
	 * @return
	 * The Box which the winner is currently set equal to
	 */
	public Box getWinner(){
		return winner;
	}
	
	/**
	 * A setter method for the isEnd variable of the GameBoardNode object
	 * @param end
	 * The boolean value to set the isEnd variable to
	 */
	public void setIsEnd(boolean end){
		isEnd = end;
	}
	
	/**
	 * A getter method for the isEnd boolean variable
	 * @return
	 * The isEnd variable contained in this GameBoardNode object
	 */
	public boolean getEnd(){
		return isEnd;
	}
	
	
	/**
	 * A method to override the built in toString method. This method
	 * returns a string representation of the current configuration of the board
	 * object contained in this GameBoardNode object.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < board.getBoardSize(); i++){
			sb.append("|" + (char)board.getSpace(i).getBox() + "|");
			if(i == 2 || i== 5 || i==8){
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * * A method to override the default clone method. This method clones
	 * the GameBoardNode object it is called on and returns the clone.
	 */
	public GameBoardNode clone(){
		
		GameBoardNode result;

		try{
			result = (GameBoardNode) super.clone();
		}catch(CloneNotSupportedException e){
			throw new RuntimeException();
		}
		result.board = (GameBoard) board.clone();
		return result;
	}
	
}
