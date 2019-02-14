package chess.backend.impl;

import org.springframework.stereotype.Component;

import chess.backend.MoveCalculator;
import chess.backend.MoveCalculatorFactory;
import chess.backend.impl.concretepiece.BishopMoveCalculator;
import chess.backend.impl.concretepiece.KingMoveCalculator;
import chess.backend.impl.concretepiece.KnightMoveCalculator;
import chess.backend.impl.concretepiece.PawnMoveCalculator;
import chess.backend.impl.concretepiece.QueenMoveCalculator;
import chess.backend.impl.concretepiece.RookMoveCalculator;
import chess.json.Board.Square.Piece;

/**
 * This class implements the MoveCalculatorFactory.  Given a String coresponding
 * to a standard Chess piece (Pawn, Rook, Knight, Bishop, King, or Queen) it provides
 * a method for returning the corresponding MoveCalculator
 * 
 * @author Brian King
 *
 */
@Component("moveCalculatorFactoryImpl")
public class MoveCalculatorFactoryImpl implements MoveCalculatorFactory {

	@Override
	public MoveCalculator buildMoveCalculator(Piece piece) {
		if (piece==null) {
			throw new RuntimeException("A null piece was passed to MoveCalculatorFactoryImpl.buildMoveCalculator(piece)");
		}
		String type = piece.getType();
		if (type.equals("Pawn")) {
			//Pawns need to know the owner and if it is a virgin pawn
			return new PawnMoveCalculator(piece.getOwner(), piece.isHasMoved());
		//Other pieces move without regard to owner or previous move status
		} else if (type.equals("Rook")) {
			return new RookMoveCalculator();
		} else if (type.equals("Knight")) {
			return new KnightMoveCalculator();
		} else if (type.equals("Bishop")) {
			return new BishopMoveCalculator();
		} else if (type.equals("King")) {
			return new KingMoveCalculator();
		} else if (type.equals("Queen")) {
			return new QueenMoveCalculator();
		} else {
			throw new RuntimeException("The following piece type was not found: " + type);
		}
	}

}
