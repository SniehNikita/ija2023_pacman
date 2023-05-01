package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonField.Direction;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class GeneralObject implements CommonMazeObject {
	
	private CommonField staysAt = null;
	private CommonMaze existsIn = null;
	
	public GeneralObject(CommonField field, CommonMaze maze) {
		staysAt = field;
		existsIn = maze;
	}	
	
	public boolean canMove(Direction r) {		
		CommonField next = staysAt.nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
	}
	
	public CommonField getField() {
		return staysAt;
	}
	
	public CommonMaze getMaze() {
		return existsIn;
	}
	
	public int getLives() {
		return 0;
	}

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
}
