package chess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chess.ai.ChessAi;
import chess.backend.BoardMover;
import chess.json.Board;
import chess.json.Board.Move;
import chess.json.DefaultBoardGenerator;

/**
 * 
 * @author Brian King
 *
 */
@RestController
public class BoardController {

	public static Board board = (new DefaultBoardGenerator()).generateDefaultBoard();
	
	@Autowired
	private BoardMover boardMover;
	
	@Autowired
	@Qualifier("miniMaxChessAi")
	//@Qualifier("chessAiThatPicksARandomMove")
	private ChessAi chessAi;
	
	@RequestMapping("/board.json")
	public Board getGameState(
			@RequestParam(value="x1", required=false) String x1, 
			@RequestParam(value="y1", required=false) String y1, 
			@RequestParam(value="x2", required=false) String x2, 
			@RequestParam(value="y2", required=false) String y2) {
		//Create move that the player can make
		Move playerMove = generateMove(x1, y1, x2, y2);
		//If the player specifies a Move, we apply it to the board
		if (playerMove!=null) {
			//Create a new Board using the BoardMover
			board = boardMover.applyMoveToBoard(board, playerMove);
			//Let the AI make a move
			Move computerMove = chessAi.decideMove(board);
			if (computerMove!=null) {
				//Apply the computer's move
				board = boardMover.applyMoveToBoard(board, computerMove);
			}
		//If the player does not make a move, then we assume they want to
		//play a new game
		} else {
			 board = (new DefaultBoardGenerator()).generateDefaultBoard();
		}
		//return the resulting board
		return board;
	}
	
	private Move generateMove(String x1, String y1, String x2, String y2) {
		//If the player does not specify URL parameters, we return null
		if (x1==null || y1==null || x2==null || y2==null) {
			return null;
		}
		//Else try to create move
		try {
			Move move = new Move();
			move.setX1(Integer.parseInt(x1));
			move.setY1(Integer.parseInt(y1));
			move.setX2(Integer.parseInt(x2));
			move.setY2(Integer.parseInt(y2));
			return move;
		} catch (Exception e) {
			//The supplied javascript will not give a non-integer, but if someone is URL
			//tampering (such a x1=notavalue) then we return null
			return null;
		}
	}
	

}	
