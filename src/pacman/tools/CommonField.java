package pacman.tools;

import java.awt.Graphics2D;

public interface CommonField {

	public enum Direction {
		U, L, D, R
	}

	boolean isEmpty();

	boolean canMove();

	CommonField nextField(Direction r);

	CommonMazeObject get();

	boolean contains(CommonMazeObject obj);

	int getRow();

	int getCol();

	void draw(Graphics2D g2d);
}
