package chess.json;

import java.util.ArrayList;
import java.util.List;

import chess.json.Board.Move;
import chess.json.Board.Square;
import chess.json.Board.Square.Piece;

public class DefaultBoardGenerator {

	public Board generateDefaultBoard() {
		//Create a list of Square Objects on the board
		List<Square> squares = generateDefaultSquares();
		//Create a list of legal first moves
		List<Move> moves = generateDefaultMoves();
		//Create a list of pieces that can be attained when
		//a pawn reaches the back row
		List<String> promotionPieces = generatePromotionPieces();
		//Create a board that contains squares and moves
		//(It is "White"s turn,
		//and pawns can become Rooks, Knights, Bishops, and Queens)
		Board board = new Board();
		board.setActivePlayer("White");
		board.setSquares(squares);
		board.setMoves(moves);
		board.setPromotionPieces(promotionPieces);
		//Return the default staring board for chess
		return board;
	}
	
	private List<Square> generateDefaultSquares() {
		//Create a list of Square Objects on the board
		List<Square> squares = new ArrayList<>();
		//Create the back row of White
		squares.add(new Square(1, 1, new Piece("White", "Rook", false)));
		squares.add(new Square(1, 2, new Piece("White", "Knight", false)));
		squares.add(new Square(1, 3, new Piece("White", "Bishop", false)));
		squares.add(new Square(1, 4, new Piece("White", "Queen", false)));
		squares.add(new Square(1, 5, new Piece("White", "King", true)));
		squares.add(new Square(1, 6, new Piece("White", "Bishop", false)));
		squares.add(new Square(1, 7, new Piece("White", "Knight", false)));
		squares.add(new Square(1, 8, new Piece("White", "Rook", false)));
		//Create the front row of White (all pawns)
		for (int i=1; i<=8; i++) {
			squares.add(new Square(2, i, new Piece("White", "Pawn", false)));
		}
		//Create the empty squares in the middle of the board
		for (int i=3; i<=6; i++) {
			for (int j=1; j<=8; j++) {
				//The null represents no piece is on this square
				squares.add(new Square(i, j, null));
			}
		}
		//Create the front row of Black (all pawns)
		for (int i=1; i<=8; i++) {
			squares.add(new Square(7, i, new Piece("Black", "Pawn", false)));
		}
		//Create the back row of Black
		squares.add(new Square(8, 1, new Piece("Black", "Rook", false)));
		squares.add(new Square(8, 2, new Piece("Black", "Knight", false)));
		squares.add(new Square(8, 3, new Piece("Black", "Bishop", false)));
		squares.add(new Square(8, 4, new Piece("Black", "Queen", false)));
		squares.add(new Square(8, 5, new Piece("Black", "King", true)));
		squares.add(new Square(8, 6, new Piece("Black", "Bishop", false)));
		squares.add(new Square(8, 7, new Piece("Black", "Knight", false)));
		squares.add(new Square(8, 8, new Piece("Black", "Rook", false)));
		return squares;
	}
	
	private List<Move> generateDefaultMoves() {
		//Create a list of legal moves
		List<Move> moves = new ArrayList<>();
		//Pawns can move forward one or two
		for (int i=1; i<=8; i++) {
			moves.add(new Move(2, i, 3, i));
			moves.add(new Move(2, i, 4, i));
		}
		//The left knight can move forward left and right
		moves.add(new Move(1, 2, 3, 1));
		moves.add(new Move(1, 2, 3, 3));
		//The right knight can move forward left and right
		moves.add(new Move(1, 7, 3, 6));
		moves.add(new Move(1, 7, 3, 8));
		//Return a list of the legal moves
		return moves;
	}
	
	private List<String> generatePromotionPieces() {
		//A pawn can be promoted to a Rook, Knight, Bishop, or Queen
		List<String> promotionPieces = new ArrayList<>();
		promotionPieces.add("Rook");
		promotionPieces.add("Knight");
		promotionPieces.add("Bishop");
		promotionPieces.add("Queen");
		return promotionPieces;
	}
}
