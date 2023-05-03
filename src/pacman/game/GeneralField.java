package pacman.game;

import java.awt.Graphics2D;

import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonMazeObject;

public class GeneralField implements CommonField {
	
	protected int row = -1;
	protected int col = -1;
	protected CommonMaze maze = null;
	
	public GeneralField(int i, int j) {
		if (i >= 0 && j >= 0) {
			row = i;
			col = j;
			maze = null;
		}
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getCol() {
		return col;
	}

	public boolean put(CommonMazeObject obj) {
//		throw new Exception("UnsupportedOperationException");
		return false;
	}
	
	@Override
	public boolean isEmpty() {
		return (row < 0 || col < 0);
	}

	@Override
	public boolean canMove() {
		return false;
	}

	public void setMaze(CommonMaze maze) {
		this.maze = maze;		
	}

	@Override
	public CommonField nextField(Direction r) {
		return null;
	}

	@Override
	public CommonMazeObject get() {
		return (CommonMazeObject) maze;
	}

	public boolean remove(CommonMazeObject obj) {
		return false;
	}

	@Override
	public boolean contains(CommonMazeObject obj) {
		return false;
	}

	@Override
	public void draw(Graphics2D g2d) {
			
	}
}
