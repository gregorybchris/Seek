package data;

public class Dimension {
	private int width;
	private int height;
	
	/*
	 * Dimension all parameter constructor
	 */
	public Dimension(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/*
	 * Dimension default constructor
	 */
	public Dimension() {
		this(0, 0);
	}
	
	/*
	 * Gets the width of the Dimension
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * Sets the width of the Dimension
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/*
	 * Gets the height of the Dimension
	 */
	public int getHeight() {
		return height;
	}
	
	/*
	 * Sets the height of the Dimension
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
