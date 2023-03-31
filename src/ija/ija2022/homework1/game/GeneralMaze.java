package ija.ija2022.homework1.game;

import ija.ija2022.homework1.common.Field;
import ija.ija2022.homework1.common.Maze;

public class GeneralMaze implements Maze {
	private int rows;
	private int cols;
	private Field[][] maze;
	
	public GeneralMaze(int rows, int cols) {
		this.maze = new Field[rows][cols];
		this.rows = rows;
		this.cols = cols;
	}

	@Override
	public int numRows() {
		return rows;
	}

	@Override
	public int numCols() {
		return cols;
	}

	@Override
	public Field getField(int i, int c) {
		if (i < 0 || c < 0 || i > rows-1 || c > cols-1) {
			return null;
		}
		return maze[i][c];
	}
	
	public boolean setField(int r, int c, Field f) {
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		this.maze[r][c] = f;
		return true;
	}

}
