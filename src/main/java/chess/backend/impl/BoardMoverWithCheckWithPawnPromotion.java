package chess.backend.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chess.backend.SquareLocationFactory;
import chess.backend.SquareStatusMapBuilder;
import chess.backend.impl.concretepiece.PawnMoveCalculator;
import chess.backend.physics.SquareLocation;
import chess.backend.physics.SquareStatus;
import chess.json.Board;
import chess.json.Board.Move;
import chess.json.Board.Square;
import chess.json.Board.Square.Piece;

/**
 * This BoardMover uses BoardMoverWithoutReplacementImpl to determine 
 * @author Brian
 *
 */
@Component("boardMoverWithCheckWithPawnPromotion")
public class BoardMoverWithCheckWithPawnPromotion extends BoardMoverWithCheckWithoutPawnPromotion {
	
	//Used to build a map to determine the status of all squares on the board
	@Autowired
	private SquareStatusMapBuilder squareStatusMapBuilder;
	
	//Used to convert all the Square Objects on the board into SquareLocations
	@Autowired
	private SquareLocationFactory squareLocationFactory;
	
	/**
	 * 
	 */
	@Override
	public Board applyMoveToBoard(Board board, Move move) {
		//Board newBoard = boardMoverWithoutReplacement.applyMoveToBoard(board, move);
		Board newBoard = super.applyMoveToBoard(board, move);
		
		//Use the SquareStatusMapBuilder to create a map of SquareStatus on the board
		Map<SquareLocation, SquareStatus> locationStatusMap 
			= squareStatusMapBuilder.buildSquareStatusMap(board.getActivePlayer(), board.getSquares());
		for (Square square : newBoard.getSquares()) {
			Piece piece = square.getPiece();
			if (piece!=null && piece.getType().equals("Pawn")) {
				//Convert the square with an active piece into a usable SquareLocation key
				SquareLocation pieceLocation = squareLocationFactory.buildSquareLocation(square);
				
				PawnMoveCalculator pawnMoveCalculator = new PawnMoveCalculator(piece.getOwner(), piece.isHasMoved());
				if (pawnMoveCalculator.pawnCanBePromoted(pieceLocation, locationStatusMap)) {
					square.setPiece(new Piece(piece.getOwner(), "Queen", false));
				}
			}
		}
		return newBoard;
	}

}
