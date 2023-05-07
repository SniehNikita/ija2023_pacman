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
	
	public GhostObject(CommonField field, CommonMaze maze, int ghost_num) {
		super(field, maze);
		this.ghost_num = ghost_num;
		move_seed = ((GeneralMaze) this.getMaze()).getSeed();
		if (move_seed == -1) {
			move_seed = this.getRandomNumber(10000000, 99999999);
		}
		move_num = move_seed % 10 + 1;
	}
	
	public void setDirectionSeed(int seed) {
		this.move_seed = seed;
		this.move_num = move_seed % 10 + 1;
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
		return super.move(d);
	}
	
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
	
	@Override
	public boolean isPacman() {
		return false;
	}

	@Override
	public int getLives() {
		// TODO Exception
		return 0;
	}
	
	private int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
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
