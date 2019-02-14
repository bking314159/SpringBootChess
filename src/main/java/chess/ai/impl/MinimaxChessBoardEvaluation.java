package chess.ai.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chess.ai.ChessBoardEvaluation;
import chess.backend.BoardMover;
import chess.json.Board;
import chess.json.Board.Move;

/**
 * This class implements the ChessBoardEvaluation. It looks depth moves in the future.  Each time Black (AI)
 * can act, the agent chooses the maximum value.  Each time White (Player) makes a move, the agent 
 * assumes the player will pick the minimum value
 * 
 * @author Brian King
 *
 */
@Component("minimaxChessBoardEvaluation")
public class MinimaxChessBoardEvaluation implements ChessBoardEvaluation {

	private int depth;
	
	MinimaxChessBoardEvaluation() {
		this(2);
	}
	
	MinimaxChessBoardEvaluation(int depth) {
		this.depth = depth;
	}
	
	@Override
	public int evaluateBoard(Board board) {
		//Evaluate the board at the depth set at construction time
		return evaluateBoard(board, this.depth);
	}
	
	//Used to evaluate the base case (depth=0)
	@Autowired
	private ChessBoardEvaluation chessBoardEvaluation = new ChessBoardEvaluationImpl();
	
	//Used to determine how the board evolves over time given a move
	@Autowired
	private BoardMover boardMover;// = new BoardMoverWithCheckWithPawnPromotion();
	
	private int evaluateBoard(Board board, int currentDepth) {
		//BASE CASE
		if (currentDepth<=0) {
			//If the depth is 0, we use the ChessBoardEvaluation to return a value
			return chessBoardEvaluation.evaluateBoard(board);
		//RECURSIVE STEP
		} else {
			//Get all the boards that are next
			List<Board> boards = getBoardsAfterPossibleActions(board);
			//If there are no moves, compute the value for the end of the game
			if (board.getMoves().size()==0) {
				return computeEndOfGameValue(board);
			//If it is White's turn, pick the minimum value
			} else if (board.getActivePlayer().equals("White")) {
				return getMinValue(boards, currentDepth);
			//If it is Black's turn, pick the maximum value
			} else {
				return getMaxValue(boards, currentDepth);
			}
		}
	}
	
	private int computeEndOfGameValue(Board board) {
		//(This should only be called if there are no moves)
		if (board.getStatus().equals("In check!")) {
			if (board.getActivePlayer().equals("White")) {
				//If white is in check, we value the board at one million
				return 1000000;
			} else {
				//If we are in check, we value the board at negative one million
				return -100000;
			}
		} else {
			return -1000000;
		}
	}
	
	private List<Board> getBoardsAfterPossibleActions(Board board) {
		//Create a List for the resulting Boards
		List<Board> newBoards = new ArrayList<>();
		//Iterate though each possible Move
		for (Move move : board.getMoves()) {
			//Get the resulting board and add it to the list
			Board newBoard = boardMover.applyMoveToBoard(board, move);
			newBoards.add(newBoard);
		}
		//return the List of resulting boards
		return newBoards;
	}
	
	private int getMaxValue(List<Board> boards, int currentDepth) {
		//(This should only be called if there are at least 1 moves)
		//Set the initial max to the first board
		int max = evaluateBoard(boards.get(0), currentDepth-1);
		//Iterate though each board
		for (int i=1; i<boards.size(); i++) {
			//Recursively evaluate the board
			Board board = boards.get(i);
			int value = evaluateBoard(board, currentDepth-1);
			//If we have a new maximum, update max
			if (value>max) {
				max = value;
			}
		}
		//Return the max value
		return max;
	}
	
	private int getMinValue(List<Board> boards, int currentDepth) {
		//(This should only be called if there are at least 1 moves)
		//Set the initial min to the first board
		int min = evaluateBoard(boards.get(0), currentDepth-1);
		//Iterate though each board
		for (int i=1; i<boards.size(); i++) {
			//Recursively evaluate the board
			Board board = boards.get(i);
			int value = evaluateBoard(board, currentDepth-1);
			//If we have a new minimum, update min
			if (value<min) {
				min = value;
			}
		}
		//Return the min value
		return min;
	}
	 

}
