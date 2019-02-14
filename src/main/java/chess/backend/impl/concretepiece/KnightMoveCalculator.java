package chess.backend.impl.concretepiece;

import java.util.ArrayList;
import java.util.List;

import chess.backend.impl.abstractpiece.LeaperMoveCalculator;
import chess.backend.physics.Vector;

/**
 * This class provides a method that returns a List of all Moves that a Knight piece can make
 * 
 * @author Brian King
 *
 */
public class KnightMoveCalculator extends LeaperMoveCalculator {

	@Override
	/**
	 * @return the list of Vectors that the Knight can move alone one time
	 */
	public List<Vector> getVectors() {
		List<Vector> vectors = new ArrayList<>();
		//The knight can move every direction with a difference of (2,1)
		vectors.add(new Vector( 1,  2));
		vectors.add(new Vector(-1,  2));
		vectors.add(new Vector( 1, -2));
		vectors.add(new Vector(-1, -2));
		vectors.add(new Vector( 2,  1));
		vectors.add(new Vector(-2,  1));
		vectors.add(new Vector( 2, -1));
		vectors.add(new Vector(-2, -1));
		return vectors;
	}

}
