package structure;

import java.awt.event.KeyEvent;
import java.util.Iterator;

import memory.Memory;
import actors.Bot;
import actors.Player;
import constants.AC;
import constants.GC;
import constants.MC;
import constants.SC;
import data.IOData;
import data.Map;
import data.Point;

public class Engine {
	private static Engine instance;
	private Map map;
	private IOData iodata;
	private Memory memory;

	/*
	 * Engine constructor
	 */
	public Engine(Map map, IOData iodata, Memory memory) {
		Engine.instance = this;
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
		checkKeyInputs();
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
		
		Iterator<Bot> botsCollisionIterator = map.getBotsIterator();
		while (botsCollisionIterator.hasNext()) {
			Bot bot = botsCollisionIterator.next();
			Point botPosition = bot.getPosition();
			int botRadius = GC.BOT_RADII[bot.getType()];
			
			int distance = botPosition.distance(playerPosition);
			if (distance < GC.PLAYER_RADIUS + botRadius) {
				//TODO: Maybe do something if the player got caught
				/*
				double angle = botPosition.angle(playerPosition);
				botPosition.translate(
					(int)(Math.cos(angle) * (double)(GC.PLAYER_RADIUS + botRadius) - distance),
					(int)(Math.sin(angle) * (double)(GC.PLAYER_RADIUS + botRadius) - distance)
				);
				*/
			}
		}
		
	}

	/*
	 * Updates the data related to Bot movement based on memory
	 * 	This is the main function that uses the memory data to determine
	 * 	Bot behavior
	 */
	private void updateBots() {
		Player player = map.getPlayer();
		Point playerPosition = player.getPosition();
		Iterator<Bot> botsMovementIterator = map.getBotsIterator();
		while (botsMovementIterator.hasNext()) {
			Bot bot = botsMovementIterator.next();
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
		
		Iterator<Bot> botsCollisionIteratorI = map.getBotsIterator();
		int botI = 0;
		while (botsCollisionIteratorI.hasNext()) {
			Bot iBot = botsCollisionIteratorI.next();
			Iterator<Bot> botsCollisionIteratorJ = map.getBotsIterator();
			int botJ = 0;
			while (botsCollisionIteratorJ.hasNext()) {
				Bot jBot = botsCollisionIteratorJ.next();
				if (botI != botJ)
					fixBotCollision(iBot, jBot);
				botJ++;
			}
			botI++;
		}
	}
	
	/*
	 * Makes sure that two bots do not collide
	 */
	private void fixBotCollision(Bot botA, Bot botB) {
		Point botAPosition = botA.getPosition();
		Point botBPosition = botB.getPosition();
		int botARadius = GC.BOT_RADII[botA.getType()];
		int botBRadius = GC.BOT_RADII[botB.getType()];
		
		int distance = botAPosition.distance(botBPosition);
		int idealSpacing = botARadius + botBRadius + GC.BOT_SPACING;
		if (distance < idealSpacing) {
			double angleA = botAPosition.angle(botBPosition);
			double angleB = botBPosition.angle(botAPosition);
			
			int repulsion = (int)(idealSpacing - distance);
			int dxA = (int)(Math.cos(angleA) * repulsion);
			int dyA = (int)(Math.sin(angleA) * repulsion);
			int dxB = (int)(Math.cos(angleB) * repulsion);
			int dyB = (int)(Math.sin(angleB) * repulsion);
			botA.move(dxA, dyA);
			botB.move(dxB, dyB);
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
	
	/*
	 * Checks for all relevant current key events and performs their actions
	 */
	private void checkKeyInputs() {
		if (iodata.keyAccess(KeyEvent.VK_ESCAPE))
			System.exit(0);
		if (iodata.keyAccess(KeyEvent.VK_R))
			map.reset();
	}

	public static Memory getEngineMemory() {
		return instance.memory;
	}
}
