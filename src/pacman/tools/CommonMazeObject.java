package pacman.tools;

import pacman.tools.CommonField.Direction;

public interface CommonMazeObject {

	boolean canMove(Direction r);

	boolean move(Direction u);

	CommonField getField();

	int getLives();

	boolean isPacman();

}
