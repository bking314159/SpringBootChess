package chess.backend;

import chess.backend.physics.SquareLocation;
import chess.json.Board.Square;

/**
 * This interface provides a method for returning a SquareLocation
 * given a Square
 * 
 * @author Brian King
 *
 */
public interface SquareLocationFactory {

	/**
	 * This method returns a SquareLocation given a Square
	 * 
	 * (The Square Object can have a piece field.  The SquareLocation
	 * simply has an x,y location)
	 * 
	 * @param square a Square with (x,y) and a piece
	 * 
	 * @return a SquareLocation with identical (x,y)
	 */
	SquareLocation buildSquareLocation(Square square);

}
