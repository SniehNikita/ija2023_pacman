package pacman.tools;

import pacman.game.GeneralMaze;

public class MazeConfigure {
		
	CommonMaze maze = null;
	
	private int curLine = 0;
	private boolean isReading = false;

	public void startReading(int rows, int cols) {
		if (!isReading) {
			// TODO ERRHANDLE
		}
		
		if (rows > 0 && cols > 0) {
			maze = new GeneralMaze(rows+2, cols+2);
			for (int i = 0; i < maze.numRows(); i++) {
				for (int j = 0; j < maze.numCols(); j++) {
					((GeneralMaze) maze).initField(i, j);
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
					case '.': 	((GeneralMaze) maze).putPath(curLine, i+1); break;
					case 'X': 	((GeneralMaze) maze).putWall(curLine, i+1); break;
					case 'S': 	((GeneralMaze) maze).putPath(curLine, i+1);
							  	((GeneralMaze) maze).spawnPacman(curLine, i+1); break;
					case 'G':	((GeneralMaze) maze).putPath(curLine, i+1);
				  				((GeneralMaze) maze).spawnGhost(curLine, i+1);break;
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
			isReading = false;
		} else {
			// TODO ERRHANDLE
		}
	}

	public CommonMaze createMaze() {
		for (int i = 0; i < maze.numRows(); i++) {
			((GeneralMaze) maze).putWall(i, 0);
			((GeneralMaze) maze).putWall(i, maze.numCols()-1);
		}
		for (int i = 0; i < maze.numCols(); i++) {
			((GeneralMaze) maze).putWall(0, i);
			((GeneralMaze) maze).putWall(maze.numRows()-1, i);
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
