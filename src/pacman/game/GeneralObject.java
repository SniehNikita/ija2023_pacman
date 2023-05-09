/**
 * @Author xsnieh00 , xstang03
 * Class used to describe objects which can be placed on game fields
 */
package pacman.game;

import java.awt.Graphics2D;

import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMazeObject;
import pacman.tools.Direct;

public class GeneralObject implements CommonMazeObject {
	
	private CommonField pos_field;
	private CommonMaze pos_maze;
	protected float pos_offset = 0;
	protected Direction last_direction;
	
	/**
	 * constructor method
	 * @param field - current location of object
	 * @param maze - maze in which object exists
	 */
	public GeneralObject(CommonField field, CommonMaze maze) {
		pos_field = field;
		pos_maze = maze;
		pos_offset = 0;
		last_direction = Direction.U;
	}	
	
	/**
	 * checks if object can move in specified direction
	 */
	public boolean canMove(Direction r) {		
		CommonField next = this.getField().nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
	}
	
	/**
	 * sets position of this object to specified field
	 */	
	public void setField(CommonField field) {
		this.pos_field = field;
	}
	
	/**
	 * gets field at which object currently is
	 */
	public CommonField getField() {
		return pos_field;
	}
	
	/**
	 * @return maze in which objects exists
	 */
	public CommonMaze getMaze() {
		return pos_maze;
	}
	
	/**
	 * only pacman object has lives
	 */
	public int getLives() {
		return 0;
	}

	/**
	 * moves object in target direction
	 */
	public boolean move(Direction d) {
		Direction l = last_direction;
		float offset = (float)CONST.PACMAN_SPEED / (float)CONST.FPS;
		
		if (this.canMove(d) && (Direct.angle(d,l) == 0 || Direct.angle(d,l) == 180)) {
			// If can move and doesn't change direction or go opposite
			
			if (d == Direction.D || d == Direction.R) { 
				pos_offset += offset;
			} else {
				pos_offset -= offset;	
			}
		} else if (!this.canMove(d) && (Direct.angle(d,l) == 0 || Direct.angle(d,l) == 180)) {
			// If can't move and doesn't change direction or go opposite
			
			if (d == Direction.D || d == Direction.R) { 
				if (pos_offset + offset < -0.001) {
					pos_offset += offset;
				} else {
					pos_offset = 0;
				}
			} else if (d == Direction.L || d == Direction.U) {
				if (pos_offset - offset > 0.001) {
					pos_offset -= offset;
				} else {
					pos_offset = (float) 0;
				}
			}
			
			return false;
		} else if (this.canMove(d) && Direct.angle(d,l) == 90) {
			// If can move and turns
			
			if (Math.abs(pos_offset) < 0.02) {
				// Can turn
				
				if (d == Direction.D || d == Direction.R) { 
					pos_offset = offset;
				} else {
					pos_offset = -offset;	
				}
			} else {
				// Go to the center
				
				d = l;
				if (d == Direction.D || d == Direction.R) { 
					pos_offset += offset;
				} else {
					pos_offset -= offset;	
				}
			}
		} else if (!this.canMove(d) && Direct.angle(d,l) == 90) {
			// Can't turn -> go previous direction
			d = l;
			if (this.canMove(d)) {
				if (d == Direction.D || d == Direction.R) { 
					pos_offset += offset;
				} else {
					pos_offset -= offset;	
				}
			} else {
				return false;
			}
		}
		
		if (Math.abs(pos_offset) >= 0.5) {
			((PathField) this.getField()).remove(this);
			this.setField(this.getField().nextField(d));
			((PathField) this.getField()).put(this);
		
			if (pos_offset < 0) {
				pos_offset++;
			} else {
				pos_offset--;
			}
		}
			
		last_direction = d;
		return true;
	}

	/**
	 * returns X position of object
	 */
	public float getX() {
		if (this.last_direction == Direction.D || this.last_direction == Direction.R) {
			return this.getField().getCol() + pos_offset;
		} else {
			return this.getField().getCol();
		}
	}

	/**
	 * returns Y position of object
	 */
	public float getY() {
		if (this.last_direction == Direction.D || this.last_direction == Direction.R) {
			return this.getField().getRow() + pos_offset;
		} else {
			return this.getField().getRow();
		}
	}

	/**
	 * checks for collisions with different objects
	 */
	@Override
	public boolean checkCollision(CommonMazeObject obj) {
		float x1 = this.getX();
		float x2 = obj.getX();
		float y1 = this.getY();
		float y2 = obj.getY();
		
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2)) < 0.5;
	}

	/**
	 * checks if object is pacman
	 */
	@Override
	public boolean isPacman() {
		// TODO Auto-generated method stub
		return false;
	}
		
	/**
	 * method used for drawing
	 */
	@Override
	public void draw(Graphics2D g) {
		
	}
}
