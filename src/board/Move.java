package board;
import pieces.Piece;
import board.Board.*;

public abstract class Move {

	protected final Piece movedPiece;
	protected final Board board;
	protected final int destinationCoordinate;
	public static final Move NULL_MOVE = new NullMove();
	private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
	}
	private Move(final Board board,
				 final int destinationCoordinate) {
		this.board = board;
		this.destinationCoordinate = destinationCoordinate;
		this.movedPiece = null;
	}
	public int getCurrentCoordinate(){
		return this.getMovedPiece().getPiecePosition();
	}
	public int getDestinationCoordinate(){
		return this.destinationCoordinate;
	}
	public Piece getMovedPiece(){
		return this.movedPiece;
	}
	public boolean isAttack() {
		return false;
	}
	public Piece getAttackedPiece() {
		return null;
	}

	public Board execute() {
        final Builder builder = new Builder();
        for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
            if(!this.movedPiece.equals(piece)){
                builder.setPiece(piece);
            }
        }
        for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
        return builder.build();
    }
	public static final class MajorMove extends Move {
		public MajorMove(final Board board,
						 final Piece movedPiece,
						 final int destinationCoordinate) {
			super(board, movedPiece, destinationCoordinate);
		}
	}
	public static class AttackMove extends Move {
		final Piece attackedPiece;
		public AttackMove(final Board board, final Piece movedPiece,
				final int destinationCoordinate, final Piece attackedPiece){
			super(board, movedPiece, destinationCoordinate);
			this.attackedPiece = attackedPiece;
		}
		@Override
		public boolean isAttack(){
			return true;
		}
		@Override
		public Piece getAttackedPiece(){
			return this.attackedPiece;
		}
	}
	public static final class NullMove extends Move {
		public NullMove() {
			super(null, -1);
		}
		@Override
		public Board execute(){
			throw new RuntimeException("Cannot execute board!");
		}
	}
	public static Move createMove(final Board board,
								  final int currentCoordinate,
								  final int destinationCoordinate) {
		for (final Move move : board.getAllLegalMoves()) {
			if (move.getCurrentCoordinate() == currentCoordinate &&
					move.getDestinationCoordinate() == destinationCoordinate) {
				return move;
			}
		}
		return NULL_MOVE;
	}
}
