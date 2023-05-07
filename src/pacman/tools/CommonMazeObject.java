package pacman.tools;

import java.awt.Graphics2D;

import pacman.tools.CommonField.Direction;

public interface CommonMazeObject {

	boolean canMove(Direction r);

	boolean move(Direction u);

	CommonField getField();

	int getLives();

	boolean isPacman();

	void draw(Graphics2D g);
	
	float getX();
	
	float getY();
	
	boolean checkCollision(CommonMazeObject obj);
}
