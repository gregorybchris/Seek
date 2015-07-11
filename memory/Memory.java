package memory;

import java.io.Serializable;

public class Memory implements Serializable {
	private static final long serialVersionUID = 3200232337624988754L;
	
	private int width;
	private int height;
	private int rows;
	private int cols;

	private int numMoves;
	private int numTopMoves;
	private double eta;
	
	private Cell[][] Cells;

	/*
	 * Memory constructor
	 * Takes in width and height of movement area,
	 * 	numbers of rows and columns of cells to keep track of movement,
	 * 	the number of types of movement to
	 */
	public Memory(int width, int height, int rows, int cols, 
			int numMoves, int numTopMoves, double eta) {
		assert(width > 0 && height > 0 && rows > 0 && cols > 0);
		assert(cols < width && rows < height);
		assert(numMoves > 0 && numTopMoves > 0);
		assert(numTopMoves < numMoves);

		this.width = width;
		this.height = height;
		this.rows = rows;
		this.cols = cols;

		this.numMoves = numMoves;
		this.numTopMoves = numTopMoves;
		this.eta = eta;

		Cells = new Cell[cols][rows];
		for (int y = 0; y < rows; y++)
			for (int x = 0; x < cols; x++)
				Cells[x][y] = new Cell(numMoves, numTopMoves, eta);
	}

	/*
	 * Inserts the motion into the Cell for the given location
	 */
	public void put(int x, int y, int movement) {
		assert(x >= 0 && x < width && y >= 0 && y < height);
		assert(movement >= 0 && movement < numMoves);

		int CellCol = x / (width / cols);
		int CellRow = y / (height / rows);

		Cells[CellCol][CellRow].put(movement);
	}

	/*
	 * Gets one of the top most probable movements for the given location
	 * 	Note: this output is non-deterministic
	 */
	public int get(int x, int y) {
		assert(x >= 0 && x < width && y >= 0 && y < height);

		int CellCol = x / (width / cols);
		int CellRow = y / (height / rows);

		return Cells[CellCol][CellRow].get();
	}
	
	@Override
	public String toString() {
		return toJSON();
	}
	
	/*
	 * Converts the Memory to a JSON object
	 */
	public String toJSON() {
		String stringRep = "{ \n";
		stringRep += "\t \"width\": " + width + ", \n";
		stringRep += "\t \"height\": " + height + ", \n";
		stringRep += "\t \"rows\": " + rows + ", \n";
		stringRep += "\t \"cols\": " + cols + ", \n";
		stringRep += "\t \"numMoves\": " + numMoves + ", \n";
		stringRep += "\t \"numTopMoves\": " + numTopMoves + ", \n";
		stringRep += "\t \"eta\": " + eta + ", \n";
		stringRep += "\t \"Cells\": [ \n";
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				stringRep += Cells[x][y].toString();
				if (x != rows - 1 || y != cols - 1)
					stringRep += ", \n";
				else
					stringRep += "\n";
			}
		}
		stringRep += "\t ] \n";
		stringRep += "}";
		return stringRep;
	}
}
