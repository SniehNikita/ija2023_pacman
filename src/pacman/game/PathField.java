package pacman.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

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
	
	private boolean isGhost() {
		for (int i = 0; i < objs.size(); i++) {
			if (objs.get(i) instanceof GhostObject) {
				return true;
			}
		}
		return false;
	}
	
	private void checkCollisions() {
		if (isGhost()) {
			for (int i = 0; i < objs.size(); i++) {
				if (objs.get(i) instanceof PacmanObject) {
					PacmanObject p = (PacmanObject) objs.get(i);
					p.hit();
				}
			}
		}
	}
	
	
	@Override
	public boolean isEmpty() {
		return objs.isEmpty();
	}

	@Override
	public boolean put(CommonMazeObject obj) {
		remove(obj);
		objs.add(obj);
		checkCollisions();
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
		Image img = null;
		try {
			img = ImageIO.read(new File(CONST.PATHFIELD_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, this.getCol()*CONST.SPRITE_SIZE, this.getRow()*CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
		for (int i = 0; i < objs.size(); i++) {
			objs.get(i).draw(g);
		}
	}
}
