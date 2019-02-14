package chess.backend;

import java.util.List;

import chess.json.Board.Move;
import chess.json.Board.Square;

/**
 * This interface provides a method for returning a  List of possible moves 
 * that a target player can make given a list of Squares on the board
 * 
 * @author Brian King
 *
 */
public interface BoardMoveGenerator {

	/**
	 * This method returns a list of possible Moves that activePlayer can make, given
	 * a List of Squares that have pieces
	 * 
	 * @param activePlayer is the target player (Usually the String "Black" or "White")
	 * @param squares the list of Squares on the board
	 * 
	 * @return a List of Moves that activePlayer can make
	 */
	List<Move> generatePossibleMoves(String activePlayer, List<Square> squares);
	
}
