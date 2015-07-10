package drivers;

import memory.Memory;

public class Driver {
	public static void main(String[] args) {
		Memory memory = new Memory(600, 600, 10, 10, 5, 3);
		System.out.println(memory);
	}
}
