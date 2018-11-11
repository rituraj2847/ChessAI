package pieces;

import board.Move;
import board.Move.*;
import engine.Alliance;
import board.Board;
import board.BoardUtils;
import java.util.*;

public class Pawn extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATES = { 7, 8, 9, 16 };

	public Pawn(final int piecePosition, final Alliance pieceAlliance) {
		super(PieceType.PAWN, piecePosition, pieceAlliance);
	}
	@Override
	public Collection<Move> calculateLegalMoves(final Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		for (final int candidateCoordinateOffset : CANDIDATE_MOVE_COORDINATES) {
			int candidateDestinationCoordinate = this.piecePosition
					+ this.pieceAlliance.getDirection() * candidateCoordinateOffset;
			if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
				continue;
			}
			if (candidateCoordinateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
				legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
			} else if (candidateCoordinateOffset == 16 && (BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceAlliance.isBlack()
					|| BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceAlliance.isWhite())) {
				final int behindCandidateDestinationPosition = this.piecePosition
						+ this.pieceAlliance.getDirection() * 8;
				if (!board.getTile(behindCandidateDestinationPosition).isTileOccupied()
						&& !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
				}
			} else if (candidateCoordinateOffset == 7
					&& !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite())
							|| (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))) {
				if (board.getTile(candidateDestinationCoordinate).isTileOccupied()){
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
						legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
					}
				}
			} else if (candidateCoordinateOffset == 9
					&& !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())
							|| (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()))) {
				if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
						legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
					}
				}
			}
		}
		return Collections.unmodifiableList(legalMoves);
	}
	@Override
	public Pawn movePiece(Move move) {
		return new Pawn(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
	}
	@Override
	public String toString() {
		return PieceType.PAWN.toString();
	}
}