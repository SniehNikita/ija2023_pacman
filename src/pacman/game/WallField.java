/**
 * @Author xsnieh00 , xstang03
 * WallField represents wall object in maze
 */
package pacman.game;

import java.awt.Graphics2D;
import pacman.tools.CONST;
import pacman.tools.CommonMazeObject;

public class WallField extends GeneralField{
		
	/**
	 * Constructor of the class, sets this field´s row and column
	*/
	public WallField(int i, int j) {
		super(i,j);
	}
		
	/**
	 * @return always true, objects can´t be placed in walls
	*/
	@Override
	public boolean isEmpty() {
		return true;
	}

	/**
	 * @return null , objects can´t be in wall
	*/
	@Override
	public CommonMazeObject get(){
//		throw new Exception("UnsupportedOperationException");
		return null;
	}

	/**
	 * @return false can´t remove objects from wall
	*/
	@Override
	public boolean remove(CommonMazeObject obj) {
//		throw new Exception("UnsupportedOperationException");
		return false;
	}
		
	/**
	 * @return false , can´t put objects in wall
	*/
	@Override
	public boolean put(CommonMazeObject obj) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("UnsupportedOperationException");
	}
		
	/**
	 * @return true if object is instance of wallfield, false otherwise
	*/
	public boolean equals(Object obj) {	
		if (obj instanceof WallField) {
			return true;
		} else {
			return false;
		}		
	}
	
		
	/**
	 * Draws a wall field on game screen
	*/
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(CONST.WALLFIELD_IMG, CONST.GAME_FRAME_START_X + this.getCol() * CONST.SPRITE_SIZE, 
										 CONST.GAME_FRAME_START_Y + this.getRow() * CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
	}
}
