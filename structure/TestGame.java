package structure;

import java.util.Scanner;

import memory.Memory;
import constants.MC;
import constants.SC;

public class TestGame {
	private Scanner in;
	private Memory memory;
	private int x;
	private int y;
	private int speed;

	/*
	 * Game constructor
	 */
	public TestGame() {
		in = new Scanner(System.in);
		memory = new Memory(SC.WIDTH, SC.HEIGHT, SC.ROWS, SC.COLS, 
				SC.NUM_MOVEMENTS, SC.NUM_TOP_VALUES, SC.ETA);
		x = SC.WIDTH - 5;
		y = SC.HEIGHT - 5;
		speed = 10;
	}

	/*
	 * Starts the testing 
	 */
	public void play() {
		while (true) {
			System.out.println(memory);
			System.out.println("X=" + x + ", Y=" + y);
			String line = in.nextLine();
			
			if (line.equals("w")) {
				if (y + speed < SC.HEIGHT) {
					y = y + speed;
					memory.put(x, y, MC.MOVE_N);
				}
				else
					System.out.println("Bump!");
			}
			else if (line.equals("a")) {
				if (x - speed >= 0) {
					x = x - speed;
					memory.put(x, y, MC.MOVE_W);
				}
				else
					System.out.println("Bump!");
			}
			else if (line.equals("s")) {
				if (y - speed >= 0) {
					y = y - speed;
					memory.put(x, y, MC.MOVE_S);
				}
				else
					System.out.println("Bump!");
			}
			else if (line.equals("d")) {
				if (x + speed < SC.WIDTH) {
					x = x + speed;
					memory.put(x, y, MC.MOVE_E);
				}
				else
					System.out.println("Bump!");
			}
			else if (line.equals("q")) {
				
				System.exit(0);
			}
			else
				System.out.println("Invalid movement!");
		}
	}
}
