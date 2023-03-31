package ija.ija2022.homework1.common;

public interface Field {
	
	enum Direction {
		D, L, R, U;
	}
	
	boolean isEmpty();

	boolean canMove();

	MazeObject get();

	boolean remove(MazeObject obj);

	boolean put(MazeObject obj);
	
	void setMaze(Maze maze);

	Field nextField(Direction r);

}
