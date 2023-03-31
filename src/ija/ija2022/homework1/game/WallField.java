package ija.ija2022.homework1.game;

import ija.ija2022.homework1.common.MazeObject;

public class WallField extends GeneralField {
	
	public WallField(int i, int j) {
		super(i,j);
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public MazeObject get(){
//		throw new Exception("UnsupportedOperationException");
		return null;
	}

	@Override
	public boolean remove(MazeObject obj) {
//		throw new Exception("UnsupportedOperationException");
		return false;
	}

	@Override
	public boolean put(MazeObject obj) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("UnsupportedOperationException");
	}
	
	public boolean equals(Object obj) {	
		if (obj instanceof WallField) {
			return true;
		} else {
			return false;
		}		
	}
}
