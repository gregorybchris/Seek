package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import javax.swing.JPanel;

import actors.Bot;
import constants.DC;
import constants.GC;
import constants.SC;
import constants.MC;
import data.IOData;
import data.Map;

import structure.Engine;
import memory.Memory;
import memory.Cell;


public class GraphicsPanel extends JPanel 
implements KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	private Map map;
	private IOData iodata;

	/*
	 * GraphicsPanel constructor
	 */
	public GraphicsPanel(Map map, IOData iodata) {
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);

		this.map = map;
		this.iodata = iodata;
	}

	/*
	 * Updates all of the graphics according to the Map
	 */
	public void update() {
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(GC.COLOR_BACKGROUND);
		g2.fillRect(0, 0, GC.SCREEN_WIDTH, GC.SCREEN_HEIGHT);

		//TODO: Update with better graphics

		if (DC.SHOW_CELL_BOUNDARIES)
			drawCellBoundaries(g2);

		if(DC.SHOW_DIRECTIONAL_PROBABILITIES)
			drawProbabilities(g2);

		drawPlayer(g2, map.getPlayer().getX(), map.getPlayer().getY());

		Iterator<Bot> botsIterator = map.getBotsIterator();
		while (botsIterator.hasNext()) {
			Bot bot = botsIterator.next();
			drawBot(g2, bot.getX(), bot.getY(), bot.getType());
		}
	}

	/*
	 * Draws the boundary lines between processing cells
	 */
	private void drawCellBoundaries(Graphics2D g2) {
		g2.setColor(GC.CELL_BOUNDS);
		g2.setStroke(GC.CELL_BOUNDS_STROKE);
		int colSize = GC.SCREEN_WIDTH / SC.COLS;
		int rowSize = GC.SCREEN_HEIGHT / SC.ROWS;
		for (int x = 0; x < SC.COLS; x++)
			g2.drawLine(x * colSize, 0, x * colSize, GC.SCREEN_HEIGHT);
		for (int y = 0; y < SC.ROWS; y++)
			g2.drawLine(0, y * rowSize, GC.SCREEN_WIDTH, y * rowSize);
		g2.setStroke(GC.DEFAULT_STROKE);
	}

	/*
	 * Draws lines to indicate the relative probabilities of directions on a cell
	 */
	private void drawProbabilities(Graphics2D g2) {
		int colSize = GC.SCREEN_WIDTH / SC.COLS;
		int _colSize = colSize / 2;
		int rowSize = GC.SCREEN_HEIGHT / SC.ROWS;
		int _rowSize = rowSize / 2;
		Memory engineMemory = Engine.getEngineMemory();
		for (int x = 0; x < SC.COLS; x++) {
			for (int y = 0; y < SC.ROWS; y++) {
				Cell cell = engineMemory.getCell(x, y);
				double[] probs = cell.getProbs();
				int dir = cell.get();
				
				int c_x = colSize * x + _colSize;
				int c_y = GC.SCREEN_HEIGHT - (rowSize * y + _rowSize);
				
				g2.setColor(GC.DIRECTION_COLOR);
				g2.setStroke(GC.DIRECTION_STROKE);
				g2.drawLine(c_x, c_y, c_x, c_y - (int)(_rowSize * probs[MC.MOVE_N]));
				g2.drawLine(c_x, c_y, c_x, c_y + (int)(_rowSize * probs[MC.MOVE_S]));
				g2.drawLine(c_x, c_y, c_x - (int)(_colSize * probs[MC.MOVE_W]), c_y);
				g2.drawLine(c_x, c_y, c_x + (int)(_colSize * probs[MC.MOVE_E]), c_y);


				g2.setColor(GC.PROB_DIRECTION_COLOR);
				g2.setStroke(GC.PROB_DIRECTION_STROKE);
				switch(dir) {
					case MC.MOVE_N:
					g2.drawLine(c_x, c_y, c_x, c_y - _rowSize);
					break;
					case MC.MOVE_S:
					g2.drawLine(c_x, c_y, c_x, c_y + _rowSize);
					break;
					case MC.MOVE_E:
					g2.drawLine(c_x, c_y, c_x + _rowSize, c_y);
					break;
					case MC.MOVE_W:
					g2.drawLine(c_x, c_y, c_x - _rowSize, c_y);
					break;
				}
			}
		}
	}

	/*
	 * Draws a player to the screen at the location given
	 */
	private void drawPlayer(Graphics2D g2, int x, int y) {
		int graphicsX = x;
		int graphicsY = GC.SCREEN_HEIGHT - y - 1;
		g2.setColor(GC.PLAYER_COLOR);
		g2.fillOval(graphicsX - GC.PLAYER_DIMENSION.getWidth() / 2, 
				graphicsY - GC.PLAYER_DIMENSION.getHeight() / 2, 
				GC.PLAYER_DIMENSION.getWidth(), 
				GC.PLAYER_DIMENSION.getHeight());
		g2.setStroke(GC.ACTOR_BORDER_STROKE);
		g2.setColor(GC.ACTOR_BORDER);
		g2.drawOval(graphicsX - GC.PLAYER_DIMENSION.getWidth() / 2, 
				graphicsY - GC.PLAYER_DIMENSION.getHeight() / 2, 
				GC.PLAYER_DIMENSION.getWidth(), 
				GC.PLAYER_DIMENSION.getHeight());
		g2.setStroke(GC.DEFAULT_STROKE);
	}

	/*
	 * Draws a Bot to the screen at the location given
	 */
	private void drawBot(Graphics2D g2, int x, int y, int type) {
		int graphicsX = x;
		int graphicsY = GC.SCREEN_HEIGHT - y - 1;
		g2.setColor(GC.BOT_COLORS[type]);
		g2.fillOval(graphicsX - GC.BOT_DIMENSIONS[type].getWidth() / 2, 
				graphicsY - GC.BOT_DIMENSIONS[type].getHeight() / 2, 
				GC.BOT_DIMENSIONS[type].getWidth(), 
				GC.BOT_DIMENSIONS[type].getHeight());
		g2.setStroke(GC.ACTOR_BORDER_STROKE);
		g2.setColor(GC.ACTOR_BORDER);
		g2.drawOval(graphicsX - GC.BOT_DIMENSIONS[type].getWidth() / 2, 
				graphicsY - GC.BOT_DIMENSIONS[type].getHeight() / 2, 
				GC.BOT_DIMENSIONS[type].getWidth(), 
				GC.BOT_DIMENSIONS[type].getHeight());
		g2.setStroke(GC.DEFAULT_STROKE);
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		iodata.keyDown(ke.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		iodata.keyUp(ke.getKeyCode());
	}

	@Override
	public void mousePressed(MouseEvent me) {
		iodata.setMouseLocation(me.getX(), me.getY());
		iodata.setMousePressed(true);
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		iodata.setMouseLocation(me.getX(), me.getY());
		iodata.setMousePressed(false);
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		iodata.setMouseLocation(me.getX(), me.getY());
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		iodata.setMouseLocation(me.getX(), me.getY());
	}

	@Override
	public void keyTyped(KeyEvent ke) {}
	@Override
	public void mouseClicked(MouseEvent me) {}
	@Override
	public void mouseEntered(MouseEvent me) {}
	@Override
	public void mouseExited(MouseEvent me) {}
}