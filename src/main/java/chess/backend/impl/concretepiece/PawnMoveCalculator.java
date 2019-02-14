package chess.backend.impl.concretepiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.backend.MoveCalculator;
import chess.backend.physics.SquareLocation;
import chess.backend.physics.SquareLocationNavigator;
import chess.backend.physics.SquareStatus;
import chess.backend.physics.Vector;
import chess.json.Board.Move;

public class PawnMoveCalculator implements MoveCalculator {

	private List<Vector> moveVectors;
	private List<Vector> captureVectors;
	
	private SquareLocationNavigator squareLocationNavigator = new SquareLocationNavigator();
	
	public PawnMoveCalculator(String owner, boolean hasMoved) {
		moveVectors = new ArrayList<>();
		captureVectors = new ArrayList<>();
		if (owner.contentEquals("White")) {
			moveVectors.add(new Vector(1, 0));
			if (!hasMoved) {
				moveVectors.add(new Vector(2, 0));
			}
			captureVectors.add(new Vector(1, 1));
			captureVectors.add(new Vector(1, -1));
		} else {
			moveVectors.add(new Vector(-1, 0));
			if (!hasMoved) {
				moveVectors.add(new Vector(-2, 0));
			}
			captureVectors.add(new Vector(-1, 1));
			captureVectors.add(new Vector(-1, -1));
		}
	}

	@Override
	public List<Move> getPossibleMoves(SquareLocation pieceLocation, Map<SquareLocation, SquareStatus> locationStatusMap) {
		List<Move> possibleMoves = new ArrayList<>();
		for (Vector moveVector : moveVectors) {
			SquareLocation moveLocation = squareLocationNavigator.applyVectorToSquareLocation(pieceLocation, moveVector); 
			SquareStatus status = locationStatusMap.get(moveLocation);
			if (status==SquareStatus.EMPTY) {
				Move move = new Move(pieceLocation.getX(), pieceLocation.getY(), moveLocation.getX(), moveLocation.getY());
				possibleMoves.add(move);
			} else {
				break;
			}
		}
		for (Vector captureVector : captureVectors) {
			SquareLocation captureLocation = squareLocationNavigator.applyVectorToSquareLocation(pieceLocation, captureVector); 
			SquareStatus status = locationStatusMap.get(captureLocation);
			if (status==SquareStatus.ENEMY) {
				Move move = new Move(pieceLocation.getX(), pieceLocation.getY(), captureLocation.getX(), captureLocation.getY());
				possibleMoves.add(move);
			}
		}
		return possibleMoves;
	}
	
	public boolean pawnCanBePromoted(SquareLocation pieceLocation, Map<SquareLocation, SquareStatus> locationStatusMap) {
		Vector moveVector = moveVectors.get(0);
		SquareLocation moveLocation = squareLocationNavigator.applyVectorToSquareLocation(pieceLocation, moveVector);
		return !locationStatusMap.containsKey(moveLocation);
	}

}
