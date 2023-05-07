package pacman.game;

import java.awt.Graphics2D;
import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMaze;

public class PacmanObject extends GeneralObject {

	private int lives;
	private int effect_invincible;
	private CommonField start_field;
		
	public PacmanObject(CommonField field, CommonMaze maze, int lives) {
		super(field, maze);
		this.lives = lives;
		
		start_field = field;
		effect_invincible = CONST.FPS * 2;
	}
	
	@Override
	public boolean canMove(Direction r) {
		CommonField next = this.getField().nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
	}

	public void collectKey(CommonField field) {
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
	
	public void die() {
		lives = lives - 1;
		System.out.printf("Hit!\n");		
		if (lives > 0) {
			this.getMaze().spawnGhost(this.getField().getRow(), this.getField().getCol());
			this.getMaze().movePacman(this, start_field);
			pos_offset = 0;
		} else {
			((GeneralMaze) this.getMaze()).removePacman(this);	
			((GeneralMaze) this.getMaze()).getRunner().end(false);
			if (((GeneralMaze) this.getMaze()).getRecorder() != null) {
				((GeneralMaze) this.getMaze()).getRecorder().stop();
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
	
	public void update() {
		for (int i = 0; i < this.getMaze().ghosts().size(); i++) {
			if (this.checkCollision(this.getMaze().ghosts().get(i))) {
				if (effect_invincible == 0) {
					this.die();
					effect_invincible = 90;
				}
			}
		}
		
		for (int i = 0; i < this.getMaze().keys().size(); i++) {
			if (this.checkCollision(this.getMaze().keys().get(i))) {
				((GeneralMaze) this.getMaze()).collectKey(this.getField());
			}
		}
		for (int i = 0; i < this.getMaze().targets().size(); i++) {
			if (this.checkCollision(this.getMaze().targets().get(i))) {
				((GeneralMaze) this.getMaze()).reachTarget(this, this.getMaze().targets().get(i));
			}
		}
		
		if (effect_invincible > 0) {
			effect_invincible--;
		}
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
		
//		if (this.last_direction == Direction.L || this.last_direction == Direction.D) {
//		    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
//		    tx.translate(-CONST.SPRITE_SIZE, 0);
//		    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//		    
//			g.drawImage(op.filter((BufferedImage) CONST.PACMAN_IMG, null), 
//						CONST.GAME_FRAME_START_X + x, 
//						CONST.GAME_FRAME_START_Y + y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);			
//		} else {
//			g.drawImage(CONST.PACMAN_IMG,
//						CONST.GAME_FRAME_START_X + x, 
//						CONST.GAME_FRAME_START_Y + y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);		
//		}
		
		g.drawImage(CONST.PACMAN_IMG,
		CONST.GAME_FRAME_START_X + x, 
		CONST.GAME_FRAME_START_Y + y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);		
		
		for (int i = 0; i < this.getLives(); i++) {
			g.drawImage(CONST.HEART_IMG, 
						CONST.GAME_FRAME_START_X + CONST.SPRITE_SIZE * i, 
						CONST.GAME_FRAME_START_Y, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);			
		}
	}
}
