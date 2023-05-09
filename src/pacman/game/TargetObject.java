package pacman.game;

import java.awt.Graphics2D;

import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonField.Direction;

public class TargetObject extends GeneralObject {
		
	/**
	 * object constructor
	 * @param field - at which target is
	 * @param maze - in which target exists
	 */
	public TargetObject(CommonField field, CommonMaze maze) {
		super(field, maze);
	}
		
	/**
	 * targets can't move
	 */
	@Override
	public boolean canMove(Direction r) {
		return false;
	}

	/**
	 * targets can't be moved
	 */
	@Override
	public boolean move(Direction u) {
		return false;
	}

	/**
	 * targets don't have lives
	 */
	@Override
	public int getLives() {
		return 0;
	}

	/**
	 * target is not pacman
	 */
	@Override
	public boolean isPacman() {
		return false;
	}

	/**
	 * method used to draw
	 */
	@Override
	public void draw(Graphics2D g) {
		int x = this.getField().getCol()*CONST.SPRITE_SIZE;
		int y = this.getField().getRow()*CONST.SPRITE_SIZE;
		
		g.drawImage(CONST.DOOR_IMG, 
					CONST.GAME_FRAME_START_X + x, 
					CONST.GAME_FRAME_START_Y + y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
	}

}
