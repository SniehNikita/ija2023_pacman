package pacman.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import pacman.tools.CommonField;
import pacman.tools.CommonMaze;
import pacman.tools.CommonMazeObject;
import pacman.tools.CommonRandom;

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
	private CommonRandom random;
	
	private Stack<Integer> move_seeds;
	
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
	
	public void addSeed(int move_seed) {
		this.move_seeds.push(move_seed);
	}
	
	public int getSeed() {
		if (this.move_seeds.size() > 0) {
			return this.move_seeds.pop();	
		} else {
			return -1;
		}
	}
	
	public void setRandomProvider(CommonRandom random) {
		this.random = random;
	}
	
	public int getRandomNumber(int min, int max) {
		return random.getRandomNumber(min, max);
	}
	
	public void setRecorder(MazeRecorder rec) {
		this.rec = rec;
	}
	
	public void removeRecorder() {
		this.rec = null;
	}
	
	public MazeRecorder getRecorder() {
		return this.rec;
	}
	
	@Override
	public CommonField getField(int i, int c) {
		if (i < 0 || c < 0 || i > rows-1 || c > cols-1) {
			return null;
		}
		return maze[i][c];
	}

	@Override
	public List<CommonMazeObject> ghosts() {
		return new ArrayList<CommonMazeObject>(ghost_list);
	}

	@Override
	public List<CommonMazeObject> pacmans() {
		return new ArrayList<CommonMazeObject>(pacman_list);
	}

	@Override
	public List<CommonMazeObject> keys() {
		return new ArrayList<CommonMazeObject>(key_list);
	}

	@Override
	public List<CommonMazeObject> targets() {
		return new ArrayList<CommonMazeObject>(target_list);
	}

	@Override
	public int numCols() {
		return cols;
	}

	@Override
	public int numRows() {
		return rows;
	}

	public boolean spawnPacman(int r, int c) {
		PacmanObject pacman = new PacmanObject(getField(r,c), this, livesNumber);
		pacman_list.add(pacman);
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		this.maze[r][c].put(pacman);
		return true;
	}
	
	public boolean spawnGhost(int r, int c) {
		GhostObject ghost = new GhostObject(getField(r,c), this, this.ghost_list.size());	
		ghost_list.add(ghost);	
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		this.maze[r][c].put(ghost);
		return true;
	}
	
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
	
	public boolean putWall(int r, int c) {
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		WallField wall_field = new WallField(r, c);		
		this.maze[r][c] = wall_field;
		((GeneralField) getField(r,c)).setMaze(this);
		return true;
	}
	
	public boolean putPath(int r, int c) {
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}
		PathField path_field = new PathField(r, c);		
		this.maze[r][c] = path_field;
		((GeneralField) getField(r,c)).setMaze(this);
		return true;
	}
	
	public boolean initField(int r, int c) {
		if (r < 0 || c < 0 || r > rows-1 || c > cols-1) {
			return false;
		}	
		this.maze[r][c] = null;
		return true;
	}

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

	@Override
	public void movePacman(CommonMazeObject pacman, CommonField field) {	
		((PathField) pacman.getField()).remove(pacman);
		((PathField) field).put(pacman);
		((GeneralObject) pacman).setField(field);
	}
	
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

	public void collectKey(CommonField field) {
		for (int i = 0; i < this.keys().size(); i++) {
			if (this.key_list.get(i).getField() == field) {
				this.key_list.remove(i);
			}
		}		
	}

	public void reachTarget(CommonMazeObject pacman, CommonMazeObject target) {
		if (this.key_list.size() == 0) {
			for (int i = 0; i < this.pacmans().size(); i++) {
				if (this.pacman_list.get(i) == pacman) {
					this.pacman_list.remove(i);
				}
			}
			if (this.getRecorder() != null) {
				this.getRecorder().stop();
			}
			System.out.printf("Win Win\n");
		} else {
			System.out.printf("Collect all keys\n");			
		}
	}
	
}
