package ija.ija2022.homework2.tool.common;

import ija.ija2022.homework2.tool.common.CommonField.Direction;

public interface CommonMazeObject {
	
	boolean canMove(CommonField.Direction dir);

	CommonField getField();

	boolean isPacman();

	boolean move(CommonField.Direction d);

	Object getLives();

}
