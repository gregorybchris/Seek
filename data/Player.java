package data;

import constants.AC;

public class Player extends Actor {
	
	/*
	 * Player all parameter constructor
	 */
	public Player(int x, int y) {
		this.position.set(x, y);
		speed = AC.PLAYER_SPEED;
	}
	
	/*
	 * Player default constructor
	 */
	public Player() {
		this(0, 0);
	}
}
