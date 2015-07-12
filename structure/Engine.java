package structure;

import java.awt.event.KeyEvent;
import java.util.Iterator;

import memory.Memory;
import constants.AC;
import constants.GC;
import constants.MC;
import constants.SC;
import data.Bot;
import data.IOData;
import data.Map;
import data.Player;
import data.Point;

public class Engine {
	private Map map;
	private IOData iodata;
	private Memory memory;

	/*
	 * Engine constructor
	 */
	public Engine(Map map, IOData iodata, Memory memory) {
		this.map = map;
		this.iodata = iodata;
		this.memory = memory;
	}

	/*
	 * The main update function that updates the data for the game
	 * 	for every tick in the game loop
	 */
	public void update() {
		updatePlayer();
		updateBots();
	}

	/*
	 * Updates the data related to player movement based on IO
	 */
	private void updatePlayer() {
		Player player = map.getPlayer();
		Point playerPosition = player.getPosition();
		if (SC.NUM_MOVEMENTS <= 5) {
			if (iodata.keyPressed(KeyEvent.VK_UP)) {
				map.getPlayer().move(0, AC.PLAYER_SPEED);
				if (!isInBounds(playerPosition))
					setToClosestInBounds(playerPosition);
				memory.put(playerPosition.x, playerPosition.y, MC.MOVE_N);
			}
			else if (iodata.keyPressed(KeyEvent.VK_DOWN)) {
				map.getPlayer().move(0, -AC.PLAYER_SPEED);
				if (!isInBounds(playerPosition))
					setToClosestInBounds(playerPosition);
				memory.put(playerPosition.x, playerPosition.y, MC.MOVE_S);
			}
			else if (iodata.keyPressed(KeyEvent.VK_LEFT)) {
				map.getPlayer().move(-AC.PLAYER_SPEED, 0);
				if (!isInBounds(playerPosition))
					setToClosestInBounds(playerPosition);
				memory.put(playerPosition.x, playerPosition.y, MC.MOVE_W);
			}
			else if (iodata.keyPressed(KeyEvent.VK_RIGHT)) {
				map.getPlayer().move(AC.PLAYER_SPEED, 0);
				if (!isInBounds(playerPosition))
					setToClosestInBounds(playerPosition);
				memory.put(playerPosition.x, playerPosition.y, MC.MOVE_E);
			}
		}
		//TODO: Add more actions for intercardinal movements
	}

	/*
	 * Updates the data related to Bot movement based on memory
	 * 	This is the main function that uses the memory data to determine
	 * 	Bot behavior
	 */
	private void updateBots() {
		Player player = map.getPlayer();
		Point playerPosition = player.getPosition();
		Iterator<Bot> botsIterator = map.getBotsIterator();
		while (botsIterator.hasNext()) {
			Bot bot = botsIterator.next();
			Point botPosition = bot.getPosition();
			
			int foresight = bot.getForesight();
			Point prediction = new Point(playerPosition);
			for (int i = 0; i < foresight; i++) {
				int nextMovement = memory.get(prediction.x, prediction.y);
				iterateBotPrediction(prediction, 
						nextMovement, player.getSpeed());
				if (!isInBounds(prediction))
					setToClosestInBounds(prediction);
			}
			
			double angle = prediction.angle(botPosition);
			int botSpeed = bot.getSpeed();
			int dx = (int)(Math.cos(angle) * botSpeed);
			int dy = (int)(Math.sin(angle) * botSpeed);
			bot.move(dx, dy);
			
			//TODO: Add logic for if bot can get to the place where 
			//	it thinks the player will be before the player can get there
			//	then go there instead of continuing predictions
		}
	}

	/*
	 * Updates the destination of the Bot based on where the player might go
	 */
	private void iterateBotPrediction(Point prediction, 
			int movement, int speed) {
		switch (movement) {
		case MC.MOVE_H:
			break;
		case MC.MOVE_N:
			prediction.translate(0, speed);
			break;
		case MC.MOVE_S:
			prediction.translate(0, -speed);
			break;
		case MC.MOVE_E:
			prediction.translate(speed, 0);
			break;
		case MC.MOVE_W:
			prediction.translate(-speed, 0);
			break;
		default:
			break;
		}
		
		//TODO: Add more actions for intercardinal movements
	}
	
	/*
	 * Returns true if the point provided is within the screen bounds
	 */
	private boolean isInBounds(Point position) {
		int x = position.x;
		int y = position.y;
		return x >= 0 && x < GC.SCREEN_WIDTH && y >= 0 && y < GC.SCREEN_HEIGHT;
	}
	
	/*
	 * Adjusts a point to be the closest point to it, but in the screen bounds
	 */
	private void setToClosestInBounds(Point position) {
		if (position.x < 0)
			position.x = 0;
		if (position.x >= GC.SCREEN_WIDTH)
			position.x = GC.SCREEN_WIDTH - 1;
		if (position.y < 0)
			position.y = 0;
		if (position.y >= GC.SCREEN_HEIGHT)
			position.y = GC.SCREEN_HEIGHT - 1;
	}
}
