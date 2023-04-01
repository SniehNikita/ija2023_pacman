package ija.ija2022.homework1.game;

import ija.ija2022.homework1.common.Field;
import ija.ija2022.homework1.common.Field.Direction;
import ija.ija2022.homework1.common.Maze;
import ija.ija2022.homework1.common.MazeObject;

public class PacmanObject implements MazeObject{
	private Field staysAt = null;
//	private Maze existsIn = null;
		
	public PacmanObject(Field field, Maze maze) {
		staysAt = field;
//		existsIn = maze;
	}
	
	@Override
	public boolean canMove(Direction r) {
		Field next = staysAt.nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean move(Direction u) {
		if (this.canMove(u)) {
			staysAt.remove(this);
			staysAt = staysAt.nextField(u);
			try { staysAt.put(this); } catch (Exception e) { }
			return true;
		}
		return false;
	}

}
