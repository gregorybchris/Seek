package actors;

import constants.AC;

public class Bot extends Actor {
	private int type;
	private int foresight;
	
	/*
	 * Bot all parameter constructor
	 */
	public Bot(int type, int x, int y) {
		this.type = type;
		this.position.set(x, y);
		foresight = AC.BOT_FORESIGHTS[type] + (int)((Math.random() - 0.5) * AC.BOT_FORESIGHTS[type]);
		speed = AC.BOT_SPEEDS[type];
	}
	
	/*
	 * Bot default constructor
	 */
	public Bot() {
		this(AC.DRONE, 0, 0);
	}
	
	/*
	 * Bot type constructor
	 */
	public Bot(int type) {
		this(type, 0, 0);
	}
	
	/*
	 * Bot (x, y) position constructor
	 */
	public Bot(int x, int y) {
		this(AC.DRONE, x, y);
	}
	
	/*
	 * Gets the type of the Bot
	 */
	public int getType() {
		return type;
	}
	
	/*
	 * Gets how many steps ahead the Bot can think
	 */
	public int getForesight() {
		return foresight;
	}
}
