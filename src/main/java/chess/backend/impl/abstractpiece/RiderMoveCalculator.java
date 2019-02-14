package chess.backend.impl.abstractpiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.backend.MoveCalculator;
import chess.backend.physics.SquareLocation;
import chess.backend.physics.SquareLocationNavigator;
import chess.backend.physics.SquareStatus;
import chess.backend.physics.Vector;
import chess.json.Board.Move;

/**
 * This abstract class describes pieces that can move from their location along a vector
 * any number of times.  Examples in normal chess are the Rook, Bishop, and Queen
 * 
 * @author Brian King
 *
 */
public abstract class RiderMoveCalculator implements MoveCalculator {

	/**
	 * @return the list of Vectors that the piece can move along any number of times
	 */
	public abstract List<Vector> getVectors();
	
	//The navigator is used to find the result of applying Vectors to SquareLocations
	private SquareLocationNavigator squareLocationNavigator = new SquareLocationNavigator();
	
	/**
	 * Returns a List of all the moves that the specified piece can make
	 */
	@Override
	public List<Move> getPossibleMoves(SquareLocation pieceLocation, Map<SquareLocation, SquareStatus> locationStatusMap) {
		//Create a List to hold the answer
		List<Move> possibleMoves = new ArrayList<>();
		//Iterate though the vectors
		for (Vector vector : getVectors()) {
			//Store the current location and status
			SquareLocation currentLocation = pieceLocation;
			SquareStatus status = null;
			do {
				//Move the piece along the vector and get the status of the location
				currentLocation = squareLocationNavigator.applyVectorToSquareLocation(currentLocation, vector);
				status = locationStatusMap.get(currentLocation);
				//The move is valid if the square is empty or has an empty piece
				if (status==SquareStatus.ENEMY || status==SquareStatus.EMPTY) {
					//If the location is movable, create a new move
					Move move = new Move(
							//The starting (x,y) is from the pieceLocation
							pieceLocation.getX(), pieceLocation.getY(), 
							//The ending (x,y) is from the moveLocation
							currentLocation.getX(), currentLocation.getY()
						);
					//Add that move to the list of possible moves
					possibleMoves.add(move);
				}
				//Keep moving along the vector is the piece is empty
			} while (status==SquareStatus.EMPTY);
		}
		//Return the List of legal moves
		return possibleMoves;
	}

}
