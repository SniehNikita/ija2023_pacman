package pacman.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.Direct;
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
	public boolean move(Direction d) {
		d = this.chooseDirection(d);
		Direction l = last_direction;
		float offset = (float)CONST.GHOST_SPEED / (float)CONST.FPS;
		
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
	
	private Direction chooseDirection(Direction d) {
		List<Direction> d_list = new ArrayList<Direction>();
		if (d == null) {
			d = last_direction;
		}
		if (this.canMove(Direct.left(d))) {
			d_list.add(Direct.left(d));
		}
		if (this.canMove(Direct.right(d))) {
			d_list.add(Direct.right(d));
		}
		if (this.canMove(d) || pos_offset != 0) {
			d_list.add(d);
		}
		
		if (d_list.size() == 0) {
			return Direct.back(d);
		} else {
			return d_list.get(this.getRandomNumber(0, d_list.size()));
		}
	}
	
	private int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
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
		int x = this.getField().getCol()*CONST.SPRITE_SIZE;
		int y = this.getField().getRow()*CONST.SPRITE_SIZE;
		
		if (last_direction == Direction.D) {
			y += pos_offset * (float)CONST.SPRITE_SIZE;
		} else if (last_direction == Direction.U) { 
			y += pos_offset * (float)CONST.SPRITE_SIZE;
		} else if (last_direction == Direction.L) {
			x += pos_offset * (float)CONST.SPRITE_SIZE;
		} else if (last_direction == Direction.R) { 
			x += pos_offset * (float)CONST.SPRITE_SIZE;
		}
		
		g.drawImage(CONST.GHOST_IMG, x, y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
	}
}
