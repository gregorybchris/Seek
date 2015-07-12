package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import actors.Bot;
import actors.Player;
import constants.AC;
import constants.GC;

public class Map {
	private Player player;
	private ArrayList<Bot> bots;
	
	/*
	 * Map constructor
	 */
	public Map() {
		bots = new ArrayList<Bot>();
		
		setUpPlayer();
		setUpBots();
	}
	
	/*
	 * Creates the initial setup of the Player on the screen
	 */
	private void setUpPlayer() {
		Random random = new Random();
		//TODO: replace 100 and 50 with actual values
		int playerX = random.nextInt(GC.SCREEN_WIDTH - 100) + 50;
		int playerY = random.nextInt(GC.SCREEN_HEIGHT - 100) + 50;
		player = new Player(playerX, playerY);
	}
	
	/*
	 * Creates the initial setup of all Bots that will be on the screen
	 */
	private void setUpBots() {
		Random random = new Random();
		int numDrones = 5;
		int numRunners = 3;
		int numGurus = 1;
		for (int i = 0; i < numDrones; i++) {
			int botX = random.nextInt(GC.SCREEN_WIDTH - 100) + 50;
			int botY = random.nextInt(GC.SCREEN_HEIGHT - 100) + 50;
			bots.add(new Bot(AC.DRONE, botX, botY));
		}
		for (int i = 0; i < numRunners; i++) {
			int botX = random.nextInt(GC.SCREEN_WIDTH - 100) + 50;
			int botY = random.nextInt(GC.SCREEN_HEIGHT - 100) + 50;
			bots.add(new Bot(AC.RUNNER, botX, botY));
		}
		for (int i = 0; i < numGurus; i++) {
			int botX = random.nextInt(GC.SCREEN_WIDTH - 100) + 50;
			int botY = random.nextInt(GC.SCREEN_HEIGHT - 100) + 50;
			bots.add(new Bot(AC.GURU, botX, botY));
		}
	}
	
	/*
	 * Returns the iterator for the bots ArrayList
	 */
	public Iterator<Bot> getBotsIterator() {
		return bots.iterator();
	}
	
	/*
	 * Gets the Map Player object
	 */
	public Player getPlayer() {
		return player;
	}
}
