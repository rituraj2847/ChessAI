package pieces;

import engine.Alliance;
import java.util.*;
import board.Board;
import board.Move;

public abstract class Piece {

	protected final PieceType pieceType;
	protected final int piecePosition;
	protected final Alliance pieceAlliance;

	Piece(final PieceType pieceType, final int piecePosition,
		  final Alliance pieceAlliance) {
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

	public enum PieceType {
		KING("K"){
			public boolean isKing(){
				return true;
			}
			public boolean isRook(){
				return false;
			}
		},
		QUEEN("Q"){
			public boolean isKing(){
				return false;
			}
			public boolean isRook(){
				return false;
			}

		},
		ROOK("R"){
			public boolean isKing() {
				return false;
			}
			public boolean isRook(){
				return true;
			}

		},
		BISHOP("B"){
			public boolean isKing(){
				return false;
			}
			public boolean isRook(){
				return false;
			}

		},
		KNIGHT("N"){
			public boolean isKing(){
				return false;
			}
			public boolean isRook(){
				return false;
			}

		},
		PAWN("P"){
			public boolean isKing(){
				return false;
			}
			public boolean isRook(){
				return false;
			}

		};
		private String pieceName;
		PieceType( final String pieceName) {
			this.pieceName = pieceName;

		}
		@Override
		public String toString() {
			return this.pieceName;
		}

		public abstract boolean isKing();

		public abstract boolean isRook();
	}
}
