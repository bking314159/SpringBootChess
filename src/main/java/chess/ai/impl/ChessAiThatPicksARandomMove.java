package chess.ai.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import chess.ai.ChessAi;
import chess.json.Board;
import chess.json.Board.Move;

/**
 * This is the simplest chess agent possible. Given a board, it randomly picks a move
 * 
 * @author Brian
 *
 */
@Component("chessAiThatPicksARandomMove")
public class ChessAiThatPicksARandomMove implements ChessAi {

	@Override
	/**
	 * Given the state of the game, the AI will produce a random move
	 * 
	 * @param board the state of the board
	 * @return a Move that the AI wishes to make
	 */
	public Move decideMove(Board board) {
		List<Move> possibleMoves = board.getMoves();
		//If there are no possible moves (such as checkmate or stalemate)
		//the AI returns null
		if (possibleMoves.size()==0) {
			return null;
		}
		//Pick a random index and return the move at that index
		int randomIndex = (int)(Math.random()*possibleMoves.size());
		return possibleMoves.get(randomIndex);
	}

}
