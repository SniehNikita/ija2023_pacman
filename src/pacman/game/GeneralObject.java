package pacman.game;

import java.awt.Graphics2D;

import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMazeObject;

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
			staysAt = staysAt.nextField(u);
			((PathField) staysAt).put(this);
			return true;
		}
		return false;
	}

	@Override
	public boolean isPacman() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void draw(Graphics2D g) {
		
	}
}
