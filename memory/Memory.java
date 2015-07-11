package memory;

public class Memory {
	private int width;
	private int height;
	private int rows;
	private int cols;

	private int numMoves;
	private int numTopMoves;
	private double eta;
	
	private Graph[][] graphs;

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

		graphs = new Graph[cols][rows];
		for (int y = 0; y < rows; y++)
			for (int x = 0; x < cols; x++)
				graphs[x][y] = new Graph(numMoves, numTopMoves, eta);
	}

	/*
	 * Inserts the motion into the graph for the given location
	 */
	public void put(int x, int y, int movement) {
		assert(x >= 0 && x < width && y >= 0 && y < height);
		assert(movement >= 0 && movement < numMoves);

		int graphCol = x / (width / cols);
		int graphRow = y / (height / rows);

		graphs[graphCol][graphRow].put(movement);
	}

	/*
	 * Gets one of the top most probable movements for the given location
	 * 	Note: this output is non-deterministic
	 */
	public int get(int x, int y) {
		assert(x >= 0 && x < width && y >= 0 && y < height);

		int graphCol = x / (width / cols);
		int graphRow = y / (height / rows);

		return graphs[graphCol][graphRow].get();
	}
	
	@Override
	public String toString() {
		return toJSON();
	}
	
	public String toJSON() {
		String stringRep = "{ \n";
		stringRep += "\t \"width\": " + width + ", \n";
		stringRep += "\t \"height\": " + height + ", \n";
		stringRep += "\t \"rows\": " + rows + ", \n";
		stringRep += "\t \"cols\": " + cols + ", \n";
		stringRep += "\t \"numMoves\": " + numMoves + ", \n";
		stringRep += "\t \"numTopMoves\": " + numTopMoves + ", \n";
		stringRep += "\t \"eta\": " + eta + ", \n";
		stringRep += "\t \"graphs\": [ \n";
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				stringRep += graphs[x][y].toString();
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
