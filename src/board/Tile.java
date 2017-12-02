package board;

import pieces.Piece;

public abstract class Tile {
	protected final int tileCoordinate;

	public static Tile createTile(final int tileCoordinate, final Piece piece){
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : new EmptyTile(tileCoordinate);
	}
	private Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	public abstract boolean isTileOccupied();
	public abstract Piece getPiece();
	public int getTileCoordinate(){
		return this.tileCoordinate;
	}

	public static final class EmptyTile extends Tile {
		EmptyTile(int coordinate) {
			super(coordinate);
		}
		@Override
		public String toString() {
			return "-";
		}
		@Override
		public boolean isTileOccupied() {
			return false;
		}
		@Override
		public Piece getPiece() {
			return null;
		}
	}

	public static final class OccupiedTile extends Tile {
		Piece pieceOnTile;
		OccupiedTile(int coordinate, Piece pieceOnTile) {
			super(coordinate);
			this.pieceOnTile = pieceOnTile;
		}
		@Override
		public String toString() {
			return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase()
					: getPiece().toString();
		}
		@Override
		public boolean isTileOccupied() {
			return true;
		}
		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
	}
}