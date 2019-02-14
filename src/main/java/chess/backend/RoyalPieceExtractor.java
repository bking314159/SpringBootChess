package chess.backend;

import java.util.Set;

import chess.json.Board;
import chess.json.Board.Square;

/**
 * This interface provides a method for locating all the Royal pieces on the
 * Board
 * 
 * @author Brian King
 *
 */
public interface RoyalPieceExtractor {

	/**
	 * Given a board and a targetPlayer, this method returns all Squares that have
	 * a Royal piece (In normal chess, the Set will only contain the King)
	 * 
	 * @param board the current state of the game
	 * @param targetPlayer the owner of the Royal pieces
	 * 
	 * @return all the squares that contain a royal piece owned by targetPlayer
	 */
	Set<Square> extractRoyalPieces(Board board, String targetPlayer);

}
