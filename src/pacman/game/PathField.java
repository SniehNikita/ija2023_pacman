package pacman.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonMazeObject;

public class PathField extends GeneralField {
	public List<CommonMazeObject> objs;

	public PathField(int i, int j) {
		super(i,j);
		objs = new ArrayList<CommonMazeObject>();
	}

	public boolean equals(Object obj) {
		if (obj instanceof PathField) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public CommonMazeObject get() {
		if (objs.isEmpty()) {
			return null;
		} else {		
			return objs.get(0);
	
		}
	}
		
	@Override
	public boolean remove(CommonMazeObject obj) {
		for (int i = 0; i < objs.size(); i++) {
			if (obj == objs.get(i)) {
				objs.remove(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isEmpty() {
		return objs.isEmpty();
	}

	@Override
	public boolean put(CommonMazeObject obj) {
		remove(obj);
		objs.add(obj);
		return true;
	}
	
	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public CommonField nextField(Direction r) {
		switch (r) {
		case D: return maze.getField(row+1, col);
		case L: return maze.getField(row, col-1);
		case R: return maze.getField(row, col+1);
		case U: return maze.getField(row-1, col);
		};
		return null;
	}
	
	public boolean contains(CommonMazeObject obj) 
	{
		for(int i = 0; i < objs.size(); i++) 
		{
			if(objs.get(i) == obj) 
			{
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(CONST.PATHFIELD_IMG, CONST.GAME_FRAME_START_X + this.getCol() * CONST.SPRITE_SIZE, 
				 	CONST.GAME_FRAME_START_Y + this.getRow() * CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
}
}
