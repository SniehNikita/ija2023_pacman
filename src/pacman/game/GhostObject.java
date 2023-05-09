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
		
	private int ghost_num;
	private int move_seed;
	private int move_num;
	
	/**
	 * constructor of method
	 * @param field at which ghost currently is 
	 * @param maze in which ghosts exists
	 * @param ghost_num number of ghosts
	 */
	public GhostObject(CommonField field, CommonMaze maze, int ghost_num) {
		super(field, maze);
		this.ghost_num = ghost_num;
		move_seed = ((GeneralMaze) this.getMaze()).getSeed();
		if (move_seed == -1) {
			move_seed = this.getRandomNumber(10000000, 99999999);
		}
		move_num = move_seed % 10 + 1;
	}
	
	/**
	 * sets direction for ghost to move
	 * @param seed
	 */
	public void setDirectionSeed(int seed) {
		this.move_seed = seed;
		this.move_num = move_seed % 10 + 1;
	}
	
	/**
	 * checks if ghost can move in specific direction
	 * @param r - direction to move
	 */
	@Override
	public boolean canMove(Direction r) {
		CommonField next = this.getField().nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
	}

	/**
	 * moves ghost in specified direction
	 * @param d direction for ghost to move to
	 */
	@Override
	public boolean move(Direction d) {
		d = this.chooseDirection(d);
		return super.move(d);
	}
		
	/**
	 * chooses a direction in which the ghost will move
	 * @param d
	 * @return Direction in which the ghost will next move
	 */
	private Direction chooseDirection(Direction d) {
		List<Direction> d_list = new ArrayList<Direction>();
		if (d == null) {
			d = last_direction;
		}
		if (pos_offset < 0.1 && pos_offset > -0.1) {
			if (this.canMove(Direct.left(d))) {
				d_list.add(Direct.left(d));
			}
			if (this.canMove(Direct.right(d))) {
				d_list.add(Direct.right(d));
			}
		}
		if (this.canMove(d) || pos_offset != 0) {
			d_list.add(d);
		}
		

		if (d_list.size() == 0) {
			return Direct.back(d);
		} else if (d_list.size() == 1) {
			return d_list.get(0);
		} else {
			int seed = (move_seed / move_num) % 10;
			move_num = (move_num + 11) % 10000000 + 1;
			if (((GeneralMaze) this.getMaze()).getRecorder() != null) {
				((GeneralMaze) this.getMaze()).getRecorder().ghostMovementSeed(ghost_num, move_seed);
			}
			return d_list.get(seed & (d_list.size() - 1));
		}
	}
		
	/**
	 * returns false becaus object is a ghost
	 */
	@Override
	public boolean isPacman() {
		return false;
	}

	/**
	 * returns 0 because ghosts don't have lives
	 */
	@Override
	public int getLives() {
		// TODO Exception
		return 0;
	}
		
	/**
	 * gets random number to help with choosing movement direction
	 * @param min - minimal number to choose from
	 * @param max - maximal number to choose from
	 * @return returns random number from specified range
	 */
	private int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
		
	/**
	 * method for drawing ghosts on game screen
	 */
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
		
//		if (this.last_direction == Direction.R || this.last_direction == Direction.U) {
//		    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
//		    tx.translate(-CONST.SPRITE_SIZE, 0);
//		    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//		    
//			g.drawImage(op.filter((BufferedImage) CONST.GHOST_IMG, null), 
//						CONST.GAME_FRAME_START_X + x, 
//						CONST.GAME_FRAME_START_Y + y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);			
//		} else {
//			g.drawImage(CONST.GHOST_IMG,
//						CONST.GAME_FRAME_START_X + x, 
//						CONST.GAME_FRAME_START_Y + y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);		
//		}
		g.drawImage(CONST.GHOST_IMG,
				CONST.GAME_FRAME_START_X + x, 
				CONST.GAME_FRAME_START_Y + y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);	
	}
}
