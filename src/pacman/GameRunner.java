package pacman;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import pacman.game.GeneralMaze;
import pacman.game.MazeRecorder;
import pacman.io.GameFrame;
import pacman.io.GameInput;
import pacman.io.RandomProvider;
import pacman.tools.CONST;
import pacman.tools.CommonMaze;
import pacman.tools.MazeConfigure;

public class GameRunner implements Runnable {
	private boolean is_running = false;
	Thread thread;
	
	GameFrame game_frame;
	GameInput key_handler;
	CommonMaze maze;
	JFrame main_frame;
	
	MazeRecorder mr;
	
    public void main() {
    	CONST.readImg();
        MazeConfigure cfg = new MazeConfigure();
        MazeRecorder rec = new MazeRecorder(this);
        mr = null;
        
        cfg.startReading(10, 12);
        rec.mazeConfigure("10:12");
        
        cfg.processLine("............");
        rec.mazeConfigure("............");
        
        cfg.processLine("............");
        rec.mazeConfigure("............");
        
        cfg.processLine("............");
        rec.mazeConfigure("............");
        
        cfg.processLine("G...........");
        rec.mazeConfigure("G...........");
        
        cfg.processLine("............");
        rec.mazeConfigure("............");
        
        cfg.processLine("............");
        rec.mazeConfigure("............");
        
        cfg.processLine("............");
        rec.mazeConfigure("............");
        
        cfg.processLine("............");
        rec.mazeConfigure("............");
        
        cfg.processLine("............");
        rec.mazeConfigure("............");
        
        cfg.processLine(".SKT........");
        rec.mazeConfigure(".SKT........");
        
        cfg.stopReading();
        
        maze = cfg.createMaze();
        
        ((GeneralMaze) maze).setRecorder(rec);
        ((GeneralMaze) maze).setRandomProvider(new RandomProvider());
        
    	main_frame = new JFrame();
    	main_frame.setTitle("IJA2023 Pacman");
    	main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	main_frame.setResizable(false);
    	
    	game_frame = new GameFrame(maze);
    	main_frame.add(game_frame);
    	
    	main_frame.pack();
    	main_frame.setLocationRelativeTo(null);
    	main_frame.setVisible(true);
    }
    
    public void setMazeRecorder(MazeRecorder mr) {
    	this.mr = mr;
    }
    
    public void setMaze(CommonMaze maze) {
    	this.maze = maze;
    }
    
    public void setGameFrame(GameFrame gf) {
    	this.game_frame = gf;
    }
    
    public GameFrame getGameFrame() {
    	return game_frame;
    }
    
    public void setMainFrame(JFrame mf) {
    	this.main_frame = mf;
    }
    
    public JFrame getMainFrame() {
    	return main_frame;
    }
	
	public void start() {
		thread = new Thread(this);
		is_running = true;
		thread.start();
	}
	
	public void stop() {
		is_running = false;
	}
	
	private void update() {
		game_frame.update();
	}

	@Override
	public void run() {
		long sleep_time = 1000/CONST.FPS;

		while (is_running) {		
			long start_frame = System.currentTimeMillis();
			if (this.mr != null) {
				mr.updateInputs();
			}
			this.update();
			sleep((int)(sleep_time - (System.currentTimeMillis() - start_frame)));
		}		
	}
	
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
