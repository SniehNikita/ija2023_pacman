package pacman.io;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import pacman.Main;
import pacman.game.GeneralMaze;
import pacman.game.PacmanObject;
import pacman.tools.CONST;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMaze;

/*
 * 
 */
@SuppressWarnings("serial")
public class GameFrame extends JPanel {
	private int frame_width;
	private int frame_height;
	
	private CommonMaze maze;
	GameInput key_input;
	
	public GameFrame(CommonMaze maze) {
		this.maze = maze;
		frame_width = CONST.SCREEN_WIDTH;
		frame_height = CONST.SCREEN_HEIGHT;
		
		this.setPreferredSize(new Dimension(frame_width, frame_height));
		this.setDoubleBuffered(true);
    	this.setFocusable(true);
    	
    	key_input = new GameInput();
    	key_input.setSim(false);
    	this.addKeyListener(key_input);
    	
    	if (((GeneralMaze) maze).getRecorder() != null) {
    		((GeneralMaze) maze).getRecorder().setKeyListener(key_input); 
    	}
	}
	
	public GameInput getKeyInput() {
		return key_input;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		maze.draw(g2d);
		
		g2d.dispose();
	}
	
	public void update() {
		for (int i = 0; i < maze.pacmans().size(); i++) {
			if (key_input.isKeyPressed(CONST.UP_KEY)) {
				maze.pacmans().get(0).move(Direction.U);
			} else if (key_input.isKeyPressed(CONST.LEFT_KEY)) {
				maze.pacmans().get(0).move(Direction.L);
			} else if (key_input.isKeyPressed(CONST.DOWN_KEY)) {
				maze.pacmans().get(0).move(Direction.D);
			} else if (key_input.isKeyPressed(CONST.RIGHT_KEY)) {
				maze.pacmans().get(0).move(Direction.R);
			} 
			((PacmanObject) maze.pacmans().get(i)).update();
		}	
		
		for (int i = 0; i < maze.ghosts().size(); i++) {
			maze.ghosts().get(i).move(null);
		}
		
		if (((GeneralMaze)maze).getRecorder() != null) {
			((GeneralMaze)maze).getRecorder().update();
		}
		this.repaint();
	}
	
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
