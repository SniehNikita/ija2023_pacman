package pacman.tools;

import java.awt.Graphics2D;
import java.util.List;

public interface CommonMaze {

	CommonField getField(int i, int c);

	int numCols();

	int numRows();
	
	public void movePacman(CommonMazeObject pacman, CommonField field);
	
	public boolean spawnPacman(int r, int c);
	
	public boolean spawnGhost(int r, int c);
	
	public boolean spawnKey(int r, int c);
	
	public boolean spawnTarget(int r, int c);
	
	List<CommonMazeObject> ghosts();

	List<CommonMazeObject> pacmans();

	List<CommonMazeObject> keys();

	List<CommonMazeObject> targets();

	void draw(Graphics2D g);
}
