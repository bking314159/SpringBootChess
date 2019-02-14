package chess.backend;

import java.util.List;

import chess.json.Board.Square;

/**
 * This interface provides a method for extracting all the Squares that have an active piece
 * owned by the specified player
 * 
 * @author Brian King
 *
 */
public interface ActivePieceExtractor {

	/**
	 * Given a player, this method returns a all the squares with a piece owned by that player
	 * 
	 * @param activePlayer The target player (Usually the string "Black" or "White"
	 * @param squares The list of Squares on the board
	 * 
	 * @return a List of Squares that have a non-null piece owned by activePlayer
	 */
	List<Square> extractSquaresWithActivePieces(String activePlayer, List<Square> squares);

}
