package pacman.tools;

public interface CommonField {

	public enum Direction {
		U, L, D, R
	}

	boolean isEmpty();

	boolean canMove();

	CommonField nextField(Direction r);

	CommonMazeObject get();

	boolean contains(CommonMazeObject obj);

}
