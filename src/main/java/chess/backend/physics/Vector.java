package chess.backend.physics;

/**
 * The vector class is used for piece navigation. It has an (x,y) pair that represents
 * the rise and run of a moving piece
 * 
 * @author Brian King
 *
 */
public class Vector {

	private int x;
	private int y;
	
	public Vector() {
		
	}
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
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
	
}
