package pacman.game;

import java.awt.Graphics2D;

import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMazeObject;

public class TargetObject implements CommonMazeObject{

	private CommonField staysAt = null;
	private CommonMaze existsIn = null;
	
	public TargetObject(CommonField field, CommonMaze maze)
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

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

}
