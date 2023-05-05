package pacman.io;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import pacman.Main;
import pacman.tools.CONST;
import pacman.tools.CommonField.Direction;
import pacman.tools.CommonMaze;
import pacman.tools.io.CommonPresenter;

/*
 * 
 */
@SuppressWarnings("serial")
public class GameFrame extends JPanel implements CommonPresenter {
	private int frame_width;
	private int frame_height;
	
	private int rows;
	private int cols;
	
	private CommonMaze maze;
	KeyboardInput key_input;
	
	public GameFrame(CommonMaze maze) {
		this.rows = maze.numRows();
		this.cols = maze.numCols();
		this.maze = maze;
		frame_width = cols * CONST.SPRITE_SIZE;
		frame_height = rows * CONST.SPRITE_SIZE;
		
		this.setPreferredSize(new Dimension(frame_width, frame_height));
		this.setDoubleBuffered(true);
    	this.setFocusable(true);
    	
    	key_input = new KeyboardInput();
    	this.addKeyListener(key_input);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		maze.draw(g2d);
		
		g2d.dispose();
	}
	
	public void update() {		
		if (key_input.isKeyPressed('W')) {
			maze.pacmans().get(0).move(Direction.U);
		} else if (key_input.isKeyPressed('A')) {
			maze.pacmans().get(0).move(Direction.L);
		} else if (key_input.isKeyPressed('S')) {
			maze.pacmans().get(0).move(Direction.D);
		} else if (key_input.isKeyPressed('D')) {
			maze.pacmans().get(0).move(Direction.R);
		} 
		for (int i = 0; i < maze.ghosts().size(); i++) {
			maze.ghosts().get(i).move(null);
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
