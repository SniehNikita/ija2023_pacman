package pacman.game;

import java.awt.Graphics2D;
import pacman.tools.CONST;
import pacman.tools.CommonMazeObject;

public class WallField extends GeneralField{
	
	public WallField(int i, int j) {
		super(i,j);
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public CommonMazeObject get(){
//		throw new Exception("UnsupportedOperationException");
		return null;
	}

	@Override
	public boolean remove(CommonMazeObject obj) {
//		throw new Exception("UnsupportedOperationException");
		return false;
	}
	
	@Override
	public boolean put(CommonMazeObject obj) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("UnsupportedOperationException");
	}
	
	public boolean equals(Object obj) {	
		if (obj instanceof WallField) {
			return true;
		} else {
			return false;
		}		
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(CONST.WALLFIELD_IMG, this.getCol()*64, this.getRow()*64, 64, 64, null);
	}
}
