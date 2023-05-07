package pacman.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameInput implements KeyListener {
	private boolean[] keys_pressed = new boolean['Z'-'A'+1];
	private boolean is_sim = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		if (Character.toUpperCase(key) >= 'A' && Character.toUpperCase(key) <= 'Z' && !is_sim) {
			keys_pressed[Character.toUpperCase(key) - 'A'] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		if (Character.toUpperCase(key) >= 'A' && Character.toUpperCase(key) <= 'Z' && !is_sim) {
			keys_pressed[Character.toUpperCase(key) - 'A'] = false;
		}		
	}
	
	public void setSim(boolean state) {
		this.is_sim = state;
	}
	
	public void setKeyPressed(char key) {
		if (Character.toUpperCase(key) >= 'A' && Character.toUpperCase(key) <= 'Z') {
			keys_pressed[Character.toUpperCase(key) - 'A'] = true;
		}			
	}

	public void setKeyReleased(char key) {
		if (Character.toUpperCase(key) >= 'A' && Character.toUpperCase(key) <= 'Z') {
			keys_pressed[Character.toUpperCase(key) - 'A'] = false;
		}		
	}
	
	public boolean isKeyPressed(char key) {
		return keys_pressed[Character.toUpperCase(key) - 'A'];
	}
}
