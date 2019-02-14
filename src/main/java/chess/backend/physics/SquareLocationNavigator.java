package chess.backend.physics;

/**
 * This class provides a method for determining the result of applying a Vector to a SquareLocation
 * 
 * @author Brian King
 *
 */
public class SquareLocationNavigator {

	/**
	 * Returns the resulting SquareLocation that vector will map to when starting at squareLocation
	 * 
	 * @param squareLocation the staring location of a piece
	 * @param vector the Vector that the piece is moving on
	 * 
	 * @return the resulting location that the piece will end on
	 */
	public SquareLocation applyVectorToSquareLocation(SquareLocation squareLocation, Vector vector) {
		//Create a SquareLocation object
		SquareLocation newSquareLocation = new SquareLocation();
		//Set the x and y to the sum of the squareLocation and the vector's x and y
		newSquareLocation.setX(squareLocation.getX() + vector.getX());
		newSquareLocation.setY(squareLocation.getY() + vector.getY());
		return newSquareLocation;
	}
}
