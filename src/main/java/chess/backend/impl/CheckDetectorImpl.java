package chess.backend.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chess.backend.BoardMoveGenerator;
import chess.backend.CheckDetector;
import chess.backend.RoyalPieceExtractor;
import chess.json.Board;
import chess.json.Board.Move;
import chess.json.Board.Square;

/**
 * This class implements CheckDetector and provides a method for determining if the active player of
 * the Board is in check
 * 
 * @author Brian King
 *
 */
@Component
public class CheckDetectorImpl implements CheckDetector {

	//Used to get the location of all royal pieces
	@Autowired
	private RoyalPieceExtractor royalPieceExtractor;
	
	//Used to see if enemy moves can capture a royal
	@Autowired
	private BoardMoveGenerator boardMoveGenerator;
	
	@Override
	/**
	 * Returns true if and only if the active player of the board is in check
	 * 
	 * @param board the current state of the board
	 * 
	 * @return true if the player is in check
	 */
	public boolean detectIfPlayerIsInCheck(Board board) {
		//Get the attacking player (The player who is not currently moving)
		String attackingPlayer = toggleActivePlayer(board.getActivePlayer());
		//Get all the square with royal pieces in them
		Set<Square> squares = royalPieceExtractor.extractRoyalPieces(board, board.getActivePlayer());
		//Get all possible moves that the enemy can make (NOT the player whos turn it is)
		List<Move> moves = boardMoveGenerator.generatePossibleMoves(attackingPlayer, board.getSquares());
		for (Move move : moves) {
			//If the enemy can make a move that puts the current player in check, return true
			if (moveEndsInASquareWithARoyalPiece(move.getX2(), move.getY2(), squares)) {
				return true;
			}
		}
		return false;
	}
	
	private String toggleActivePlayer(String activePlayer) {
		//Returns the opposite of who's turn it is
		if ("Black".equals(activePlayer)) {
			return "White";
		} else {
			return "Black";
		}
	}
	
	private boolean moveEndsInASquareWithARoyalPiece(int x, int y, Set<Square> squares) {
		//Returns true if (x,y) is in the set of squares
		for (Square square : squares) {
			if (square.getX()==x && square.getY()==y) {
				return true;
			}
		}
		return false;
	}

}
