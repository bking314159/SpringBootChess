package chess.backend.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import chess.backend.BoardMoveGenerator;
import chess.backend.BoardMover;
import chess.json.Board;
import chess.json.Board.Move;
import chess.json.Board.Square;
import chess.json.Board.Square.Piece;

public class BoardMoverWithoutCheckWithoutPawnPromotion implements BoardMover {
	
	@Autowired
	private BoardMoveGenerator boardMoveGenerator;
	
	@Override
	public Board applyMoveToBoard(Board board, Move move) {
		Piece pieceToBeMoved = getPieceToBeMoved(board, move);
		List<Square> squares = new ArrayList<>();
		for (Square square : board.getSquares()) {
			Square squareClone = new Square();
			squareClone.setX(square.getX());
			squareClone.setY(square.getY());
			if (move.getX1()==square.getX() && move.getY1()==square.getY()) {
				squareClone.setPiece(null);
			} else if (move.getX2()==square.getX() && move.getY2()==square.getY()) {
				squareClone.setPiece(clonePiece(pieceToBeMoved));
				if (squareClone.getPiece()!=null) {
					squareClone.getPiece().setHasMoved(true);
				}
			} else {
				squareClone.setPiece(clonePiece(square.getPiece()));
			}
			squares.add(squareClone);
		}
		Board newBoard = new Board();
		newBoard.setActivePlayer(toggleActivePlayer(board.getActivePlayer()));
		newBoard.setSquares(squares);
		List<Move> potentialMoves = boardMoveGenerator.generatePossibleMoves(newBoard.getActivePlayer(), newBoard.getSquares());
		newBoard.setMoves(potentialMoves);
		List<String> promotionPieces = new ArrayList<>();
		promotionPieces.addAll(board.getPromotionPieces());
		newBoard.setPromotionPieces(promotionPieces);
		return newBoard;
	}
	
	private String toggleActivePlayer(String activePlayer) {
		if ("Black".equals(activePlayer)) {
			return "White";
		} else {
			return "Black";
		}
	}

	private Piece getPieceToBeMoved(Board board, Move move) {
		for (Square square : board.getSquares()) {
			if (move.getX1()==square.getX() && move.getY1()==square.getY()) {
				return square.getPiece();
			}
		}
		return null;
	}
	
	private Piece clonePiece(Piece piece) {
		if (piece==null) {
			return null;
		}
		Piece newPiece = new Piece();
		newPiece.setOwner(piece.getOwner());
		newPiece.setType(piece.getType());
		newPiece.setHasMoved(piece.isHasMoved());
		newPiece.setRoyal(piece.isRoyal());
		return newPiece;
	}
	
}
