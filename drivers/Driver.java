package drivers;

import constants.MC;
import memory.Memory;

public class Driver {
	public static void main(String[] args) {
		Memory memory = new Memory(100, 100, 2, 2, 5, 5, 0.01);
		
		memory.insert(20, 40, MC.MOVE_S);
		memory.insert(20, 40, MC.MOVE_W);
		memory.insert(20, 40, MC.MOVE_N);
		memory.insert(20, 40, MC.MOVE_S);
		memory.insert(20, 40, MC.MOVE_W);
		memory.insert(20, 40, MC.MOVE_N);
		memory.insert(20, 40, MC.MOVE_S);
		memory.insert(20, 40, MC.MOVE_W);
		memory.insert(20, 40, MC.MOVE_N);
		
		System.out.println(memory);
	}
}
