package board;

public class BoardUtils {
	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLUMN = initColumn(6);
	public static final boolean[] EIGHTH_COLUMN = initColumn(7);

	public static final boolean[] FIRST_ROW = initRow(0);
	public static final boolean[] SECOND_ROW = initRow(8);
	public static final boolean[] THIRD_ROW = initRow(16);
	public static final boolean[] FOURTH_ROW = initRow(24);
	public static final boolean[] FIFTH_ROW = initRow( 32);
	public static final boolean[] SIXTH_ROW = initRow(40);
	public static final boolean[] SEVENTH_ROW = initRow(48);
	public static final boolean[] EIGHTH_ROW = initRow(56);

	public static final int NUM_TILES = 64;
	public static final int NUM_TILES_PER_ROW = 8;

	private static boolean[] initColumn(int colNumber) {
		final boolean[] column = new boolean[64];
		while (colNumber < NUM_TILES) {
			column[colNumber] = true;
			colNumber += NUM_TILES_PER_ROW;
		}
		return column;
	}
	
	private static boolean[] initRow(int rowNumber) {
		final boolean[] row = new boolean[64];
		int i = 0;
		while (i < 8) {
			row[rowNumber] = true;
			rowNumber += 1;
			i++;
		}
		return row;
	}

	private BoardUtils() {
		throw new RuntimeException("cannot be instantiated");
	}

	public static boolean isValidTileCoordinate(int coordinate) {
		return coordinate >= 0 && coordinate < NUM_TILES;
	}
}
