package chess.backend;

import chess.json.Board;
import chess.json.Board.Move;

/**
 * This interface provides a method for returning the resulting board
 * given a Board and a Move
 * 
 * @author Brian King
 *
 */
public interface BoardMover {

	/**
	 * Returns a new Board Object that represents the state of the game
	 * when a move is made
	 * 
	 * @param board the starting board
	 * @param move a legal move that a player or AI wishes to execute
	 * 
	 * @return a new Board Object representing the result of move on board
	 */
	Board applyMoveToBoard(Board board, Move move);
	
}
