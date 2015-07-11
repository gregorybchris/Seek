package drivers;

import memory.Memory;

public class Driver {
	public static void main(String[] args) {
		Memory memory = new Memory(100, 100, 2, 2, 5, 3, 0.01);
		System.out.println(memory);
	}
}
