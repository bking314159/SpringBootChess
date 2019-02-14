package chess.backend.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import chess.backend.ActivePieceExtractor;
import chess.json.Board.Square;

/**
 * The default implementation of the ActivePieceExtractor interface
 * 
 * @author Brian King
 *
 */
@Component("activePieceExtractorImpl")
public class ActivePieceExtractorImpl implements ActivePieceExtractor {

	/**
	 * Given a player, this method returns a all the squares with a piece owned by that player
	 * 
	 * @param activePlayer The target player (Usually the string "Black" or "White"
	 * @param squares The list of Squares on the board
	 * 
	 * @return a List of Squares that have a non-null piece owned by activePlayer
	 */
	@Override
	public List<Square> extractSquaresWithActivePieces(String activePlayer, List<Square> squares) {
		//Create a List to return the squares with active pieces
		List<Square> activeSquares = new ArrayList<>();
		//Iterate though each square
		for (Square square : squares) {
			//If the square has a piece (is non-null) and the owner of the piece is the active player...
			if (square.getPiece()!=null && square.getPiece().getOwner().equals(activePlayer)) {
				//... then add the square to the list of active pieces squares
				activeSquares.add(square);
			}
		}
		//return all squares with active pieces
		return activeSquares;
	}

}
