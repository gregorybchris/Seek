package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import constants.GC;
import data.IOData;
import data.Map;


public class GraphicsPanel extends JPanel 
implements KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	
	private Map map;
	private IOData iodata;

	public GraphicsPanel(Map map, IOData iodata) {
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		
		this.map = map;
		this.iodata = iodata;
	}
	
	public void update() {
		System.out.println("Graphics Update");
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(GC.COLOR_BACKGROUND);
		g2.fillRect(0, 0, GC.SCREEN_WIDTH, GC.SCREEN_HEIGHT);
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
		iodata.setMousePressed(true);
	}

	@Override
	public void mouseReleased(MouseEvent me) {
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