package chess.backend.physics;

/**
 * This class represents a location on the board.  It implements hashCode() and equals() so that
 * it can be put into a Map
 * 
 * @author Brian King
 *
 */
public class SquareLocation {

	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	@Override
	public boolean equals(Object object) {
		//Two Squares are equal if and only if they are both squares
		//and their x and y values match
		if (object instanceof SquareLocation) {
			SquareLocation square = (SquareLocation) object;
			return this.x == square.x && this.y == square.y; 
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		//This will be injective for x<100 && y<100
		return this.x + 1000*this.y;
	}
}
