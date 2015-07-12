package graphics;

import javax.swing.JFrame;

import data.IOData;
import data.Map;


public class GraphicsFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private GraphicsPanel graphicsPanel;

	/*
	 * GraphicsFrame constructor
	 */
	public GraphicsFrame(String title, Map map, IOData iodata) {
		super(title);
		graphicsPanel = new GraphicsPanel(map, iodata);
		this.getContentPane().add(graphicsPanel);
	}
	
	/*
	 * Updates all of the graphics on graphicsPanel
	 */
	public void update() {
		graphicsPanel.update();
	}
}