package memory;

public class Memory {
	private Graph[][] graphs;
	
	private int width;
	private int height;
	private int rows;
	private int cols;
	
	private int numMovements;
	private int numTopValues;
	
	/*
	 * Memory constructor
	 * Takes in width and height of movement area,
	 * 	numbers of rows and columns of cells to keep track of movement,
	 * 	the number of types of movement to
	 */
	public Memory(int width, int height, int rows, int cols, 
			int numMovements, int numTopValues) {
		assert(width > 0 && height > 0 && rows > 0 && cols > 0);
		assert(cols < width && rows < height);
		assert(numMovements > 0 && numTopValues > 0);
		
		this.width = width;
		this.height = height;
		this.rows = rows;
		this.cols = cols;
		
		this.numMovements = numMovements;
		this.numTopValues = numTopValues;
		
		graphs = new Graph[cols][rows];
		for (int y = 0; y < rows; y++)
			for (int x = 0; x < cols; x++)
				graphs[x][y] = new Graph(numMovements, numTopValues);
	}
	
	/*
	 * Inserts the motion into the graph for the given location
	 */
	public void insert(int x, int y, int acv) {
		int graphCol = x / (width / cols);
		int graphRow = y / (height / rows);
		
		graphs[graphCol][graphRow].insert(acv);
	}
	
	/*
	 * Gets one of the top most probable movements for the given location
	 */
	public int get(int x, int y) {
		int graphCol = x / (width / cols);
		int graphRow = y / (height / rows);
		
		return graphs[graphCol][graphRow].get();
	}
	
	@Override
	public String toString() {
		String stringRep = "Memory { \n";
		stringRep += "\t width:" + width + "px, \n";
		stringRep += "\t height:" + height + "px, \n";
		stringRep += "\t rows:" + rows + ", \n";
		stringRep += "\t cols:" + cols + ", \n";
		stringRep += "\t numMovements:" + numMovements + ", \n";
		stringRep += "\t numTopValues:" + numTopValues + "\n";
		stringRep += "} \n";
		return stringRep;
	}
}
