package chess.backend;

import org.springframework.stereotype.Component;

import chess.json.Board;

/**
 * This interface provides a method for determining if the active player of
 * the Board is in check
 * 
 * @author Brian King
 *
 */
@Component("checkDetector")
public interface CheckDetector {

	/**
	 * Returns true if and only if the active player of the board is in check
	 * 
	 * @param board the current state of the board
	 * 
	 * @return true if the player is in check
	 */
	boolean detectIfPlayerIsInCheck(Board board);
	
}
