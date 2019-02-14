package chess.json;

import java.util.List;

/**
 * This class specifies a JSON object that represents the state of the board
 * 
 * @author Brian King
 *
 */
public class Board {

	private String activePlayer;
	private String status;
	private List<Square> squares;
	private List<Move> moves;
	private List<String> promotionPieces;
	
	public String getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(String activePlayer) {
		this.activePlayer = activePlayer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Square> getSquares() {
		return squares;
	}
	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}
	public List<Move> getMoves() {
		return moves;
	}
	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	public List<String> getPromotionPieces() {
		return promotionPieces;
	}
	public void setPromotionPieces(List<String> promotionPieces) {
		this.promotionPieces = promotionPieces;
	}

	public static class Square {
		private int x;
		private int y;
		private Piece piece;
		
		public Square() {
			
		}
		public Square(int x, int y, Piece piece) {
			this.x = x;
			this.y = y;
			this.piece = piece;
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
		public Piece getPiece() {
			return piece;
		}
		public void setPiece(Piece piece) {
			this.piece = piece;
		}

		public static class Piece {
			private String owner;
			private String type;
			private boolean royal;
			private boolean hasMoved = false;
			
			public Piece() {
				
			}
			
			public Piece(String owner, String type, boolean royal) {
				this.owner = owner;
				this.type = type;
				this.royal = royal;
			}
			
			public String getOwner() {
				return owner;
			}
			public void setOwner(String owner) {
				this.owner = owner;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public boolean isRoyal() {
				return royal;
			}
			public void setRoyal(boolean royal) {
				this.royal = royal;
			}
			public boolean isHasMoved() {
				return hasMoved;
			}
			public void setHasMoved(boolean hasMoved) {
				this.hasMoved = hasMoved;
			}
			
		}
	}
	
	public static class Move {
		private int x1;
		private int y1;
		private int x2;
		private int y2;
		
		public Move() {
			
		}
		
		public Move(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		public int getX1() {
			return x1;
		}
		public void setX1(int x1) {
			this.x1 = x1;
		}
		public int getY1() {
			return y1;
		}
		public void setY1(int y1) {
			this.y1 = y1;
		}
		public int getX2() {
			return x2;
		}
		public void setX2(int x2) {
			this.x2 = x2;
		}
		public int getY2() {
			return y2;
		}
		public void setY2(int y2) {
			this.y2 = y2;
		}
		
		@Override
		public String toString() {
			return "(" + this.x1 + ", " + this.y1 + "-> " + this.x2 + ", " + this.y2 + ")";
		}
		
	}
}
