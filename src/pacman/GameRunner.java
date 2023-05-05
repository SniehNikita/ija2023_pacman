package pacman;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import pacman.game.PacmanObject;
import pacman.io.GameFrame;
import pacman.io.KeyboardInput;
import pacman.tools.CONST;
import pacman.tools.CommonMaze;
import pacman.tools.MazeConfigure;

public class GameRunner implements Runnable {
	private boolean is_running = false;
	Thread thread;
	
	GameFrame game_frame;
	KeyboardInput key_handler;
	CommonMaze maze;
	
    public void main() {
    	CONST.readImg();
        MazeConfigure cfg = new MazeConfigure();
        cfg.startReading(10, 10);
        cfg.processLine("..G.......");
        cfg.processLine(".XXXXXXXX.");
        cfg.processLine(".X........");
        cfg.processLine(".X.XXX.XXX");
        cfg.processLine(".X...X.X..");
        cfg.processLine(".XXX.X.X..");
        cfg.processLine(".X.....X..");
        cfg.processLine("....X..X..");
        cfg.processLine(".XX.X.X...");
        cfg.processLine(".XXS....X.");
        cfg.stopReading();
        
        maze = cfg.createMaze();
        
    	JFrame main_frame = new JFrame();
    	main_frame.setTitle("IJA2023 Pacman");
    	main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	main_frame.setResizable(false);
    	
    	game_frame = new GameFrame(maze);
    	main_frame.add(game_frame);
    	
    	main_frame.pack();
    	main_frame.setLocationRelativeTo(null);
    	main_frame.setVisible(true);

    	start();
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
