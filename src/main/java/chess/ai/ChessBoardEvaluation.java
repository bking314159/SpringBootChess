package chess.ai;

import chess.json.Board;

/**
 * This interface outlines an evaluation function. Given a Board, it produces an int value that
 * represents the AI's perceived value of the board
 * 
 * @author Brian King
 *
 */
public interface ChessBoardEvaluation {

	/**
	 * Given the state of the game, an int value is returned that represents the percieved value of
	 * the board such that a higher int value indicates a better position for the AI
	 * 
	 * @param board the state of the game
	 * @return the perceived value of the AIs position
	 */
	int evaluateBoard(Board board);
	
}
