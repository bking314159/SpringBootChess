package chess.backend.impl.concretepiece;

import java.util.ArrayList;
import java.util.List;

import chess.backend.impl.abstractpiece.RiderMoveCalculator;
import chess.backend.physics.Vector;

/**
 * This class provides a method that returns a List of all Moves that a Rook piece can make
 * 
 * @author Brian King
 *
 */
public class RookMoveCalculator extends RiderMoveCalculator {

	@Override
	/**
	 * @return the list of Vectors that the Rook can move along any number of times
	 */
	public List<Vector> getVectors() {
		List<Vector> vectors = new ArrayList<>();
		//The Bishop can move any direction vertically or horizontally
		vectors.add(new Vector( 1,  0));
		vectors.add(new Vector(-1,  0));
		vectors.add(new Vector( 0,  1));
		vectors.add(new Vector( 0, -1));
		return vectors;
	}

}
