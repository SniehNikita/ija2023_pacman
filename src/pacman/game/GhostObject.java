package pacman.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonMazeObject;
import pacman.tools.CommonField.Direction;

public class GhostObject implements CommonMazeObject {
	private CommonField staysAt = null;
	private CommonMaze existsIn = null;
		
	public GhostObject(CommonField field, CommonMaze maze) {
		staysAt = field;
		existsIn = maze;
	}
	
	@Override
	public boolean canMove(Direction r) {
		CommonField next = staysAt.nextField(r);
		if (next != null && next.canMove()) {
			return true;
		}
		return false;
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
	
	public CommonMaze getMaze() {
		return existsIn;
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
		Image img = null;
		try {
			img = ImageIO.read(new File(CONST.GHOST_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, this.staysAt.getCol()*CONST.SPRITE_SIZE, this.staysAt.getRow()*CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
	}
}
