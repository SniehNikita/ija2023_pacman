package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonField.Direction;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class PacmanObject implements CommonMazeObject {

	private CommonField staysAt = null;
	private CommonMaze existsIn = null;
	private int lives;
		
	public PacmanObject(CommonField field, CommonMaze maze, int lives) {
		staysAt = field;
		existsIn = maze;
		this.lives = lives;
	}
	
	@Override
	public boolean canMove(Direction r) {
		CommonField next = staysAt.nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
	}
	
	public void hit() {
		lives = lives - 1;
		if (lives == 0) {
//			GGWP
		}
	}

	@Override
	public boolean move(Direction u) {
		if (this.canMove(u)) {
			((PathField) staysAt).remove(this);
			((PathField) staysAt).notifyObservers();
			staysAt = staysAt.nextField(u);
			((PathField) staysAt).put(this);
			((PathField) staysAt).notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public CommonField getField() {
		return staysAt;
	}

	@Override
	public boolean isPacman() {
		return true;
	}

	@Override
	public int getLives() {
		return this.lives;
	}

	public CommonMaze getMaze() {
		return existsIn;
	}
}
