package ija.ija2022.homework2.tool.common;

public interface CommonField extends Observable {

	// TODO Maybe another better implementation
	public static enum Direction {
		D, L, R, U;
		
		int deltaCol() {
			return 0;
		}

		int deltaRow() {
			return 0;
		}
		
	}

	boolean canMove();
	
	boolean contains(CommonMazeObject obj);
	
	CommonMazeObject get();
	
	boolean isEmpty();
	
	CommonField nextField(CommonField.Direction dir);

}
