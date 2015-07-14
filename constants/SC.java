package constants;

/* Setup Constants */
public class SC {
	/* Rows and columns to divide the screen into processing cells */
	public static final int ROWS = 20;
	public static final int COLS = 20;
	
	/* The number of directions of travel possible */
	/* The halted movement (MOVE_H) IS included in this */
	public static final int NUM_MOVEMENTS = 5;
	
	/* The number of most probable movements to track */
	public static final int NUM_TOP_VALUES = 5;
	
	/* Determines how aggressively the algorithm learns */
	public static final double ETA = 0.20;
	
	/* The numbers of each type of bot that get created on map creation */
	public static final int[] BOTS_COUNTS = new int[]{1, 0, 1};
}
