package pacman.game;

import java.awt.Graphics2D;
import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMaze;
import pacman.tools.Direct;

public class PacmanObject extends GeneralObject {

	private int lives;
		
	public PacmanObject(CommonField field, CommonMaze maze, int lives) {
		super(field, maze);
		this.lives = lives;
	}
	
	@Override
	public boolean canMove(Direction r) {
		CommonField next = this.getField().nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
	}
	
	public void hit() {
		lives = lives - 1;
		if (lives == 0) {
//			GGWP
		}
	}
	


	@Override
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
			collectKey(this.getField());
			finish(this.getField());
		
			if (pos_offset < 0) {
				pos_offset++;
			} else {
				pos_offset--;
			}
		}
			
		last_direction = d;
		return true;
//		if (!this.canMove(u)) {
//			if (this.canMove(last_direction)) {
//				u = last_direction;
//			} else {
//				return false;
//			}
//		} else {
//			if ( ( ( (u == Direction.L || u == Direction.R) && (last_direction == Direction.U || last_direction == Direction.D) ) ||
//				   ( (u == Direction.U || u == Direction.D) && (last_direction == Direction.L || last_direction == Direction.L) ) ) &&
//					Math.abs(pos_offset) > 0.05) {
//				u = last_direction;
//			}
//		}
//		
//		if (u == Direction.D || u == Direction.R) { 
//			pos_offset += (float)CONST.PACMAN_SPEED / (float)CONST.FPS;
//		} else {
//			pos_offset -= (float)CONST.PACMAN_SPEED / (float)CONST.FPS;	
//		}
//		
//		if (Math.abs(pos_offset) >= 0.5) {
//			((PathField) this.getField()).remove(this);
//			this.setField(this.getField().nextField(u));
//			((PathField) this.getField()).put(this);
//			collectKey(this.getField());
//			finish(this.getField());
//		
//			if (pos_offset < 0) {
//				pos_offset++;
//			} else {
//				pos_offset--;
//			}
//		}
//		
//		last_direction = u;
//		return true;
	}

	public void collectKey(CommonField field) 
	{
		for(int i = 0; i < ((PathField) field).objs.size(); i++) 
		{
			if(((PathField) field).objs.get(i) instanceof KeyObject) 
			{
				System.out.println("Klúč odstránaný " + ((PathField) field).objs.get(i));
				((GeneralMaze)this.getMaze()).removeKey(((PathField) field).objs.get(i));
				System.out.println("key list len " + ((GeneralMaze) this.getMaze()).key_list.size());
			}
		}
	}
	
	public void finish(CommonField field) 
	{
		for(int i = 0; i < ((PathField) field).objs.size(); i++) 
		{
			if(((PathField) field).objs.get(i) instanceof TargetObject) 
			{
				if(((GeneralMaze) this.getMaze()).key_list.size() == 0) 
				{
					System.out.println("Congratulations , you won");
				}
				else 
				{
					System.out.println("Collect all keys , remaining:" + ((GeneralMaze) this.getMaze()).key_list.size());
				}
			}
		}
	}
	
	@Override
	public boolean isPacman() {
		return true;
	}

	@Override
	public int getLives() {
		return this.lives;
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
		
		g.drawImage(CONST.PACMAN_IMG, x, y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
	}
}
