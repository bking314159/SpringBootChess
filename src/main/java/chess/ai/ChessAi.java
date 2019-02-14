package chess.ai;

import chess.json.Board;
import chess.json.Board.Move;

/**
 * This interface provides the outline for a Chess playing AI.  It provides a method
 * that will produce a Move given a Board
 * 
 * @author Brian King
 *
 */
public interface ChessAi {

	/**
	 * Given the state of the game, the AI will produce a move
	 * 
	 * @param board the state of the board
	 * @return a Move that the AI wishes to make
	 */
	public Move decideMove(Board board);

}
