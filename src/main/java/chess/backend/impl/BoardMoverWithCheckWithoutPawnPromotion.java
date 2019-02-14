package chess.backend.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import chess.backend.CheckDetector;
import chess.json.Board;
import chess.json.Board.Move;

public class BoardMoverWithCheckWithoutPawnPromotion extends BoardMoverWithoutCheckWithoutPawnPromotion {
	
	@Autowired
	private CheckDetector checkDetector;
	
	@Override
	public Board applyMoveToBoard(Board board, Move move) {
		Board newBoard = super.applyMoveToBoard(board, move);
		boolean inCheck = checkDetector.detectIfPlayerIsInCheck(newBoard);
		
		newBoard.setMoves(pruneMovesForCheck(newBoard));
		if (newBoard.getMoves().size()==0) {
			if (inCheck) {
				newBoard.setStatus("Checkmate");
			} else {
				newBoard.setStatus("Stalemate");
			}
		} else if (inCheck) {
			newBoard.setStatus("In check!");
		}
		return newBoard;
	}
	
	private List<Move> pruneMovesForCheck(Board board) {
		List<Move> legalMoves = new ArrayList<>();
		for (Move move : board.getMoves()) {
			Board boardAfterMove = super.applyMoveToBoard(board, move);
			
			boardAfterMove.setActivePlayer(toggleActivePlayer(boardAfterMove.getActivePlayer()));
			boolean inCheck = checkDetector.detectIfPlayerIsInCheck(boardAfterMove);
			if (!inCheck) {
				legalMoves.add(move);
			}
		}
		return legalMoves;
	} 
	
	private String toggleActivePlayer(String activePlayer) {
		if ("Black".equals(activePlayer)) {
			return "White";
		} else {
			return "Black";
		}
	}

}
