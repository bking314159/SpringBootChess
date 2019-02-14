package chess.ai.impl;

import org.springframework.stereotype.Component;

import chess.ai.ChessBoardEvaluation;
import chess.json.Board;
import chess.json.Board.Square;
import chess.json.Board.Square.Piece;

/**
 * This class implements the ChessBoardEvaluation interface. Given a board, it computes the sum
 * of all the pieces owned by Black (AI) minus the sum of all the pieces owned by White (Player)
 * 
 * @author Brian King
 *
 */
@Component("chessBoardEvaluationImpl")
public class ChessBoardEvaluationImpl implements ChessBoardEvaluation {

	@Override
	/**
	 * Given the state of the game, an int value is returned that represents the percieved value of
	 * the board such that a higher int value indicates a better position for the AI.
	 * 
	 * The values are derived from the average score found at:
	 * https://en.wikipedia.org/wiki/Chess_piece_relative_value
	 * 
	 * @param board the state of the game
	 * @return the perceived value of the AIs position
	 */
	public int evaluateBoard(Board board) {
		//Create a variable for the answer
		int evaluation = 0;
		//Iterate though each square
		for (Square square : board.getSquares()) {
			Piece piece = square.getPiece();
			//If the square has a piece
			if (piece!=null) {
				//Determine if the piece is owned by the AI or the player
				//The AIs pieces will be positive (*1) and the players will
				//be negative (*-1)
				double coef = 1;
				if (piece.getOwner().equals("White")) {
					coef = -1;
				}
				//Get the value of the piece
				double value = getPieceValue(piece.getType());
				//Add or subtract the value from the answer
				evaluation += coef * value;
			}
		}
		//Return the answer
		return evaluation;
	}

	private int getPieceValue(String type) {
		/*
		 * https://en.wikipedia.org/wiki/Chess_piece_relative_value
		 */
		if (type.equals("Pawn")) {
			return 10;
		} else if (type.equals("Rook")) {
			return 52;
		} else if (type.equals("Knight")) {
			return 32;
		} else if (type.equals("Bishop")) {
			return 33;
		} else if (type.equals("King")) {
			return 35;
		} else if (type.equals("Queen")) {
			return 96;
		} else {
			return 0;
		}
	}

}
