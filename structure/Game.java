package structure;

import graphics.GraphicsFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import memory.Memory;
import constants.GC;
import constants.SC;
import data.IOData;
import data.Map;

public class Game {
	private GraphicsFrame graphicsFrame;
	private Engine engine;
	
	/*
	 * Game constructor
	 */
	public Game() {
		Map map = new Map();
		IOData iodata = new IOData();
		Memory memory = new Memory(GC.SCREEN_WIDTH, GC.SCREEN_HEIGHT, 
				SC.ROWS, SC.COLS, SC.NUM_MOVEMENTS, SC.NUM_TOP_VALUES, SC.ETA);
		engine = new Engine(map, iodata, memory);
		setUpGraphics(map, iodata);
	}
	
	/*
	 * Creates a graphics frame and panel to both show the current state
	 * 	of the game and to take in user input from mouse and keyboard
	 */
	private void setUpGraphics(Map map, IOData iodata) {
		String title = "Seek";
		graphicsFrame = new GraphicsFrame(title, map, iodata);
		graphicsFrame.setBounds(0, 0, 
				GC.SCREEN_WIDTH, GC.SCREEN_HEIGHT + GC.WINDOW_BAR_HEIGHT);
		graphicsFrame.setLocationRelativeTo(null);
		graphicsFrame.setResizable(false);
		graphicsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphicsFrame.setVisible(true);
	}
	
	/*
	 * Starts the game loop that refreshes data and graphics
	 */
	public void startGame() {
		int delay = (int)(1.0 / GC.FRAME_RATE * 1000);
		Timer timer = new Timer(delay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				engine.update();
				graphicsFrame.update();
			}
		});
		timer.start();
	}
}
