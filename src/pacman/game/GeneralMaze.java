/**
 * @Author xsnieh00 , xstang03
 * class used to implement a maze
 */

package pacman.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import pacman.GameRunner;
import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonMazeObject;

public class GeneralMaze implements CommonMaze {
	
	private int rows;
	private int cols;
	private GeneralField[][] maze;
	private int livesNumber = 3;

	private List<CommonMazeObject> pacman_list;
	private List<CommonMazeObject> ghost_list;
	public List<CommonMazeObject> key_list;
	public List<CommonMazeObject> target_list;
	
	private MazeRecorder rec;
	private GameRunner gr;
	
	private Stack<Integer> move_seeds;
	
	/**
	 * Constructor for a maze , creates maze with a specified number of rows and columns
	 * @param rows - specified number of rows
	 * @param cols - specified number of columns
	 */
	public GeneralMaze(int rows, int cols) {
		this.maze = new GeneralField[rows][cols];
		this.rows = rows;
		this.cols = cols;
		pacman_list = new ArrayList<CommonMazeObject>();
		ghost_list = new ArrayList<CommonMazeObject>();
		key_list = new ArrayList<CommonMazeObject>();
		target_list = new ArrayList<CommonMazeObject>();
		this.rec = null;
		this.move_seeds = new Stack<Integer>();
	}
	
	/**
	 * adds random seed of ghost movement to seed stack
	 */
	public void addSeed(int move_seed) {
		this.move_seeds.push(move_seed);
	}
	
	/**
	 * gets random seed of ghost movement from seed stack
	 */
	public int getSeed() {
		if (this.move_seeds.size() > 0) {
			return this.move_seeds.pop();	
		} else {
			return -1;
		}
	}
	
	/**
	 * Sets game runner
	 */
	public void setRunner(GameRunner gr) {
		this.gr = gr;
	}
	
	/**
	 * returns
	 */
	public GameRunner getRunner() {
		return this.gr;
	}
	
	/**
	 * sets logging object
	 */
	
	public void setRecorder(MazeRecorder rec) {
		this.	rec = rec;
	}
	
	
	/**
	 * removes logging object
	 */
	public void removeRecorder() {
		this.rec = null;
	}
	
	
	/**
	 * @return returns object for logging
	 */
	public MazeRecorder getRecorder() {
		return this.rec;
	}
	
	/**
	 * @return CommonField at specified row and collumn
	 * @param i - specified row
	 * @param c - specified column
	 */
	@Override
	public CommonField getField(int i, int c) {
		if (i < 0 || c < 0 || i > rows-1 || c > cols-1) {
			return null;
		}
		return maze[i][c];
	}

	/**
	 * creates new list for storing ghost objects
	 */
	@Override
	public List<CommonMazeObject> ghosts() {
		return new ArrayList<CommonMazeObject>(ghost_list);
	}

	/**
	 * creates new list for storing pacman objects
	 */
	@Override
	public List<CommonMazeObject> pacmans() {
		return new ArrayList<CommonMazeObject>(pacman_list);
	}

	/**
	 * creates new list for storing key objects
	 */
	@Override
	public List<CommonMazeObject> keys() {
		return new ArrayList<CommonMazeObject>(key_list);
	}

	/**
	 * creates new list for storing target objects
	 */
	@Override
	public List<CommonMazeObject> targets() {
		return new ArrayList<CommonMazeObject>(target_list);
	}

	/**
	 * @return number of columns of this maze
	 */
	@Override
	public int numCols() {
		return cols;
	}

	/**
	 * @return number of rows of this maze
	 */
	@Override
	public int numRows() {
		return rows;
	}

	/**
	 * spawns pacman in this maze at specified coordinates
	 * @param r - specified row
	 * @param c - specified column
	 * @return success of operation
	 */
	public boolean spawnPacman(int r, int c) {
		PacmanObject pacman = new PacmanObject(getField(r,c), this, livesNumber);
		pacman_list.add(pacman);
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		this.maze[r][c].put(pacman);
		return true;
	}
	
	/**
	 * spawns ghost in this maze at specified coordinates
	 * @param r - specified row
	 * @param c - specified column
	 * @return success of operation
	 */
	public boolean spawnGhost(int r, int c) {
		GhostObject ghost = new GhostObject(getField(r,c), this, this.ghost_list.size());	
		ghost_list.add(ghost);	
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		this.maze[r][c].put(ghost);
		return true;
	}
	
	/**
	 * spawns key in this maze at specified coordinates
	 * @param r - specified row
	 * @param c - specified column
	 * @return success of operation
	 */
	public boolean spawnKey(int r, int c) 
	{
		KeyObject key = new KeyObject(getField(r,c), this);
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		key_list.add(key);
		this.maze[r][c].put(key);
		return true;
	}
	
	/**
	 * removes specified key from this maze 
	 * @param key - key object to be removed
	 * @return success of operation
	 */
	public boolean removeKey(CommonMazeObject key) 
	{
		for(int i = 0; i < key_list.size(); i++) 
		{
			if(key_list.get(i) == key) 
			{
				key_list.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * spawns target in this maze at specified coordinates
	 * @param r - specified row
	 * @param c - specified column
	 * @return success of operation
	 */
	public boolean spawnTarget(int r, int c) 
	{
		TargetObject target = new TargetObject(getField(r,c), this);
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		target_list.add(target);
		this.maze[r][c].put(target);
		return true;
	}
	
	/**
	 * spawns WallField in this maze at specified coordinates
	 * @param r - specified row
	 * @param c - specified column
	 * @return success of operation
	 */
	public boolean putWall(int r, int c) {
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		WallField wall_field = new WallField(r, c);		
		this.maze[r][c] = wall_field;
		((GeneralField) getField(r,c)).setMaze(this);
		return true;
	}
	
	/**
	 * spawns PathField in this maze at specified coordinates
	 * @param r - specified row
	 * @param c - specified column
	 * @return success of operation
	 */
	public boolean putPath(int r, int c) {
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		PathField path_field = new PathField(r, c);		
		this.maze[r][c] = path_field;
		((GeneralField) getField(r,c)).setMaze(this);
		return true;
	}
	
	/**
	 * initiates field at specified coordinates
	 * @param r - specified row
	 * @param c - specified column
	 * @return success of operation
	 */
	public boolean initField(int r, int c) {
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}	
		this.maze[r][c] = null;
		return true;
	}

	/**
	 * draws objects in maze
	 */
	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < this.numRows(); i++) {
			for (int j = 0; j < this.numCols(); j++) {
				this.getField(i,j).draw(g);
			}
		}
		for (int i = 0; i < this.keys().size(); i++) {
			this.keys().get(i).draw(g);
		}
		for (int i = 0; i < this.targets().size(); i++) {
			this.targets().get(i).draw(g);
		}
		for (int i = 0; i < this.pacmans().size(); i++) {
			this.pacmans().get(i).draw(g);
		}
		for (int i = 0; i < this.ghosts().size(); i++) {
			this.ghosts().get(i).draw(g);
		}		
	}

	/**
	 * moves pacman to a specified field
	 * @param pacman - pacman object
	 * @param field - specified field
	 */
	@Override
	public void movePacman(CommonMazeObject pacman, CommonField field) {	
		((PathField) pacman.getField()).remove(pacman);
		((PathField) field).put(pacman);
		((GeneralObject) pacman).setField(field);
	}
	
	/**
	 * removes pacman from this maze
	 * @param pacman object to be removed
	 */	
	public void removePacman(CommonMazeObject pacman) {	
		for (int i = 0; i < this.pacmans().size(); i++) {
			if (this.pacman_list.get(i) == pacman) {
				this.pacman_list.remove(i);
			}
		}
		
		if (this.pacmans().size() == 0) {
			System.out.printf("Game over\n");
		}
	}

	/**
	 * Removes key from a specified field
	 * @param field - specified fields
	 */
	public void collectKey(CommonField field) {
		for (int i = 0; i < this.keys().size(); i++) {
			if (this.key_list.get(i).getField() == field) {
				this.key_list.remove(i);
			}
		}		
	}

	/**
	 * Checks if pacman collected all the keys upon reaching target location
	 * if not , user is prompted to collect all keys before finishing the game
	 * @param pacman object
	 * @param target - target field
	 */
	public void reachTarget(CommonMazeObject pacman, CommonMazeObject target) {
		if (this.key_list.size() == 0) {
			int score = ((PacmanObject)this.pacman_list.get(0)).getScore();
			for (int i = 0; i < this.pacmans().size(); i++) {
				if (this.pacman_list.get(i) == pacman) {
					this.pacman_list.remove(i);
				}
			}
			if (this.getRecorder() != null) {
				this.getRecorder().stop();
			}
			this.gr.end(true, score);
		} else {
			System.out.printf("Collect all keys\n");			
		}
	}
	
}
