package pacman.game;

import java.awt.Graphics2D;

import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMazeObject;

public class GeneralObject implements CommonMazeObject {
	
	private CommonField pos_field;
	private CommonMaze pos_maze;
	protected float pos_offset = 0;
	protected Direction last_direction;
	
	public GeneralObject(CommonField field, CommonMaze maze) {
		pos_field = field;
		pos_maze = maze;
		pos_offset = 0;
		last_direction = Direction.U;
	}	
	
	public boolean canMove(Direction r) {		
		CommonField next = this.getField().nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
	}
	
	public void setField(CommonField field) {
		this.pos_field = field;
	}
	
	public CommonField getField() {
		return pos_field;
	}
	
	public CommonMaze getMaze() {
		return pos_maze;
	}
	
	public int getLives() {
		return 0;
	}

	public boolean move(Direction u) {
		if (this.canMove(u)) {
			((PathField) this.getField()).remove(this);
			this.setField(this.getField().nextField(u));
			((PathField) this.getField()).put(this);
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
