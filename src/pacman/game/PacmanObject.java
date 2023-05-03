package pacman.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMaze;
import pacman.tools.CommonMazeObject;

public class PacmanObject implements CommonMazeObject {

	private CommonField staysAt = null;
	private CommonMaze existsIn = null;
	private int lives;
		
	public PacmanObject(CommonField field, CommonMaze maze, int lives) {
		staysAt = field;
		existsIn = maze;
		this.lives = lives;
	}
	
	@Override
	public boolean canMove(Direction r) {
		CommonField next = staysAt.nextField(r);
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
	public boolean move(Direction u) {
		if (this.canMove(u)) {
			((PathField) staysAt).remove(this);
			staysAt = staysAt.nextField(u);
			((PathField) staysAt).put(this);
			return true;
		}
		return false;
	}

	@Override
	public CommonField getField() {
		return staysAt;
	}

	@Override
	public boolean isPacman() {
		return true;
	}

	@Override
	public int getLives() {
		return this.lives;
	}

	public CommonMaze getMaze() {
		return existsIn;
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		Image img = null;
		try {
			img = ImageIO.read(new File(CONST.PACMAN_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, this.staysAt.getCol()*CONST.SPRITE_SIZE, this.staysAt.getRow()*CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
	}
}
