package chess.backend.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import chess.backend.RoyalPieceExtractor;
import chess.json.Board;
import chess.json.Board.Square;
import chess.json.Board.Square.Piece;

/**
 * This class implements RoyalPieceExtractor and provides a method for locating all the Royal 
 * pieces on the Board
 * 
 * @author Brian King
 *
 */
@Component("royalPieceExtractor")
public class RoyalPieceExtractorImpl implements RoyalPieceExtractor {

	@Override
	/**
	 * Given a board and a targetPlayer, this method returns all Squares that have
	 * a Royal piece (In normal chess, the Set will only contain the King)
	 * 
	 * @param board the current state of the game
	 * @param targetPlayer the owner of the Royal pieces
	 * 
	 * @return all the squares that contain a royal piece owned by targetPlayer
	 */
	public Set<Square> extractRoyalPieces(Board board, String targetPlayer) {
		//Create a set for all the Squares that contain a Royal Piece
		Set<Square> squaresWithRoyalPieces = new HashSet<>();
		for (Square square : board.getSquares()) {
			Piece piece = square.getPiece();
			//If the square has a piece that is owned by the target player and that piece is royal...
			if (piece!=null && piece.getOwner().equals(targetPlayer) && piece.isRoyal()) {
				//... add it to the set
				squaresWithRoyalPieces.add(square);
			}
		}
		//return the answers
		return squaresWithRoyalPieces;
	}

}
