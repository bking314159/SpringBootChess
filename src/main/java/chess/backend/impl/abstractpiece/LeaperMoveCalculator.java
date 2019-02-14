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
 * one time.  Examples in normal chess are the King and Knight
 * 
 * @author Brian King
 *
 */
public abstract class LeaperMoveCalculator implements MoveCalculator {

	/**
	 * @return the list of Vectors that the piece can move along one time
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
			//Apply the vector to the location to find the move location.  Get the status of the location
			SquareLocation moveLocation = squareLocationNavigator.applyVectorToSquareLocation(pieceLocation, vector);
			SquareStatus status = locationStatusMap.get(moveLocation);
			//The move is valid if the square is empty or has an empty piece
			if (status==SquareStatus.ENEMY || status==SquareStatus.EMPTY) {
				//If the location is movable, create a new move
				Move move = new Move(
						//The starting (x,y) is from the pieceLocation
						pieceLocation.getX(), pieceLocation.getY(), 
						//The ending (x,y) is from the moveLocation
						moveLocation.getX(), moveLocation.getY()
					);
				//Add that move to the list of possible moves
				possibleMoves.add(move);
			}
		}
		//Return the List of legal moves
		return possibleMoves;
	}

}
