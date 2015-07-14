package data;

public class Point {
	public int x;
	public int y;

	/*
	 * Point default constructor
	 */
	public Point() {
		this.x = 0;
		this.y = 0;
	}

	/*
	 * Point (x, y) constructor
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Point other Point constructor
	 */
	public Point(Point other) {
		x = other.x;
		y = other.y;
	}

	/*
	 * Sets the x and y values
	 */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Sets the x and y values from another Point
	 */
	public void set(Point other) {
		x = other.x;
		y = other.y;
	}

	/*
	 * Gets the x value
	 */
	public int getX() {
		return x;
	}

	/*
	 * Sets the x value
	 */
	public void setX(int x) {
		this.x = x;
	}

	/*
	 * Gets the y value
	 */
	public int getY() {
		return y;
	}

	/*
	 * Sets the y value
	 */
	public void setY(int y) {
		this.y = y;
	}


	/*
	 * Gets the distance between this Point and another Point
	 */
	public int distance(Point other) { return (int)Math.sqrt(distance2(other)); }

	/*
	 * Gets the square of the distance between this Point and another Point
	 */
	public int distance2(Point other) { return dx(other) * dx(other) + dy(other) * dy(other); }

	/*
	 * Gets the x-distance between this Point and another Point
	 */
	public int dx(Point other) { return x - other.x; }

	/*
	 * Gets the x-distance between this Point and an x-coordinate
	 */
	public int dx(int otherX) { return x - otherX; }	

	/*
	 * Gets the y-distance between this Point and another Point
	 */
	public int dy(Point other) { return y - other.y; }

	/*
	 * Gets the y-distance between this Point and an x-coordinate
	 */
	public int dy(int otherY) { return y - otherY; }

	/*
	 * Gets the distance between this Point and an (x, y) pair
	 */
	public int distance(int x, int y) { return (int)Math.sqrt(distance2(x, y)); }

	/*
	 * Gets the square of the distance between this Point and an (x, y) pair
	 */
	public int distance2(int x, int y) { return dx(x) * dx(x) + dy(y) * dy(y); }

	/*
	 * Moves the (x, y) coordinates of this point over by translation
	 */
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}

	/*
	 * Returns the angle between this Point and another Point
	 */
	public double angle(Point other) {
		return Math.atan2(y - other.y, x - other.x);
	}

	/*
	* Returns the tangent of the angle between this Point and another Point
	*/
	public double tanAngle(Point other) {
		if(dx(other) == 0)
			return -1;
		return dy(other) / dx(other);
	}

   /*
	* Returns the sine of the angle between this Point and another Point
	*/
	public double sinAngle(Point other) {
		return dy(other) / distance(other);
	}

   /*
	* Returns the cosine of the angle between this Point and another Point
	*/
	public double cosAngle(Point other) {
		return dx(other) / distance(other);
	}

	@Override
	public boolean equals(Object other) {
		Point point = (Point) other;
		return point.x == x && point.y == y;
	}

	@Override
	public String toString() {
		return toJSON();
	}
	
	/*
	 * Converts the Point to a stringified JSON object
	 */
	public String toJSON() {
		String stringRep = "{ \n";
		stringRep += "\t \"x\": " + x + ", \n";
		stringRep += "\t \"y\": " + y + " \n";
		stringRep += "}";
		return stringRep;
	}
}
