package pacman.tools;

import pacman.tools.CommonField.Direction;

public final class Direct {
	
	public static Direction left(Direction d) {
		switch (d) {
		case U: return Direction.L;
		case L: return Direction.D;
		case D: return Direction.R;
		case R: return Direction.U;
		}
		return null;
	}
	
	public static Direction right(Direction d) {
		switch (d) {
		case U: return Direction.R;
		case L: return Direction.U;
		case D: return Direction.L;
		case R: return Direction.D;
		}
		return null;
	}
	
	public static Direction back(Direction d) {
		switch (d) {
		case U: return Direction.D;
		case L: return Direction.R;
		case D: return Direction.U;
		case R: return Direction.L;
		}
		return null;
	}
	
	public static int angle(Direction d1, Direction d2) {
		if (d1 == d2) {
			return 0;
		}
		if ((d1 == Direction.U && d2 == Direction.D) ||
			(d1 == Direction.D && d2 == Direction.U) ||
			(d1 == Direction.L && d2 == Direction.R) ||
			(d1 == Direction.R && d2 == Direction.L)) {
			return 180;
		}
		return 90;
	}
}
