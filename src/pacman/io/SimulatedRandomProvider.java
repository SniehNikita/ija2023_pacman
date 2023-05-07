package pacman.io;

import java.util.Stack;

import pacman.tools.CommonRandom;

public class SimulatedRandomProvider implements CommonRandom {
	private Stack<Integer> val_list;
	private int buf;
	
	public SimulatedRandomProvider() {
		val_list = new Stack<Integer>();
	}
	
	public int getRandomNumber(int min, int max) {
		return 0;
//		if (this.val_list.size() != 0) {
//			buf = this.val_list.pop();
//			return buf;
//		} else {
//			return buf;
//		}
	}
	
	public void putRandomNumber(int num) {
		this.val_list.push(num);
	}	
}
