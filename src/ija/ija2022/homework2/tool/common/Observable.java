package ija.ija2022.homework2.tool.common;

public interface Observable {
	
	public static interface Observer {
		
		void update(Observable o);
	
	}
	
	void addObserver(Observable.Observer o);
	
	void notifyObservers();

	void removeObserver(Observable.Observer o);
	
}
