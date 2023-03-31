package ija.ija2022.homework1.game;

import ija.ija2022.homework1.common.Maze;

public class MazeConfigure {
	GeneralMaze maze = null;
	
	private int curLine = 0;
	private boolean isReading = false;
	
	public MazeConfigure() {
		maze = null;
		curLine = 0;
		isReading = false;
	}
	
	public void startReading(int rows, int cols) {
		if (!isReading) {
			// TODO ERRHANDLE
		}
		
		if (rows > 0 && cols > 0) {
			maze = new GeneralMaze(rows+2, cols+2);
			for (int i = 0; i < maze.numRows(); i++) {
				for (int j = 0; j < maze.numCols(); j++) {
					maze.setField(i, j, null);
				}
			}
			curLine = 1;
			isReading = true;
		} else {
			// TODO ERRHANDLE
		}
	}

	public void processLine(String string) {
		if (isReading && curLine < maze.numRows()) {
			for (int i = 0; i < string.length(); i++) {
				switch(string.charAt(i)) {
				case '.': 	maze.setField(curLine, i+1, new PathField(curLine, i+1));
							maze.getField(curLine, i+1).setMaze(maze); break;
				case 'X': 	maze.setField(curLine, i+1, new WallField(curLine, i+1));
							maze.getField(curLine, i+1).setMaze(maze); break;
				case 'S': 	maze.setField(curLine, i+1, new PathField(curLine, i+1));
							maze.getField(curLine, i+1).setMaze(maze); 
						  	try {maze.getField(curLine, i+1).put(new PacmanObject(maze.getField(curLine, i+1), maze)); } catch (Exception e) { } break;
				default : /* TODO ERRHANDLE */ break;
				}
			}
			curLine++;
		} else {
			// TODO ERRHANDLE
		} 
	}

	public void stopReading() {
		if (isReading && curLine + 1 == maze.numRows()) {
			// fclose(stdin);
			isReading = false;
		} else {
			// TODO ERRHANDLE
		}
	}

	public Maze createMaze() {
		for (int i = 0; i < maze.numRows(); i++) {
			maze.setField(i, 0, new WallField(i, 0));
			maze.getField(i, 0).setMaze(maze);
			maze.setField(i, maze.numCols()-1, new WallField(i, maze.numCols()-1));
			maze.getField(i, 0).setMaze(maze);
		}
		for (int i = 0; i < maze.numCols(); i++) {
			maze.setField(0, i, new WallField(0, i));
			maze.getField(i, 0).setMaze(maze);
			maze.setField(maze.numRows()-1, i, new WallField(maze.numRows()-1, i));
			maze.getField(i, 0).setMaze(maze);
		}
		
		for (int i = 0; i < maze.numRows(); i++) {
			for (int j = 0; j < maze.numCols(); j++) {
				if (maze.getField(i, j) == null) {
					return null;
				}
			}
		}
		
		return maze;
	}

}
