package pacman.io;

import pacman.tools.CommonRandom;

public class RandomProvider implements CommonRandom {
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}

}
