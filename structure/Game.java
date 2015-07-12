package structure;

import graphics.GraphicsFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import constants.GC;
import data.IOData;
import data.Map;

public class Game {
	private static final int DELAY = 60;
	private GraphicsFrame graphicsFrame;
	private Engine engine;
	
	public Game() {
		Map map = new Map();
		IOData iodata = new IOData();
		engine = new Engine(map, iodata);
		setUpGraphics(map, iodata);
	}
	
	private void setUpGraphics(Map map, IOData iodata) {
		String title = "Seek";
		graphicsFrame = new GraphicsFrame(title, map, iodata);
		graphicsFrame.setBounds(0, 0, GC.SCREEN_WIDTH, GC.SCREEN_HEIGHT);
		graphicsFrame.setLocationRelativeTo(null);
		graphicsFrame.setResizable(false);
		graphicsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphicsFrame.setVisible(true);
	}
	
	public void startGame() {
		Timer timer = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				engine.update();
				graphicsFrame.update();
			}
		});
		timer.start();
	}
}
