package ija.ija2022.homework1.game;

import ija.ija2022.homework1.common.Field;
import ija.ija2022.homework1.common.MazeObject;

public class PathField extends GeneralField {	
	protected MazeObject pacman = null;
	
	public PathField(int i, int j) {
		super(i,j);
		pacman = null;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof PathField) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public MazeObject get() {
		return pacman;
	}

	@Override
	public boolean remove(MazeObject obj) {
		if (pacman == obj) {
			pacman = null;
			return true;
		} else {
			return false;	
		}
	}
	
	@Override
	public boolean isEmpty() {
		return pacman == null;
	}

	@Override
	public boolean put(MazeObject obj) {
		if (pacman == null) {
			pacman = obj;
		} else {
			return false;
		}
		return true;
	}
	
	public boolean canMove() {
		return true;
	}

	@Override
	public Field nextField(Direction r) {
		switch (r) {
		case D: return maze.getField(row-1, col);
		case L: return maze.getField(row, col-1);
		case R: return maze.getField(row, col+1);
		case U: return maze.getField(row+1, col);
		};
		return null;
	}

}
