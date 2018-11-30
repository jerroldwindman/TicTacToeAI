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
 * A class that creates a GameTree object, which contains two GameBoardNodes referred to as root and cursor, and
 * the amount of leaves found beneath the current configuration of the root and cursor. 
 *
 */
public class GameTree {
	private GameBoardNode root;
	private GameBoardNode cursor;
	private static int xLeaves;
	private static int oLeaves;
	private static int drawLeaves;
	
	/**
	 * The constructor for the GameTree class.
	 * @param boardNode
	 * The GameBoardNode which is to define the root and cursor of this GameTree.
	 */
	public GameTree(GameBoardNode boardNode){
		root = new GameBoardNode(boardNode.getBoard().clone(), boardNode.getCurrentTurn());
		cursor = new GameBoardNode(root.getBoard().clone(), root.getCurrentTurn());
	}
	
	/**
	 * A method to alter the board array contained in the cursor GameBoardNode.
	 * @param position
	 * The index of the board array to be changed.
	 * <dt><b>Preconditions</b><dd>
	 * The position passed in is an int between 1 and 9.
	 * <dt><b>Postconditions</b><dd>
	 * THe position passed in the method is now equal to the
	 * current turn of the cursor
	 * @throws
	 * If the integer passed in is outside of the acceptable range, or the the position
	 * trying to be changed is not EMPTY,an IllegalArgumentException is thrown
	 */
	public void makeMove(int position){
		if(0 <= position && position <= 9 ){
			if(cursor.getBoard().getSpace(position - 1).getBox() == Box.EMPTY.getBox()){
				cursor.getBoard().setSpace(position, cursor.getCurrentTurn());
			}
			else{
				throw new IllegalArgumentException();
			}
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * A method to get the root of the GameTree
	 * @return
	 * The root GameBoardNode of the GameTree
	 */
	public GameBoardNode getRoot(){
		return root;
	}

	/**
	 * A method to get the cursor of the GameTree
	 * @return
	 * The cursor GameBoardNode of the GameTree
	 */
	public GameBoardNode getCursor(){
		return cursor;
	}
	

	/**
	 * A method to set the cursor of the GameTree
	 * @param newNode
	 * The new GameBoardNode to be set equal to the cursor of the GameTree
	*/
	public void setCursor(GameBoardNode newNode){
		cursor = newNode;
	}
	
	/**
	 * A recursive method which creates the entire tree of possible moved from the current configuration
	 * of the GameBoard passed in with the GameBoardNode root.
	 * @param root
	 * The GameBoardNode which the tree needs to be built for
	 * @param turn
	 * The current turn of the GameBoardNode passed in
	 * @return
	 * The tree built from the current configuration of the GameBoard which has all of the 
	 * possible moves that can be made.
	 * 
	 */
	public static GameBoardNode buildTree(GameBoardNode root, Box turn){
		GameBoardNode[] nullArray = null;
		GameBoardNode tempNode = new GameBoardNode(root.getBoard().clone(), turn);
		for(int i =0; i <9 ; i++){
			tempNode.setConfig(tempNode.clone(), i);
		}
		for(int i = 0; i < 9; i++){
			if(tempNode.getBoard().getSpace(i) == Box.EMPTY){
				tempNode.getConfig(i).getBoard().setSpace(i+1, turn);
			}
			else{
				tempNode.setConfig(null, i);
			}
		}
		
		for(int i = 0; i < 9; i++){
			if(tempNode.getConfig(i) != null){
			switch(checkWin(tempNode.getConfig(i))){
			case X:
				tempNode.getConfig(i).setWinner(Box.X);
				increaseXLeaves();
				tempNode.getConfig(i).setIsEnd(true);
				tempNode.getConfig(i).setWholeConfig(nullArray);
				break;
			case O:
				tempNode.getConfig(i).setWinner(Box.O);
				increaseOLeaves();
				tempNode.getConfig(i).setIsEnd(true);
				tempNode.getConfig(i).setWholeConfig(nullArray);
				break;
			case DRAW:
				tempNode.getConfig(i).setWinner(Box.DRAW);
				increaseDrawLeaves();
				tempNode.getConfig(i).setIsEnd(true);
				tempNode.getConfig(i).setWholeConfig(nullArray);
				break;
			default:
				if(turn == Box.X){
					tempNode.setConfig(buildTree(tempNode.getConfig(i), Box.O), i);
				}
				else{
					tempNode.setConfig(buildTree(tempNode.getConfig(i), Box.X), i);
				}
				
			}
		}
			else{
				continue;
			}

		}
		GameBoardNode tempNode2 = tempNode.clone();
		return tempNode2;
	}
			
	/**
	 * A method to increment the number of xLeaves in the GameTree
	 * <dt><b>Postconditions</b><dd>
	 * The number of xLeaves has been incremented by one
	 */
	public static void increaseXLeaves(){
		xLeaves++;
	}
	/**
	 * A method to get the number of xLeaves in the GameTree, which is the number of possible ways that X can
	 * win from the current configuration of the GameBoard
	 * @return xLeaves
	 * The number of xLeaves contained in the GameTree
	 */
	public static int getXLeaves(){
		return xLeaves;
	}
	
	/**
	 * A method to get the number of oLeaves in the GameTree
	 * @return oLeaves
	 * The number of oLeaves contained in the GameTree
	 */
	public static int getOLeaves(){
		return oLeaves;
	}
	
	/**
	 * A method to get the number of drawLeaves in the GameTree
	 * @return drawLeaves
	 * The number of drawLeaves contained in the GameTree
	 */
	public static int getDrawLeaves(){
		return drawLeaves;
	}
	
	/**
	 * A method to increment the number of oLeaves in the GameTree
	 * <dt><b>Postconditions</b><dd>
	 * The number of oLeaves has been incremented by one
	 */
	public static void increaseOLeaves(){
		oLeaves++;
	}
	
	/**
	 * A method to increment the number of drawLeaves in the GameTree
	 * <dt><b>Postconditions</b><dd>
	 * The number of drawLeaves has been incremented by one
	 */
	public static void increaseDrawLeaves(){
		drawLeaves++;
	}
	
	/**
	 * A method to set all of the leaf variables to 0
	 * <dt><b>Postconditions</b><dd>
	 * The number of xLeaves, oLeaves, and drawLeaves has been set to 0
	 */
	public void resetLeaves(){
		xLeaves = 0;
		oLeaves = 0;
		drawLeaves = 0;
	}
	
	/**
	 * A method to check the current configuration of the GameBoard object to see if there is a winning
	 * configuration.
	 * @param node
	 * The GameBoardNode containing the GameBoard array to be checked for a winning configuration
	 * @return
	 * The Box of the winning player, a draw if the game is a draw or EMPTY if there is no winner and no draw
	 */
	public static Box checkWin(GameBoardNode node){
		boolean filled = true;
		for(int i = 0; i < node.getBoard().getBoardSize(); i++){
			if((node.getBoard().getSpace(0) == Box.X && node.getBoard().getSpace(1) == Box.X &&
				node.getBoard().getSpace(2) == Box.X) || (node.getBoard().getSpace(3) == Box.X && node.getBoard().getSpace(4) == Box.X &&
				node.getBoard().getSpace(5) == Box.X) || (node.getBoard().getSpace(6) == Box.X && node.getBoard().getSpace(7) == Box.X &&
				node.getBoard().getSpace(8) == Box.X) || (node.getBoard().getSpace(0) == Box.X && node.getBoard().getSpace(3) == Box.X &&
				node.getBoard().getSpace(6) == Box.X) || (node.getBoard().getSpace(1) == Box.X && node.getBoard().getSpace(4) == Box.X &&
				node.getBoard().getSpace(7) == Box.X) || (node.getBoard().getSpace(2) == Box.X && node.getBoard().getSpace(5) == Box.X &&
				node.getBoard().getSpace(8) == Box.X) || (node.getBoard().getSpace(0) == Box.X && node.getBoard().getSpace(4) == Box.X &&
				node.getBoard().getSpace(8) == Box.X) || (node.getBoard().getSpace(2) == Box.X && node.getBoard().getSpace(4) == Box.X &&
				node.getBoard().getSpace(6) == Box.X)){
			return Box.X;
		}
		else if((node.getBoard().getSpace(0) == Box.O && node.getBoard().getSpace(1) == Box.O &&
				node.getBoard().getSpace(2) == Box.O) || (node.getBoard().getSpace(3) == Box.O && node.getBoard().getSpace(4) == Box.O &&
				node.getBoard().getSpace(5) == Box.O) || (node.getBoard().getSpace(6) == Box.O && node.getBoard().getSpace(7) == Box.O &&
				node.getBoard().getSpace(8) == Box.O) || (node.getBoard().getSpace(0) == Box.O && node.getBoard().getSpace(3) == Box.O &&
				node.getBoard().getSpace(6) == Box.O) || (node.getBoard().getSpace(1) == Box.O && node.getBoard().getSpace(4) == Box.O &&
				node.getBoard().getSpace(7) == Box.O) || (node.getBoard().getSpace(2) == Box.O && node.getBoard().getSpace(5) == Box.O &&
				node.getBoard().getSpace(8) == Box.O) || (node.getBoard().getSpace(0) == Box.O && node.getBoard().getSpace(4) == Box.O &&
				node.getBoard().getSpace(8) == Box.O) || (node.getBoard().getSpace(2) == Box.O && node.getBoard().getSpace(4) == Box.O &&
				node.getBoard().getSpace(6) == Box.O)){
			return Box.O;
		}
		if(node.getBoard().getSpace(i) != Box.EMPTY){
			filled = true;
		}
		else{
			filled = false;
			break;
		}
		}
		if(filled){
			return Box.DRAW;
		}
		else{
			//this is really not a draw, this is just to continue gameplay
			return Box.EMPTY;
		}
		
	}
	
	/**
	 * A method to calculate the current probabilties of winning, losing and drawing from the current state of the
	 * game.
	 * @return
	 * An array of probabilities. probArray[0] = probability of winning. probArray[1] = probability of losing. probArray[2] = probability of drawing
	 */
	public double[] cursorProbability(){
		int win =0, lose = 0, draw = 0;
		double[] probArray = new double[3];
		resetLeaves();
		try{
		this.getCursor().setConfig(GameTree.buildTree(this.getCursor(), this.getCursor().getCurrentTurn()), 0);
		this.setCursor(this.getCursor().getConfig(0));
		}catch(NullPointerException e){
			System.out.println("I shouldnt be here");
		}
		
		win = GameTree.getXLeaves();
		lose = GameTree.getOLeaves();
		draw = GameTree.getDrawLeaves();
		int total = win + lose + draw;
		probArray[0] = (double)win/total;
		probArray[1] = (double)lose/total;
		probArray[2] = (double)draw/total;
		
		return probArray;
		
	}
}
