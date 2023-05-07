package pacman.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import pacman.GameRunner;
import pacman.io.GameFrame;
import pacman.io.GameInput;
import pacman.tools.CONST;
import pacman.tools.MazeConfigure;
import pacman.tools.CommonMaze;

public class MazeRecorder {
	
	private String log_buffer;
	private int frame_num;
	private int[] ghost_seeds;
	
	GameInput key_input;
	private boolean is_up;
	private boolean is_down;
	private boolean is_left;
	private boolean is_right;
	
	private GameRunner gr;
	private CommonMaze maze;
	
	public boolean is_rec;
	
	public MazeRecorder(GameInput key_input, GameRunner gr) {
		log_buffer = new String();
		frame_num = 0;
		is_up = false;
		is_down = false;
		is_left = false;
		is_right = false;
		ghost_seeds = new int[CONST.MAX_GHOST_NUM];
    	this.gr = gr;
    	gr.setMazeRecorder(this);
		for (int i=0; i<CONST.MAX_GHOST_NUM; i++) { ghost_seeds[i] = -1; }
		this.key_input = key_input;
	}
	
	public MazeRecorder(GameRunner gr) {
		this(null, gr);
	}
	
	public MazeRecorder(GameInput key_input) {
		this(key_input, null);
	}

	public MazeRecorder() {
		this(null, null);
	}
	
	public void rerun() {
		MazeConfigure cfg = new MazeConfigure();
	    BufferedReader reader = null;

	    gr.main();

	    try {
			reader = new BufferedReader(new FileReader("replay_log.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    String line = new String();
	    int rows = 0;
	    int cols = 0;
	    
	    try {
			line = reader.readLine();
			String str = new String();
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) != ':') {
					str += line.charAt(i);				
				} else {
					rows = Integer.parseInt(str);
					str = "";
				}
			}
			cols = Integer.parseInt(str);
			CONST.setSizes(rows+2, cols+2);
			cfg.startReading(rows, cols);
			for (int i = 0; i < rows; i++) {
				str = reader.readLine();
//				str = str.substring(0, str.length()-1);
				cfg.processLine(str);
			}
			while (line != null) {
				line = reader.readLine();
				log_buffer += line + "\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
        cfg.stopReading();
        
        maze = cfg.createMaze();
        ((GeneralMaze) maze).removeRecorder();
        gr.setMaze(maze);
        ((GeneralMaze) maze).setRunner(gr);
        
        GameFrame gf = new GameFrame(maze);
    	gr.setGameFrame(gf);
    	
    	key_input = gf.getKeyInput();
    	key_input.setSim(true);

        gr.getMainFrame().remove(gr.getGameFrame());
        gr.setGameFrame(gf);
        gr.getMainFrame().add(gf);
        gr.setMazeRecorder(this);
        gr.getMainFrame().setVisible(true);
        
//        frame_num += 2;
        
        gr.start();
	}

	public void updateInputs() {
		int i = 0;
		int log_fr_num = 0;

		while (i < log_buffer.length()) {
			log_fr_num = 0;
			i = 0;
			while (i < log_buffer.length() && log_buffer.charAt(i) != ':') {
				log_fr_num = log_fr_num * 10 + Character.getNumericValue(log_buffer.charAt(i));
				i++;
			}
			if (log_fr_num <= frame_num) {
				i++;
				if (log_buffer.charAt(i) == 'g') {
					i += 2;
					int move_seed = 0;
					int ghost_num = 0;
					while (i < log_buffer.length() && log_buffer.charAt(i) != ':') {
						ghost_num = ghost_num * 10 + Character.getNumericValue(log_buffer.charAt(i));
						i++;
					}
					i += 3;
					while (i < log_buffer.length() && log_buffer.charAt(i) != '\n') {
						move_seed = move_seed * 10 + Character.getNumericValue(log_buffer.charAt(i));
						i++;
					}
					if (maze.ghosts().size() - 1 == ghost_num) {
						((GhostObject) maze.ghosts().get(ghost_num)).setDirectionSeed(move_seed);
					} else {
						((GeneralMaze) maze).addSeed(move_seed);
					}
					i+=1;
				} else {
					i+=2;
					char key;
					if (log_buffer.charAt(i+2) == 'U') {
						key = CONST.UP_KEY;
					} else if (log_buffer.charAt(i+2) == 'D') {
						key = CONST.DOWN_KEY;						
					} else if (log_buffer.charAt(i+2) == 'R') {
						key = CONST.RIGHT_KEY;
					} else {
						key = CONST.LEFT_KEY;						
					}
					if (log_buffer.charAt(i) == 'u') {
						key_input.setKeyReleased(key);
					} else {
						key_input.setKeyPressed(key);						
					}
					i+=4;
				}
				log_buffer = log_buffer.substring(i);
			} else {
				break;
			}
		}
		frame_num++;
	}
	
	public void stop() {
	    BufferedWriter writer;
	    writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("replay_log.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			writer.write(log_buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	    try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void setKeyListener(GameInput key_input) {
		this.key_input = key_input;		
	}

	public void update() {
		if (key_input != null) {
			this.userinput();			
		}
		frame_num++;
	}
	
	public void mazeConfigure(String mazeline) {
		log_buffer += mazeline + "\n";
	}
	
	public void ghostMovementSeed(int ghost_num, int direction_seed) {
		if (ghost_seeds[ghost_num] == -1) {
			ghost_seeds[ghost_num] = direction_seed;
			log_buffer += Integer.toString(frame_num) + ":g:" + Integer.toString(ghost_num) + ":d:" + Integer.toString(direction_seed) + "\n";
		}
	}
	
	public void userinput() {
		if (key_input.isKeyPressed(CONST.UP_KEY)) {
			if (is_up == false) { is_up = true; log_buffer += Integer.toString(frame_num) + ":i:d:U\n"; }
		} else if (is_up == true) { is_up = false; log_buffer += Integer.toString(frame_num) + ":i:u:U\n"; }

		if (key_input.isKeyPressed(CONST.LEFT_KEY)) {
			if (is_left == false) { is_left = true; log_buffer += Integer.toString(frame_num) + ":i:d:L\n"; }
		} else if (is_left == true) { is_left = false; log_buffer += Integer.toString(frame_num) + ":i:u:L\n"; }

		if (key_input.isKeyPressed(CONST.DOWN_KEY)) {
			if (is_down == false) { is_down = true; log_buffer += Integer.toString(frame_num) + ":i:d:D\n"; }
		} else if (is_down == true) { is_down = false; log_buffer += Integer.toString(frame_num) + ":i:u:D\n"; }

		if (key_input.isKeyPressed(CONST.RIGHT_KEY)) {
			if (is_right == false) { is_right = true; log_buffer += Integer.toString(frame_num) + ":i:d:R\n"; }
		} else if (is_right == true) { is_right = false; log_buffer += Integer.toString(frame_num) + ":i:u:R\n"; }
	}
}
