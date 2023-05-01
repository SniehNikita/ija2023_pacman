package ija.ija2022.homework2.game;

import java.util.ArrayList;
import java.util.List;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.tool.common.Observable;

public class GeneralField implements CommonField {
	
	protected int row = -1;
	protected int col = -1;
	protected CommonMaze maze = null;
	
	private List<Observable.Observer> observers;
	
	public GeneralField(int i, int j) {
		if (i >= 0 && j >= 0) {
			row = i;
			col = j;
			maze = null;
			observers = new ArrayList<Observable.Observer>();
		} else {
			// TODO ERRHANDLE
		}
	}

	public boolean put(CommonMazeObject obj) {
//		throw new Exception("UnsupportedOperationException");
		return false;
	}
	
	@Override
	public boolean isEmpty() {
		return (row < 0 || col < 0);
	}

	@Override
	public boolean canMove() {
		return false;
	}

	public void setMaze(CommonMaze maze) {
		this.maze = maze;		
	}

	@Override
	public CommonField nextField(Direction r) {
		return null;
	}

	@Override
	public CommonMazeObject get() {
		return (CommonMazeObject) maze;
	}

	public boolean remove(CommonMazeObject obj) {
		return false;
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);		
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).update(this);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		for (int i = 0; i < observers.size(); i++) {
			if (o.equals(observers.get(i))) {
				observers.remove(i);
			}
		}		
	}

	@Override
	public boolean contains(CommonMazeObject obj) {
		return false;
	}

}
