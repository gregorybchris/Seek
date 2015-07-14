package constants;

/* Setup Constants */
public class SC {
	/* Rows and columns to divide the screen into processing cells */
	public static final int ROWS = 10;
	public static final int COLS = 10;
	
	/* The number of directions of travel possible */
	/* The halted movement (MOVE_H) IS included in this */
	public static final int NUM_MOVEMENTS = 5;
	
	/* The number of most probable movements to track */
	public static final int NUM_TOP_VALUES = 3;
	
	/* Determines how aggressively the algorithm learns, from 0.0 to 1.0 */
	public static final double ETA = 0.02;
	
	/* The numbers of each type of bot that get created on map creation */
	public static final int[] BOTS_COUNTS = new int[]{3, 20, 80};//{10, 5, 3};//{4, 2, 1};
}
