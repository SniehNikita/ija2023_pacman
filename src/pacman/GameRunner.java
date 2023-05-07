package pacman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import pacman.game.GeneralMaze;
import pacman.game.MazeRecorder;
import pacman.io.GameFrame;
import pacman.io.GameInput;
import pacman.io.Menu;
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
	MazeConfigure cfg;
	Menu menu;
	
    public void main() {
    	if (cfg == null) {
    		this.createDefaultCFG();
    	}
        maze = cfg.createMaze();
        ((GeneralMaze) maze).setRunner(this);
        
    	main_frame = new JFrame();
    	main_frame.setTitle("IJA2023 Pacman");
    	main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	main_frame.setResizable(false);
    	
    	game_frame = new GameFrame(maze);
    	mr.setKeyListener(game_frame.getKeyInput());
        mr.is_rec = true;
        
        ((GeneralMaze) maze).setRecorder(mr);
    	main_frame.add(game_frame);
    	
    	main_frame.pack();
    	main_frame.setLocationRelativeTo(null);
    	main_frame.setVisible(true);
    }
    
    public void setMenu(Menu menu) {
    	this.menu = menu;
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
		if (game_frame != null) {
			game_frame.update();
		}
	}

	@Override
	public void run() {
		long sleep_time = 1000/CONST.FPS;

		while (is_running) {		
			long start_frame = System.currentTimeMillis();
			if (this.mr != null && this.mr.is_rec == false) {
				mr.updateInputs();
			}
			this.update();
			if ((sleep_time - (System.currentTimeMillis() - start_frame)) > 0 ) {
				sleep((int)(sleep_time - (System.currentTimeMillis() - start_frame)));
			}
		}		
	}
	
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
	
	public void readMaze(File file) {
		try 
		{
	        mr = new MazeRecorder(this);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			
			line = reader.readLine();
			String[] array = line.split(" ");

			int row = Integer.parseInt(array[0]);
			int col = Integer.parseInt(array[1]);
			
			mr.mazeConfigure(Integer.toString(row) + ":" + Integer.toString(col));
			CONST.setSizes(row+2, col+2);

			cfg = new MazeConfigure();
			cfg.startReading(row, col);

			
			while((line = reader.readLine()) != null)
			{
				cfg.processLine(line);
				mr.mazeConfigure(line);
			}
			cfg.stopReading();

			reader.close();
		}
		catch(IOException exc)
		{
			exc.printStackTrace();
		}
	}

	public void createDefaultCFG() {
		File file = new File(CONST.DEFAULT_MAZE_PATH);
		this.readMaze(file);
		
	}

	public void end(boolean is_win) {
		this.stop();
		if (is_win) {
			this.game_frame.setVisible(false);
			this.menu.openMenu();
		} else {
			this.game_frame.setVisible(false);
			this.menu.openMenu();			
		}
		this.main_frame.dispose();
		sleep(1000);
		cfg = null;
		this.game_frame = null;
	}
}
