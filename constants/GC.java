package constants;

import java.awt.BasicStroke;
import java.awt.Color;

import data.Dimension;

/* Graphics Constants */
public class GC {
	/* Timing */
	public static final int FRAME_RATE = 20;
	
	/* Sizes */
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 600;
	public static final int WINDOW_BAR_HEIGHT = 22;
	
	/* Colors */
	public static final Color COLOR_BACKGROUND = new Color(90, 90, 90);
	public static final Color[] BOT_COLORS = new Color[] {
		new Color(249, 191, 59),
		new Color(230, 126, 34),
		new Color(200, 70, 0)
	};
	public static final Color PLAYER_COLOR = new Color(75, 120, 200);
	public static final Color ACTOR_BORDER = new Color(70, 70, 70);
	
	public static final Color CELL_BOUNDS = new Color(80, 80, 80);
	
	/* Dimensions */
	public static final Dimension[] BOT_DIMENSIONS = new Dimension[] {
		new Dimension(30, 30),
		new Dimension(25, 25),
		new Dimension(20, 20)
	};
	public static final Dimension PLAYER_DIMENSION = new Dimension(30, 30);
	public static final BasicStroke DEFAULT_STROKE = new BasicStroke(1);
	public static final BasicStroke ACTOR_BORDER_STROKE = new BasicStroke(3);
	
	public static final BasicStroke CELL_BOUNDS_STROKE = new BasicStroke(5);
}
