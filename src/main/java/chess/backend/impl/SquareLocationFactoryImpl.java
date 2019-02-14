package chess.backend.impl;

import org.springframework.stereotype.Component;

import chess.backend.SquareLocationFactory;
import chess.backend.physics.SquareLocation;
import chess.json.Board.Square;

/**
 * This class implements SquareLocationFactory and provides a method for returning
 *  a SquareLocation given a Square
 * 
 * @author Brian King
 *
 */
@Component("squareLocationFactoryImpl")
public class SquareLocationFactoryImpl implements SquareLocationFactory {

	@Override
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
	public SquareLocation buildSquareLocation(Square square) {
		//Create and return a square location with the same (x,y)
		SquareLocation squareLocation = new SquareLocation();
		squareLocation.setX(square.getX());
		squareLocation.setY(square.getY());
		return squareLocation;
	}

}
