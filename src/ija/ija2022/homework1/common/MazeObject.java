package ija.ija2022.homework1.common;

import ija.ija2022.homework1.common.Field.Direction;

public interface MazeObject {

	boolean canMove(Direction r);

	boolean move(Direction u);

}
