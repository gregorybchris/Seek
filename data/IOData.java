package data;

import java.util.HashSet;

public class IOData {
	private HashSet<Integer> keySet;
	private boolean mousePressed;
	private Point mouseLocation;

	/*
	 * IOData constructor
	 */
	public IOData() {
		keySet = new HashSet<Integer>();
		mousePressed = false;
		mouseLocation = new Point();
	}

	/*
	 * Sets the key to down for the keycode given
	 */
	public void keyDown(int key) {
		keySet.add(key);
	}

	/*
	 * Sets the key to up for the keycode given
	 */
	public void keyUp(int key) {
		keySet.remove(key);
	}

	/*
	 * Returns true if the key is currently pressed
	 */
	public boolean keyPressed(int keyCode) {
		return keySet.contains(keyCode);
	}

	/*
	 * Returns true if the key is currently pressed and sets the pressed
	 * 	flag to false so that no more events will be fired
	 * 	This is the function to use when the desired behavior is one-time
	 */
	public boolean keyAccess(int keyCode) {
		boolean setContainsKey = keySet.contains(keyCode);
		keySet.remove(keyCode);
		return setContainsKey;
	}

	/*
	 * Sets the flag for whether the mouse is currently pressed
	 */
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	/*
	 * Returns true if the mouse is currently pressed
	 */
	public boolean getMousePressed() {
		return mousePressed;
	}

	/*
	 * Sets the location of the mouse to an (x, y) coordinate
	 */
	public void setMouseLocation(int x, int y) {
		mouseLocation.x = x;
		mouseLocation.y = y;
	}

	/*
	 * Gets the x value of the position of the mouse
	 */
	public double getMouseX() {
		return mouseLocation.x;
	}

	/*
	 * Gets the y value of the position of the mouse
	 */
	public double getMouseY() {
		return mouseLocation.y;
	}
}
