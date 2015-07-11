package memory;

import java.util.Random;

public class Graph {
	private static final int DEFAULT_STILL = 0;
	
	private Random random = new Random();
	
	private int numMovements;
	private int numTopValues;
	private double eta;

	private double[] data;
	private int[] movPositions;
	private int[] topValues;

	public Graph(int numMovements, int numTopValues, double eta) {
		assert(numMovements > 0 && numTopValues > 0);
		assert(numTopValues < numMovements);
		assert(eta > 0.0 && eta < 1.0);

		this.numMovements = numMovements;
		this.numTopValues = numTopValues;
		this.eta = eta;
		
		data = new double[numMovements];
		data[DEFAULT_STILL] = 1.0;
		movPositions = new int[numMovements];
		for (int i = 0; i < numMovements; i++)
			movPositions[i] = -1;
		movPositions[0] = 0;
		topValues = new int[numTopValues];
	}

	/*
	 * Inserts a new movement into the graph by increasing the probability
	 * 	of that movement and decreasing the probability of other movements
	 * 	Any motion will have a probability between 0 and 1.0 inclusive
	 * 	The total probability should be around, maybe not exactly 1.0
	 */
	public void insert(int mv) {
		assert(mv >= 0 && mv < numMovements);

		/* Make changes if the action has less than 100% probability */
		if (data[mv] != 1.0) {
			/* Update the probability of the current action */
			data[mv] += eta;
			if (data[mv] > 1.0)
				data[mv] = 1.0;
			
			/* Update the probability of all other actions */
			double offset = eta / numMovements;
			double checksum = 0;
			for (int i = 0; i < numMovements; i++) {
				if (data[i] != 0.0 && i != mv) {
					data[i] -= offset;
					if (data[i] < 0.0)
						data[i] = 0.0;
				}
				
				checksum += data[i];
			}
			
			/* If P(new motion) has exceeded values on */
			/* values on the list of top values */
			
			
			/* Bubble value up through top values */
			/*
			for (int i = numTopValues - 1; i >= 0; i--) {
				if (data[mv] > topValues[i]) {
					double newMotion = data[mv];
					topValues[i] = data[i]
				}
				else
					break;
			}
			*/
			
			/* Determine how close the graph is to total probability = 1.0 */
			System.out.println("Test: Graph checksum = " + checksum);
		}
	}

	public int get() {
		
		return 0;
	}

	@Override
	public String toString() {
		String stringRep = "\t\t { \n";
		stringRep += "\t\t\t \"numMovements\": " + numMovements + ", \n";
		stringRep += "\t\t\t \"numTopValues\": " + numTopValues + ", \n";
		stringRep += "\t\t\t \"data\": [";
		for (int i = 0; i < numMovements - 1; i++)
			stringRep += data[i] + ",";
		stringRep += data[numMovements - 1] + "] \n";
		stringRep += "\t\t }";
		return stringRep;
	}
}
