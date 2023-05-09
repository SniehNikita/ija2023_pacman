/**
 * @Author xsnieh00 , xstang03
 * Main class of this program , runs program and starts the logging of a game
 */

package pacman.game;

import java.awt.Graphics2D;

import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonMazeObject;

public class GeneralField implements CommonField {
	
	protected int row = -1;
	protected int col = -1;
	protected CommonMaze maze = null;
	
	/**
	 * Constructor of the class, sets this field´s row and column
	*/
	public GeneralField(int i, int j) {
		if (i >= 0 && j >= 0) {
			row = i;
			col = j;
			maze = null;
		}
	}


	/**
	 * This returns which row the object is on
	 * @return this object´s row
	*/
	@Override
	public int getRow() {
		return row;
	}

	/**
	 * This returns which column the object is on
	 * @return this object´s column
	*/
	@Override
	public int getCol() {
		return col;
	}

	/**
	 * Puts CommonMazeObject on this Field
	 * @return success of the operation
	*/
	public boolean put(CommonMazeObject obj) {
		return false;
	}
	
	
	/**
	 * Determines if the field is empty
	 * @return true if the field is empty
	*/
	@Override
	public boolean isEmpty() {
		return (row < 0 || col < 0);
	}

	/**
	 * Determines if an object can move into this field
	 * @return true if object can move into this field
	*/
	@Override
	public boolean canMove() {
		return false;
	}

	/**
	 * sets maze for this field
	*/
	public void setMaze(CommonMaze maze) {
		this.maze = maze;		
	}

	/**
	 * Returns CommonField object in selected Direction
	 * @return CommonField
	*/
	@Override
	public CommonField nextField(Direction r) {
		return null;
	}

	/**
	 * Returns maze this field is in
	 * @return CommonMazeObject maze
	*/
	@Override
	public CommonMazeObject get() {
		return (CommonMazeObject) maze;
	}

	/**
	 * Removes object from this field
	 * @return success of operation
	*/
	public boolean remove(CommonMazeObject obj) {
		return false;
	}

	/**
	 * Checks if object is currently in this field
	 * @return success of operation
	*/
	@Override
	public boolean contains(CommonMazeObject obj) {
		return false;
	}

	/**
	 * draws graphics
	*/
	@Override
	public void draw(Graphics2D g2d) {
			
	}
}
