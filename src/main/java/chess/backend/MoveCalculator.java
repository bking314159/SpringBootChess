package chess.backend;

import java.util.List;
import java.util.Map;

import chess.backend.physics.SquareLocation;
import chess.backend.physics.SquareStatus;
import chess.json.Board.Move;

/**
 * This interface provides a method that returns a List of all Moves that a single piece can make
 * 
 * @author Brian King
 *
 */
public interface MoveCalculator {

	/**
	 * Returns a List of all the moves that the specified piece can make
	 * 
	 * @param pieceLocation the location of the piece
	 * @param locationStatusMap a map from SquareLocations to SquareStatus that represent the pieces on the board
	 * 
	 * @return a List of Moves that the piece on pieceLocation can make
	 */
	List<Move> getPossibleMoves(SquareLocation pieceLocation, Map<SquareLocation, SquareStatus> locationStatusMap);


}
