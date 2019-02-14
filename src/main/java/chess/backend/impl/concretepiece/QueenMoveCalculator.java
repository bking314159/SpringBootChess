package chess.backend.impl.concretepiece;

import java.util.ArrayList;
import java.util.List;

import chess.backend.impl.abstractpiece.RiderMoveCalculator;
import chess.backend.physics.Vector;

/**
 * This class provides a method that returns a List of all Moves that a Queen piece can make
 * 
 * @author Brian King
 *
 */
public class QueenMoveCalculator extends RiderMoveCalculator {

	@Override
	/**
	 * @return the list of Vectors that the Queen can move along any number of times
	 */
	public List<Vector> getVectors() {
		List<Vector> vectors = new ArrayList<>();
		//The Queen can move vertically or horizontally one space...
		vectors.add(new Vector( 1,  0));
		vectors.add(new Vector(-1,  0));
		vectors.add(new Vector( 0,  1));
		vectors.add(new Vector( 0, -1));
		//... or diagonally one space
		vectors.add(new Vector( 1,  1));
		vectors.add(new Vector(-1,  1));
		vectors.add(new Vector( 1, -1));
		vectors.add(new Vector(-1, -1));
		return vectors;
	}

}
