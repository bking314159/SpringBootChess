package chess.backend.impl.concretepiece;

import java.util.ArrayList;
import java.util.List;

import chess.backend.impl.abstractpiece.RiderMoveCalculator;
import chess.backend.physics.Vector;

/**
 * This class provides a method that sreturns a List of all Moves that a Bishop piece can make
 * 
 * @author Brian King
 *
 */
public class BishopMoveCalculator extends RiderMoveCalculator {

	@Override
	/**
	 * @return the list of Vectors that the Bishop can move along any number of times
	 */
	public List<Vector> getVectors() {
		List<Vector> vectors = new ArrayList<>();
		//The Bishop can move any direction diagonally
		vectors.add(new Vector( 1,  1));
		vectors.add(new Vector(-1,  1));
		vectors.add(new Vector( 1, -1));
		vectors.add(new Vector(-1, -1));
		return vectors;
	}

}
