package ija.ija2022.homework2.tool.common;

import java.util.List;

public interface CommonMaze {

	CommonField getField(int i, int j);

	List<CommonMazeObject> ghosts();
	
	int numCols();
	
	int numRows();

}
