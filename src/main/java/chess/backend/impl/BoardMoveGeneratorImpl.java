package chess.backend.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chess.backend.ActivePieceExtractor;
import chess.backend.BoardMoveGenerator;
import chess.backend.MoveCalculator;
import chess.backend.MoveCalculatorFactory;
import chess.backend.SquareLocationFactory;
import chess.backend.SquareStatusMapBuilder;
import chess.backend.physics.SquareLocation;
import chess.backend.physics.SquareStatus;
import chess.json.Board.Move;
import chess.json.Board.Square;
import chess.json.Board.Square.Piece;

/**
 * The default implementation of the BoardMoveGenerator interface
 * 
 * @author Brian King
 *
 */
@Component("boardMoveGeneratorImpl")
public class BoardMoveGeneratorImpl implements BoardMoveGenerator {
	
	//Used to build a map to determine the status of all squares on the board
	@Autowired
	private SquareStatusMapBuilder squareStatusMapBuilder;
	
	//Used to find all pieces owned by activePlayer
	@Autowired
	private ActivePieceExtractor activePieceExtractor;
	
	//Used to convert all the Square Objects on the board into SquareLocations
	@Autowired
	private SquareLocationFactory squareLocationFactory;
	
	//Used to determine the legal moves that a piece type can make
	@Autowired
	private MoveCalculatorFactory moveCalculatorFactory;
	
	/**
	 * This method returns a list of possible Moves that activePlayer can make, given
	 * a List of Squares that have pieces
	 * 
	 * @param activePlayer is the target player (Usually the String "Black" or "White")
	 * @param squares the list of Squares on the board
	 * 
	 * @return a List of Moves that activePlayer can make
	 */
	@Override
	public List<Move> generatePossibleMoves(String activePlayer, List<Square> squares) {
		//Use the SquareStatusMapBuilder to create a map of SquareStatus on the board
		Map<SquareLocation, SquareStatus> locationStatusMap = squareStatusMapBuilder.buildSquareStatusMap(activePlayer, squares);
		//Create a list of legal moves to return
		List<Move> moves = new ArrayList<>();
		//Determine all the active pieces of the active player and iterate though them
		List<Square> sqauresWithActivePieces = activePieceExtractor.extractSquaresWithActivePieces(activePlayer, squares);
		for (Square square : sqauresWithActivePieces) {
			//Convert the square with an active piece into a usable SquareLocation key
			SquareLocation pieceLocation = squareLocationFactory.buildSquareLocation(square);
			//Get the piece on the target square and build its MoveCalculator using the factory
			Piece piece = square.getPiece();
			MoveCalculator moveCalculator = moveCalculatorFactory.buildMoveCalculator(piece);
			List<Move> movesWithPiece = moveCalculator.getPossibleMoves(pieceLocation, locationStatusMap);
			moves.addAll(movesWithPiece);
		}
		//return the list of legal moves
		return moves;
	}

	
}
