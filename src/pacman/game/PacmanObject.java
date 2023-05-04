package pacman.game;

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
			collectKey(staysAt);
			finish(staysAt);
			return true;
		}
		return false;
	}

	public void collectKey(CommonField staysAt) 
	{
		for(int i = 0; i < ((PathField) staysAt).objs.size(); i++) 
		{
			if(((PathField) staysAt).objs.get(i) instanceof KeyObject) 
			{
				System.out.println("Klúč odstránaný " + ((PathField) staysAt).objs.get(i));
				((GeneralMaze)existsIn).removeKey(((PathField) staysAt).objs.get(i));
				System.out.println("key list len " + ((GeneralMaze) existsIn).key_list.size());
			}
		}
	}
	
	public void finish(CommonField staysAt) 
	{
		for(int i = 0; i < ((PathField) staysAt).objs.size(); i++) 
		{
			if(((PathField) staysAt).objs.get(i) instanceof TargetObject) 
			{
				if(((GeneralMaze) existsIn).key_list.size() == 0) 
				{
					System.out.println("Congratulations , you won");
				}
				else 
				{
					System.out.println("Collect all keys , remaining:" + ((GeneralMaze) existsIn).key_list.size());
				}
			}
		}
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
}
