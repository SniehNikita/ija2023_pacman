package ija.ija2022.homework2.tool.common;

public class AbstractObservableField extends Object implements CommonField {
	
	@Override
	public CommonField nextField(Direction dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CommonMazeObject get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(CommonMazeObject obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
