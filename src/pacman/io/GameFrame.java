package pacman.io;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import pacman.Main;
import pacman.tools.CONST;
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
	
	public GameFrame(CommonMaze maze) {
		this.rows = maze.numRows();
		this.cols = maze.numCols();
		this.maze = maze;
		frame_width = rows * CONST.SPRITE_SIZE;
		frame_height = cols * CONST.SPRITE_SIZE;
		
		this.setPreferredSize(new Dimension(frame_width, frame_height));
		this.setDoubleBuffered(true);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				maze.getField(i, j).draw(g2d);
			}
		}
		
		g2d.dispose();
	}
	
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
