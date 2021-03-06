package constants;

import java.awt.BasicStroke;
import java.awt.Color;

import data.Dimension;

/* Graphics Constants */
public class GC {
	/* Timing */
	public static final int FRAME_RATE = 60;
	
	/* Sizes */
	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 700;
	public static final int WINDOW_BAR_HEIGHT = 22;
	public static final int BOT_SPACING = 10;
	public static final int PLAYER_RADIUS = 16;
	public static final int[] BOT_RADII = new int[] {15, 12, 10};
	public static final Dimension PLAYER_DIMENSION = new Dimension(
			PLAYER_RADIUS * 2, PLAYER_RADIUS * 2);
	public static final Dimension[] BOT_DIMENSIONS = new Dimension[] {
		new Dimension(BOT_RADII[0] * 2, BOT_RADII[0] * 2),
		new Dimension(BOT_RADII[1] * 2, BOT_RADII[1] * 2),
		new Dimension(BOT_RADII[2] * 2, BOT_RADII[2] * 2),
	};
	
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
	
	/* Strokes */
	public static final BasicStroke DEFAULT_STROKE = new BasicStroke(1);
	public static final BasicStroke ACTOR_BORDER_STROKE = new BasicStroke(3);
	public static final BasicStroke CELL_BOUNDS_STROKE = new BasicStroke(5);
}
