package chess.backend;

import java.util.List;
import java.util.Map;

import chess.backend.physics.SquareLocation;
import chess.backend.physics.SquareStatus;
import chess.json.Board.Square;

/**
 * This interface provides a method for constructing a map that represents the state of each Square
 * on the board
 * 
 * @author Brian King
 *
 */
public interface SquareStatusMapBuilder {

	/**
	 * Given an active player and a List of Square objects, this method returns a map from SquareLocations
	 * (that correspond to the squares) to SquareStatus (such a FRIENDLY, ENEMY, EMPTY)
	 * 
	 * @param activePlayer the player that will be FRIENDLY
	 * @param squares all the Squares on the board
	 * 
	 * @return a map that will give the status of each SquareLocation
	 */
	Map<SquareLocation, SquareStatus> buildSquareStatusMap(String activePlayer, List<Square> squares);

}
