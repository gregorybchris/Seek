package memory;

public class Graph {
	private int numMovements;
	private int numTopValues;
	
	private double[] data;
	
	public Graph(int numMovements, int numTopValues) {
		assert(numMovements > 0 && numTopValues > 0);
		
		this.numMovements = numMovements;
		this.numTopValues = numTopValues;
		
		data = new double[numMovements];
	}
	
	public void insert(int acv) {
		assert(acv >= 0 && acv < numMovements);
		
		/* Make changes if the action has less than 100% probability */
		if (data[acv] != 1.0) {
			
		}
	}
	
	public int get() {
		return 0;
	}
	
	@Override
	public String toString() {
		String stringRep = "Graph { \n";
		stringRep += "\t numMovements:" + numMovements + ", \n";
		stringRep += "\t numTopValues:" + numTopValues + " \n";
		stringRep += "} \n";
		return stringRep;
	}
}
