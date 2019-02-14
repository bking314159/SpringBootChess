package chess.backend.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chess.backend.SquareLocationFactory;
import chess.backend.SquareStatusMapBuilder;
import chess.backend.physics.SquareLocation;
import chess.backend.physics.SquareStatus;
import chess.json.Board.Square;

/**
 * This class implements SquareStatusMapBuilder and provides a method for constructing a map that 
 * represents the state of each Square on the board
 * 
 * @author Brian King
 *
 */
@Component("squareStatusMapBuilderImpl")
public class SquareStatusMapBuilderImpl implements SquareStatusMapBuilder {

	//This is used to build a SquareLocation for each Square
	@Autowired
	private SquareLocationFactory squareLocationFactory;
	
	@Override
	/**
	 * Given an active player and a List of Square objects, this method returns a map from SquareLocations
	 * (that correspond to the squares) to SquareStatus (such a FRIENDLY, ENEMY, EMPTY)
	 * 
	 * @param activePlayer the player that will be FRIENDLY
	 * @param squares all the Squares on the board
	 * 
	 * @return a map that will give the status of each SquareLocation
	 */
	public Map<SquareLocation, SquareStatus> buildSquareStatusMap(String activePlayer, List<Square> squares) {
		//Create a Map for the answer
		Map<SquareLocation, SquareStatus> squareLocationMap = new HashMap<>();
		//Iterate though each square
		for (Square square : squares) {
			//Create a SquareLocation for the square
			SquareLocation squareLocation = squareLocationFactory.buildSquareLocation(square);
			//If the square is empty, put EMPTY in the map
			if (square.getPiece()==null) {
				squareLocationMap.put(squareLocation, SquareStatus.EMPTY);
			//If the square has a piece that is owned by the activePlayer, put FRIENDLY
			} else if (square.getPiece().getOwner().equals(activePlayer)) {
				squareLocationMap.put(squareLocation, SquareStatus.FRIENDLY);
				//If the square has a piece that is not owned by the activePlayer, put ENEMY
			} else {
				squareLocationMap.put(squareLocation, SquareStatus.ENEMY);
			}
		}
		//return the answer
		return squareLocationMap;
	}

}
