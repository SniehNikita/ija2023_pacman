package pacman.game;

import java.awt.Graphics2D;

import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonField.Direction;

public class KeyObject extends GeneralObject {
	
	public KeyObject(CommonField field, CommonMaze maze) {
		super(field, maze);
	}
	
	@Override
	public boolean canMove(Direction r) {
		return false;
	}

	@Override
	public boolean move(Direction u) {
		return false;
	}
	
	@Override
	public int getLives() {
		return 0;
	}

	@Override
	public boolean isPacman() {
		return false;
	}
	
	@Override
	public void draw(Graphics2D g) {
		int x = this.getField().getCol()*CONST.SPRITE_SIZE;
		int y = this.getField().getRow()*CONST.SPRITE_SIZE;
		
		g.drawImage(CONST.KEY_IMG, x, y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
	}

}
