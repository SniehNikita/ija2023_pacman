package pacman.tools;

import java.util.List;

public interface CommonMaze {

	CommonField getField(int i, int c);

	int numCols();

	int numRows();

	List<CommonMazeObject> ghosts();

}
