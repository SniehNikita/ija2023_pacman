/**
 * @Author xsnieh00 , xstang03
 * Class used for representing Key objects needed to finish the game
 */
package pacman.game;

import java.awt.Graphics2D;

import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonField.Direction;

public class KeyObject extends GeneralObject {
		
	/**
	 * Object constructor
	 * @param field - at which key is 
	 * @param maze - maze in which key exists
	 */
	public KeyObject(CommonField field, CommonMaze maze) {
		super(field, maze);
	}
		
	/**
	 * keys can't move
	 */
	@Override
	public boolean canMove(Direction r) {
		return false;
	}

	/**
	 * can't move a key
	 */
	@Override
	public boolean move(Direction u) {
		return false;
	}
		
	/**
	 * key's don't have lives
	 */
	@Override
	public int getLives() {
		return 0;
	}

	/**
	 * key is not pacman
	 */
	@Override
	public boolean isPacman() {
		return false;
	}
		
	/**
	 * method used for drawing
	 */
	@Override
	public void draw(Graphics2D g) {
		int x = this.getField().getCol()*CONST.SPRITE_SIZE;
		int y = this.getField().getRow()*CONST.SPRITE_SIZE;
		
		g.drawImage(CONST.KEY_IMG, 
					CONST.GAME_FRAME_START_X + x, 
					CONST.GAME_FRAME_START_Y + y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
	}

}
