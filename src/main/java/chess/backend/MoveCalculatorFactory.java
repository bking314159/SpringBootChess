package chess.backend;

import chess.json.Board.Square.Piece;

/**
 * This interface provides a method that will return a MoveCalculator given a Piece
 * 
 * @author Brian King
 *
 */
public interface MoveCalculatorFactory {

	/**
	 * Given a piece, this factory will return a MoveCalculator. The MoveCalculator will
	 *  construct the moves that piece can make
	 * 
	 * @param piece target Piece
	 * 
	 * @return a MoveCalculator used to find Moves piece can make
	 */
	MoveCalculator buildMoveCalculator(Piece piece);

}
