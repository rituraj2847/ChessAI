package ai;

import java.util.List;

import board.*;
import pieces.Piece;
import player.*;

public class AI {
	public static Move bestMove;
	private static int evaluateBoard(final Board board) {
		int blackPiecesValue = 0;
		for(final Piece piece : board.getBlackPieces()) {
			blackPiecesValue += piece.getPieceType().getPieceValue();
		}
		
		int whitePiecesValue = 0;
		for(final Piece piece : board.getWhitePieces()) {
			whitePiecesValue += piece.getPieceType().getPieceValue();
		}

		return blackPiecesValue - whitePiecesValue;
	}
	
	public static int alphabeta(Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
		if(depth == 0) {
			return evaluateBoard(board);
		}
		int value;
		if(maximizingPlayer) {
			value = Integer.MIN_VALUE;
			for(final Move move : board.currentPlayer().getLegalMoves()) {
				MoveTransition transition = board.currentPlayer().makeMove(move);
				if (transition.getMoveStatus().isDone()) {
					value = Integer.max(value, alphabeta(transition.getBoard(), depth-1, alpha, beta, false));
					if(alpha < value) {
						if(depth == 4) {
							bestMove = move;
						}
						alpha = value;
					}
					if(beta <= alpha) {
						return value;
					}
				}
			}
		} else {
			value = Integer.MAX_VALUE;
			for(final Move move : board.currentPlayer().getLegalMoves()) {
				MoveTransition transition = board.currentPlayer().makeMove(move);
				if(transition.getMoveStatus().isDone()) {
					value = Integer.min(value, alphabeta(transition.getBoard(), depth-1, alpha, beta, true));
					if(beta > value) {
						beta = value;
					}
					if(beta <= alpha) {
						return value;
					}
				}
			}
		}
		//in case when no move can be made
		if(value == Integer.MIN_VALUE ) value++;
		return value;
	}
}
