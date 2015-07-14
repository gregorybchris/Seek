package memory;

import java.util.Random;

import constants.MC;

public class Cell {
	private Random random = new Random();

	private int numMoves;
	private int numTopMoves;
	private double eta;

	/* Probabilities of each movement occurring */
	private double[] moveProbs;

	/* Indexes for each movement in the topMoves list */
	private int[] moveTopIndexes;

	/* A sorted list of the most probable movements */
	private int[] topMoves;

	/* The index of the least probable movement in the topMoves list */
	private int lastTopIndex;

	/*
	 * Cell constructor
	 * Takes in the number of types of movement to track, the number
	 * 	of most probable movements to track, and how aggressively
	 * 	the algorithm should learn
	 */
	public Cell(int numMoves, int numtopMoves, double eta) {
		assert(numMoves > 0 && numtopMoves > 0);
		assert(numtopMoves < numMoves);
		assert(eta > 0.0 && eta < 1.0);

		this.numMoves = numMoves;
		this.numTopMoves = numtopMoves;
		this.eta = eta;

		moveProbs = new double[numMoves];
		moveProbs[0] = 1.0;

		moveTopIndexes = new int[numMoves];
		for (int i = 0; i < numMoves; i++)
			moveTopIndexes[i] = -1;
		moveTopIndexes[0] = 0;

		topMoves = new int[numtopMoves];
		for (int i = 0; i < numtopMoves; i++)
			topMoves[i] = -1;
		topMoves[0] = 0;
		lastTopIndex = 0;
	}

	/*
	 * Inserts a new movement into the Cell by increasing the probability
	 * 	of that movement and decreasing the probability of other movements
	 * 	Movements will always have a probability between 0 and 1.0 inclusive
	 * 	The total probability will be between 0 and the number of movements
	 */
	public void put(int mv) {
		assert(mv >= 0 && mv < numMoves);

		if (mv == MC.MOVE_H)
			System.out.println(this);

		/* Update the probability of the current movement */
		moveProbs[mv] += eta;
		if (moveProbs[mv] > 1.0)
			moveProbs[mv] = 1.0;

		/* Update the probability of all other movements */
		double offset = eta / numMoves;
		for (int i = 0; i < numMoves; i++) {
			if (moveProbs[i] != 0.0 && i != mv) {
				moveProbs[i] -= offset;
				if (moveProbs[i] < 0.0)
					moveProbs[i] = 0.0;
			}
		}

		addToTops(mv);
	}

	/*
	 * Updates the values for all of the fields related to keeping
	 * 	track of the highest probability movements
	 */
	private void addToTops(int mv) {
		int bubbleStartIndex = -1;

		/* Make sure that the movement is somewhere in */
		/*  topMoves if it should be and set the location to start sorting */
		/* If the new movement is already in topMoves */
		if (moveTopIndexes[mv] != -1)
			bubbleStartIndex = moveTopIndexes[mv];
		/* If the new movement is not in topMoves */
		else {
			/* If topMoves not full yet */ 
			if (lastTopIndex < numTopMoves - 1) {
				topMoves[lastTopIndex + 1] = mv;
				lastTopIndex++;
				moveTopIndexes[mv] = lastTopIndex;
				bubbleStartIndex = lastTopIndex;
			}
			/* If topMoves is full already */
			else {
				/* If P(new movement) has exceeded the smallest value */
				/*  in topMoves */
				if (moveProbs[mv] > moveProbs[topMoves[lastTopIndex]]) {
					moveTopIndexes[topMoves[lastTopIndex]] = -1;
					topMoves[lastTopIndex] = mv;
					moveTopIndexes[mv] = lastTopIndex;
					bubbleStartIndex = lastTopIndex;
				}
			}
		}

		/* If the new movement is in topMoves and ready to be sorted */
		if (moveTopIndexes[mv] != -1) {
			/* Bubble the new movement up through topMoves into place */
			for (int i = bubbleStartIndex; i > 0; i--) {
				if (moveProbs[mv] > moveProbs[topMoves[i - 1]]) {
					topMoves[i] = topMoves[i - 1];
					moveTopIndexes[topMoves[i]] = i;
					topMoves[i - 1] = mv;
					moveTopIndexes[topMoves[i - 1]] = i - 1;
				}
				else
					break;
			}
		}
	}

	/*
	 * Picks a movement probabilistically through a comparison between
	 * 	a randomly generated value and a weighted distribution of the 
	 * 	past movement probabilities (this output is non-deterministic)
	 */
	public int get() {
		int precision = 100;
		int randInt = random.nextInt(precision);
		double randDouble = randInt / (double)precision;

		double probabilityAccumulator = 0;
		for (int i = 0; i < numMoves; i++) {
			probabilityAccumulator += moveProbs[i] / numMoves;
			if (randDouble < probabilityAccumulator)
				return i;
		}
		return 0;
	}

	private double round(double x) {
		int precision = 1000;
		return ((int)(x * precision)) / (double)precision;
	}

	@Override
	public String toString() {
		return toJSON();
	}

	/*
	 * Converts the Cell to a stringified JSON object
	 */
	public String toJSON() {
		String stringRep = "\t\t { \n";
		stringRep += "\t\t\t \"numMoves\": " + numMoves + ", \n";
		stringRep += "\t\t\t \"numTopMoves\": " + numTopMoves + ", \n";
		stringRep += "\t\t\t \"eta\": " + eta + ", \n";

		stringRep += "\t\t\t \"moveProbs\": [";
		for (int i = 0; i < numMoves - 1; i++)
			stringRep += round(moveProbs[i]) + ", ";
		stringRep += round(moveProbs[numMoves - 1]) + "], \n";

		stringRep += "\t\t\t \"moveTopIndexes\": [";
		for (int i = 0; i < numMoves - 1; i++)
			stringRep += moveTopIndexes[i] + ", ";
		stringRep += moveTopIndexes[numMoves - 1] + "], \n";

		stringRep += "\t\t\t \"topMoves\": [";
		for (int i = 0; i < numTopMoves - 1; i++)
			stringRep += topMoves[i] + ", ";
		stringRep += topMoves[numTopMoves - 1] + "], \n";

		stringRep += "\t\t\t \"lastTopIndex\": " + lastTopIndex + " \n";

		stringRep += "\t\t }";
		return stringRep;
	}
}
