package ija.ija2022.homework1.game;
import ija.ija2022.homework1.common.Field;
import ija.ija2022.homework1.common.Maze;
import ija.ija2022.homework1.common.MazeObject;

public class GeneralField implements Field {
	protected int row = -1;
	protected int col = -1;
	protected Maze maze = null;
	
	public GeneralField(int i, int j) {
		if (i >= 0 && j >= 0) {
			row = i;
			col = j;
			maze = null;
		} else {
			// TODO ERRHANDLE
		}
	}
	
	public boolean put(MazeObject obj) {
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

	@Override
	public void setMaze(Maze maze) {
		this.maze = maze;		
	}

	@Override
	public Field nextField(Direction r) {
		return null;
	}

	@Override
	public MazeObject get() {
		return (MazeObject) maze;
	}

	@Override
	public boolean remove(MazeObject obj) {
		return false;
	}



}
