package structure;

import data.IOData;
import data.Map;

public class Engine {
	private Map map;
	private IOData iodata;
	
	public Engine(Map map, IOData iodata) {
		this.map = map;
		this.iodata = iodata;
	}
	
	public void update() {
		System.out.println("Engine Update");
	}
}
