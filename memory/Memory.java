package memory;

public class Memory {
	private int width;
	private int height;
	private int rows;
	private int cols;

	private int numMovements;
	private int numTopValues;
	private double eta;
	
	private Graph[][] graphs;

	/*
	 * Memory constructor
	 * Takes in width and height of movement area,
	 * 	numbers of rows and columns of cells to keep track of movement,
	 * 	the number of types of movement to
	 */
	public Memory(int width, int height, int rows, int cols, 
			int numMovements, int numTopValues, double eta) {
		assert(width > 0 && height > 0 && rows > 0 && cols > 0);
		assert(cols < width && rows < height);
		assert(numMovements > 0 && numTopValues > 0);
		assert(numTopValues < numMovements);

		this.width = width;
		this.height = height;
		this.rows = rows;
		this.cols = cols;

		this.numMovements = numMovements;
		this.numTopValues = numTopValues;
		this.eta = eta;

		graphs = new Graph[cols][rows];
		for (int y = 0; y < rows; y++)
			for (int x = 0; x < cols; x++)
				graphs[x][y] = new Graph(numMovements, numTopValues, eta);
	}

	/*
	 * Inserts the motion into the graph for the given location
	 */
	public void insert(int x, int y, int movement) {
		assert(x >= 0 && x <= width && y >= 0 && y <= height);
		assert(movement >= 0 && movement < numMovements);

		int graphCol = x / (width / cols);
		int graphRow = y / (height / rows);

		graphs[graphCol][graphRow].insert(movement);
	}

	/*
	 * Gets one of the top most probable movements for the given location
	 * 	Note: this output is non-deterministic
	 */
	public int get(int x, int y) {
		assert(x >= 0 && x <= width && y >= 0 && y <= height);

		int graphCol = x / (width / cols);
		int graphRow = y / (height / rows);

		return graphs[graphCol][graphRow].get();
	}

	@Override
	public String toString() {
		String stringRep = "{ \n";
		stringRep += "\t \"width\": " + width + ", \n";
		stringRep += "\t \"height\": " + height + ", \n";
		stringRep += "\t \"rows\": " + rows + ", \n";
		stringRep += "\t \"cols\": " + cols + ", \n";
		stringRep += "\t \"numMovements\": " + numMovements + ", \n";
		stringRep += "\t \"numTopValues\": " + numTopValues + ", \n";
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
