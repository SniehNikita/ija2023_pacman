package pacman.game;

import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMazeObject;

public class KeyObject implements CommonMazeObject{
	
	private CommonField staysAt = null;
	private CommonMaze existsIn = null;
	
	public KeyObject(CommonField field, CommonMaze maze)
	{
		staysAt = field;
		existsIn = maze;
	}

	@Override
	public boolean canMove(Direction r) {
		return false;
	}

	@Override
	public boolean move(Direction u) {
		return false;
	}

	@Override
	public CommonField getField() {
		return staysAt;
	}

	@Override
	public int getLives() {
		return 0;
	}

	@Override
	public boolean isPacman() {
		return false;
	}
	
	public CommonMaze getMaze() {
		return existsIn;
	}

}
