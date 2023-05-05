package pacman.tools;

import java.awt.Graphics2D;
import java.util.List;

public interface CommonMaze {

	CommonField getField(int i, int c);

	int numCols();

	int numRows();

	List<CommonMazeObject> ghosts();

	List<CommonMazeObject> pacmans();

	List<CommonMazeObject> keys();

	List<CommonMazeObject> targets();

	void draw(Graphics2D g);
}
