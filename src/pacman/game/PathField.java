/**
 * @Author xsnieh00 , xstang03
 * PathField represents field of the maze which objects can be placed on
 */
package pacman.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import pacman.tools.CONST;
import pacman.tools.CommonField;
import pacman.tools.CommonMazeObject;

public class PathField extends GeneralField {
	public List<CommonMazeObject> objs;
	private boolean is_visited;

	public PathField(int i, int j) {
		super(i,j);
		is_visited = false;
		objs = new ArrayList<CommonMazeObject>();
	}

	/**
	 * Constructor of the class, sets this field´s row and column
	*/
	public boolean equals(Object obj) {
		if (obj instanceof PathField) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Mark field as visited
	 * @return true if field wasn't visited
	*/
	public boolean collect() {
		if (is_visited) {
			return false;
		} else {
			is_visited = true;
			return true;
		}
	}

	/**
	 * This returns first object currently placed on this field
	 * @return CommonMazeObject on this field
	*/
	@Override
	public CommonMazeObject get() {
		if (objs.isEmpty()) {
			return null;
		} else {		
			return objs.get(0);
	
		}
	}
				
	/**
	 * Removes object from this field
	 * @return success of the operation
	*/
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
		
	/**
	 * checks if any objects are placed on this field
	 * @return true if field is empty
	*/
	@Override
	public boolean isEmpty() {
		return objs.isEmpty();
	}

	/**
	 * puts object on this field
	 * @return success of operation
	*/
	@Override
	public boolean put(CommonMazeObject obj) {
		remove(obj);
		objs.add(obj);
		return true;
	}
		
	/**
	 * checks if object can move to this field
	 * @return True if object can move to this field
	*/
	@Override
	public boolean canMove() {
		return true;
	}

	/**
	 * @return closest CommonField in selected direction
	*/
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
		
	/**
	 * Checks if CommonMazeObject obj is in this field
	 * @return true if object is in this field
	*/
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
	
		
	/**
	 * Draws object on this field
	*/
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(CONST.PATHFIELD_IMG, CONST.GAME_FRAME_START_X + this.getCol() * CONST.SPRITE_SIZE, 
				 	CONST.GAME_FRAME_START_Y + this.getRow() * CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, CONST.SPRITE_SIZE, null);
}
}
