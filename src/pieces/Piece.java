package pieces;

import engine.Alliance;
import java.util.*;
import board.Board;
import board.Move;

public abstract class Piece {

	protected final PieceType pieceType;
	protected final int piecePosition;
	protected final Alliance pieceAlliance;

	Piece(PieceType pieceType, 
			int piecePosition,
			Alliance pieceAlliance) {
		this.pieceType = pieceType;
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
	}

	public int getPiecePosition() {
		return this.piecePosition;
	}

	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}

	public PieceType getPieceType(){
		return this.pieceType;
	}

	public abstract Collection<Move> calculateLegalMoves(final Board board);

	public abstract Piece movePiece(Move move);

	public static enum PieceType {
		KING("K", 10000){
			@Override
			public boolean isKing(){
				return true;
			}
		},
		QUEEN("Q", 900),
		ROOK("R", 500){
			@Override
			public boolean isRook(){
				return true;
			}
		},
		BISHOP("B", 300),
		KNIGHT("N", 300),
		PAWN("P", 100);
		private String pieceName;
		private int pieceValue;
		PieceType(final String pieceName, final int pieceValue) {
			this.pieceName = pieceName;
			this.pieceValue = pieceValue;
		}
		@Override
		public String toString() {
			return this.pieceName;
		}
		public int getPieceValue() {
			return this.pieceValue;
		}
		public boolean isKing() {return false;}
		public boolean isRook() {return false;}
	}
}
