package chess.ai.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import chess.ai.ChessAi;
import chess.ai.ChessBoardEvaluation;
import chess.backend.BoardMover;
import chess.json.Board;
import chess.json.Board.Move;

/**
 * This class provides a chess agent that evaluates all possible moves at a given depth using
 * minimax, then randomly picks one of the maximum values
 * 
 * https://en.wikipedia.org/wiki/Minimax
 * 
 * @author Brian King
 *
 */
@Component("miniMaxChessAi")
public class MiniMaxChessAi implements ChessAi {

	public MiniMaxChessAi() {
		this(3);
	}
	
	/**
	 * Creates a chess AI agent that looks ahead a set number of moves in the futures.
	 * 
	 * The runtime is exponential relative to the number of moves
	 * 
	 * @param numberOfMovesToLookIntoTheFuture the number of moves to look ahead
	 */
	public MiniMaxChessAi(int numberOfMovesToLookIntoTheFuture) {
		//The depth of 1 is computed by a call to maximize(board), so we need to evaluate
		//one less that the specified depth
		int depthToExplore = numberOfMovesToLookIntoTheFuture-1;
		chessBoardEvaluation = new MinimaxChessBoardEvaluation(depthToExplore);
	}
	
	@Autowired
	private BoardMover boardMover;
	
	@Autowired
	@Qualifier("minimaxChessBoardEvaluation")
	private ChessBoardEvaluation chessBoardEvaluation;
	
	
	@Override
	public Move decideMove(Board board) {
		List<Move> possibleMoves = board.getMoves();
		//If there are no possible moves (such as checkmate or stalemate)
		//the AI returns null
		if (possibleMoves.size()==0) {
			return null;
		}
		//Get the List of Moves that produce a max value for the AI
		List<Move> maxActions = pickTheMaximumMoves(board);
		if (maxActions.size()==0) {
			return null;
		}
		//Pick one of these moves
		//(there may be a tie, break it with a random value)
		int randomIndex = (int)(Math.random()*maxActions.size());
		return maxActions.get(randomIndex);
	}
	
	private List<Move> pickTheMaximumMoves(Board board) {
		//Create a List to hold all Moves that produce a max value
		//(There may be a tie)
		List<Move> maxMoves = new ArrayList<>();
		//Hold the max value
		int max = 0;
		//Iterate though each possible move
		for (Move move : board.getMoves()) {
			//See the effect that the move will have on the board
			Board boardAfterMove = boardMover.applyMoveToBoard(board, move);
			//If there is not yet a max (the first time though the loop),
			//Add the Move as a maxMove and set the max value
			if (maxMoves.size()==0) {
				maxMoves.add(move);
				max = chessBoardEvaluation.evaluateBoard(boardAfterMove);
			//Else check if the new value is a new max or a tie
			} else {
				int newMax = chessBoardEvaluation.evaluateBoard(boardAfterMove);
				//If the new value is a tie, add it to the list
				if (newMax == max) {
					maxMoves.add(move);
				//If the new value is a new max, create a whole new list and
				//add the value
				} else if (newMax > max) {
					maxMoves = new ArrayList<>();
					maxMoves.add(move);
					max = newMax;
				}
			}
		}
		//return the List of moves that tie for the best value
		return maxMoves;
	}
	
}
