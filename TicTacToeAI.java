import java.util.InputMismatchException;
import java.util.Scanner;


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
 * THe driver class for the Tic-Tac-Toe game. Enters into an infinite loop where the player
 * can play against an AI in Tic-Tac-Toe endlessly.
 *
 */
public class TicTacToeAI {
	public static void main(String[] args){
		
		
		
		int run = 1;
		while(true){
			System.out.println("****Game " + run + "****");
			
			GameBoard board = new GameBoard();
			GameBoardNode root = new GameBoardNode(board, Box.X);
		
			GameTree game =new GameTree(root);
			
			System.out.println("Welcome to Tic Tac Toe!");
			System.out.println("Instructions:\n");
			for(int i = 0 ; i < game.getRoot().getBoard().getBoardSize(); i++){
				System.out.print("|" + (i + 1) + "|");
				if(i == 2 || i== 5 || i==8){
					System.out.print("\n");
				}
			}
			

			playGame(game);
			
			
			run++;
		}
		
		
	}
	
	/**
	 * A static recursive method which controls the flow of the game.
	 * @param tree
	 * The current GameTree configuration being played on.
	 */
	public static void playGame(GameTree tree){
		int move = 0;
		double winProb = 0, loseProb = 0, drawProb = 0;
		if(tree.getCursor().getEnd() == true || GameTree.checkWin(tree.getCursor()) != Box.EMPTY){
			return;
		}
		else{
		Scanner input = new Scanner(System.in);
		boolean legal = true;
		while(legal){
		System.out.println("Please make a move");
		try{
		move = input.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Please enter a valid spot");
			input.nextLine();
			continue;
		}catch(IllegalArgumentException e){
			System.out.println("This spot is taken!");
			input.nextLine();
			continue;
		}
		
		if(move < 1 || move > 9){
			System.out.println("Pick a valid spot!");
			input.nextLine();
			continue;
		}
		else{
			try{
			tree.makeMove(move);
			legal = false;
			}catch(IllegalArgumentException e){
				System.out.println("This space is already filled!");
				continue;
			}
		}
			if(tree.getCursor().getBoard().isFull()){
				System.out.println(tree.getCursor().toString());
				System.out.println("The game is a draw!");
				return;
			}
			tree.getCursor().setCurrentTurn(Box.O);
			
			
			tree.getCursor().setConfig(GameTree.buildTree(tree.getCursor(), tree.getCursor().getCurrentTurn()), 0);
			tree.setCursor(tree.getCursor().getConfig(0));
			
			double highestProb = 0;
			int path = 0;
			boolean end = false;
			
			if(tree.getCursor().getWholeConfig() != null){
				for(int i =0; i <9; i++){
					if(tree.getCursor().getConfig(i) != null){
						if(tree.getCursor().getConfig(i).getWinner() == Box.O){
								path = i;
								end = true;
								break;
						}else{ 
							continue;
						}
					}else{
						continue;
					}
				}
			}
			if(!end){
				if(tree.getCursor().getBoard().getSpace(0) == Box.X && tree.getCursor().getBoard().getSpace(1) == Box.X && tree.getCursor().getBoard().getSpace(2) == Box.EMPTY ||
					tree.getCursor().getBoard().getSpace(2) == Box.EMPTY && tree.getCursor().getBoard().getSpace(4) == Box.X && tree.getCursor().getBoard().getSpace(6) == Box.X ||
					tree.getCursor().getBoard().getSpace(2) == Box.EMPTY && tree.getCursor().getBoard().getSpace(5) == Box.X && tree.getCursor().getBoard().getSpace(8) == Box.X){
					path = 2;
				}else if(tree.getCursor().getBoard().getSpace(0) == Box.X && tree.getCursor().getBoard().getSpace(1) == Box.EMPTY && tree.getCursor().getBoard().getSpace(2) == Box.X ||
					tree.getCursor().getBoard().getSpace(7) == Box.X && tree.getCursor().getBoard().getSpace(1) == Box.EMPTY && tree.getCursor().getBoard().getSpace(4) == Box.X){
					path = 1;
				}else if(tree.getCursor().getBoard().getSpace(0) == Box.EMPTY && tree.getCursor().getBoard().getSpace(1) == Box.X && tree.getCursor().getBoard().getSpace(2) == Box.X ||
					tree.getCursor().getBoard().getSpace(0) == Box.EMPTY && tree.getCursor().getBoard().getSpace(4) == Box.X && tree.getCursor().getBoard().getSpace(8) == Box.X ||
					tree.getCursor().getBoard().getSpace(0) == Box.EMPTY && tree.getCursor().getBoard().getSpace(3) == Box.X && tree.getCursor().getBoard().getSpace(6) == Box.X){
						path = 0;
				}else if(tree.getCursor().getBoard().getSpace(3) == Box.EMPTY && tree.getCursor().getBoard().getSpace(4) == Box.X && tree.getCursor().getBoard().getSpace(5) == Box.X ||
						tree.getCursor().getBoard().getSpace(3) == Box.EMPTY && tree.getCursor().getBoard().getSpace(0) == Box.X && tree.getCursor().getBoard().getSpace(6) == Box.X ){
					path = 3;
				}else if(tree.getCursor().getBoard().getSpace(4) == Box.EMPTY && tree.getCursor().getBoard().getSpace(0) == Box.X && tree.getCursor().getBoard().getSpace(8) == Box.X ||
						tree.getCursor().getBoard().getSpace(4) == Box.EMPTY && tree.getCursor().getBoard().getSpace(2) == Box.X && tree.getCursor().getBoard().getSpace(6) == Box.X ||
						tree.getCursor().getBoard().getSpace(4) == Box.EMPTY && tree.getCursor().getBoard().getSpace(1) == Box.X && tree.getCursor().getBoard().getSpace(7) == Box.X ||
						tree.getCursor().getBoard().getSpace(4) == Box.EMPTY && tree.getCursor().getBoard().getSpace(3) == Box.X && tree.getCursor().getBoard().getSpace(5) == Box.X){
					path = 4;
				}else if(tree.getCursor().getBoard().getSpace(5) == Box.EMPTY && tree.getCursor().getBoard().getSpace(2) == Box.X && tree.getCursor().getBoard().getSpace(8) == Box.X ||
						tree.getCursor().getBoard().getSpace(5) == Box.EMPTY && tree.getCursor().getBoard().getSpace(3) == Box.X && tree.getCursor().getBoard().getSpace(4) == Box.X ){
					path = 5;
				}else if(tree.getCursor().getBoard().getSpace(6) == Box.EMPTY && tree.getCursor().getBoard().getSpace(0) == Box.X && tree.getCursor().getBoard().getSpace(3) == Box.X ||
						tree.getCursor().getBoard().getSpace(6) == Box.EMPTY && tree.getCursor().getBoard().getSpace(7) == Box.X && tree.getCursor().getBoard().getSpace(8) == Box.X ||
						tree.getCursor().getBoard().getSpace(6) == Box.EMPTY && tree.getCursor().getBoard().getSpace(2) == Box.X && tree.getCursor().getBoard().getSpace(4) == Box.X){
					path = 6;
				}else if(tree.getCursor().getBoard().getSpace(7) == Box.EMPTY && tree.getCursor().getBoard().getSpace(4) == Box.X && tree.getCursor().getBoard().getSpace(1) == Box.X ||
						tree.getCursor().getBoard().getSpace(7) == Box.EMPTY && tree.getCursor().getBoard().getSpace(8) == Box.X && tree.getCursor().getBoard().getSpace(6) == Box.X ){
					path = 7;
				}else if(tree.getCursor().getBoard().getSpace(8) == Box.EMPTY && tree.getCursor().getBoard().getSpace(0) == Box.X && tree.getCursor().getBoard().getSpace(4) == Box.X ||
						tree.getCursor().getBoard().getSpace(8) == Box.EMPTY && tree.getCursor().getBoard().getSpace(7) == Box.X && tree.getCursor().getBoard().getSpace(6) == Box.X ||
						tree.getCursor().getBoard().getSpace(8) == Box.EMPTY && tree.getCursor().getBoard().getSpace(2) == Box.X && tree.getCursor().getBoard().getSpace(5) == Box.X){
					path = 8;
				}
				else{
					for(int i = 0 ; i < 9; i++){
						if(tree.getCursor().getConfig(i) != null){
							GameTree tempTree = new GameTree(tree.getCursor().getConfig(i).clone());
							double[] possibleProbs = new double[3];
							possibleProbs =tempTree.cursorProbability();
							
							if(possibleProbs[1] > highestProb){
								highestProb = possibleProbs[1];
								path = i;
							}else if(path == 0){
								path = i;
							}
						}
					}
				
				}
				}
			tree.setCursor(tree.getCursor().getConfig(path));
			
			System.out.println(tree.getCursor().toString());
			if(tree.getCursor().getWholeConfig() == null){
				Box winner = GameTree.checkWin(tree.getCursor());
				tree.getCursor().setWinner(winner);
				if(tree.getCursor().getWinner() == Box.X){
					System.out.println("X is the winner!");
				}else if(tree.getCursor().getWinner() == Box.O){
					System.out.println("O is the winner!");
				}else if(tree.getCursor().getWinner() == Box.DRAW){
					System.out.println("The game is a draw!");
				}
				return;
			}
			
			double[] possibleProbs = tree.cursorProbability();
			winProb = possibleProbs[0];
			loseProb = possibleProbs[1];
			drawProb = possibleProbs[2];
			
			
			
			System.out.printf("Win Probability: " + "%.2f\n", winProb);
			System.out.printf("Lose Probability: " + "%.2f\n", loseProb);
			System.out.printf("Draw Probability: " + "%.2f\n", drawProb);
			GameTree newTree = new GameTree(tree.getCursor().clone());
			playGame(newTree);
		}
		}
	}
}
