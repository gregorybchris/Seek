package data;

public abstract class Actor {
	protected Point position = new Point();
	protected int speed = 0;
	
	/*
	 * Gets the current position of the Actor
	 */
	public Point getPosition() {
		return position;
	}
	
	/*
	 * Gets the X value of the Actor's position
	 */
	public int getX() {
		return position.x;
	}
	
	/*
	 * Sets the X value of the Actor's position
	 */
	public void setX(int x) {
		position.setX(x);
	}
	
	/*
	 * Gets the Y value of the Actor's position
	 */
	public int getY() {
		return position.y;
	}
	
	/*
	 * Sets the Y value of the Actor's position
	 */
	public void setY(int y) {
		position.setY(y);
	}
	
	/*
	 * Sets the position of the Actor to the given (x, y) coordinates
	 */
	public void setPosition(int x, int y) {
		position.set(x, y);
	}
	
	/*
	 * Translates the position of the Actor by the given (x, y) coordinates
	 */
	public void move(int dx, int dy) {
		this.position.translate(dx, dy);
	}
	
	/*
	 * Translates the position of the Actor by the given Point
	 */
	public void move(Point offset) {
		this.position.translate(offset.x, offset.y);
	}
	
	/*
	 * Sets the position of the Actor to the Point provided
	 */
	public void moveTo(Point point) {
		this.position.set(point);
	}
	

	/*
	 * Gets the speed of the Actor (roughly in pixels per tick)
	 */
	public int getSpeed() {
		return speed;
	}
	
	/*
	 * Sets the speed of the Actor (roughly in pixels per tick)
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
