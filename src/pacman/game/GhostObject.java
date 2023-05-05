package pacman.game;

import java.awt.Graphics2D;
import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonField.Direction;

public class GhostObject extends GeneralObject {
		
	public GhostObject(CommonField field, CommonMaze maze) {
		super(field, maze);
	}
	
	@Override
	public boolean canMove(Direction r) {
		CommonField next = this.getField().nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
	}

	@Override
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
		return false;
	}

	@Override
	public int getLives() {
		// TODO Exception
		return 0;
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(CONST.GHOST_IMG, this.getField().getCol()*CONST.SPRITE_SIZE, this.getField().getRow()*CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
	}
}
