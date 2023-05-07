package pacman.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pacman.GameRunner;
import pacman.game.MazeConfigure;
import pacman.game.MazeRecorder;
import pacman.tools.CONST;

public class Menu implements ActionListener {
	JButton startgame;
    JButton selectmaze;
    JButton replay;
    JButton exit;
    JLabel score;
    MazeConfigure config = null;
    GameRunner gr;
    JFrame menuframe;
    
    public Menu(GameRunner gr) {
    	this.gr = gr;
    }    
    
	public void StartMenu() {	
		menuframe = new JFrame();
		menuframe.setSize(CONST.MENU_WIDTH,CONST.SCREEN_HEIGHT);
        
		score = new JLabel();
		score.setBounds(10, 0, 200, 50);		
        menuframe.add(score);
		
        JPanel buttons_panel = new JPanel();
        buttons_panel.setLayout(null);
        buttons_panel.setSize(200, 400);
        
        startgame = new JButton("Start Game");
        startgame.setBounds(0, 50, 200, 50);
        buttons_panel.add(startgame);
        startgame.addActionListener(this);
        
        selectmaze = new JButton("Select Maze");
        selectmaze.setBounds(0, 100, 200, 50);
        buttons_panel.add(selectmaze);
        selectmaze.addActionListener(this);
        
        replay = new JButton("Replay");
        replay.setBounds(0, 150, 200, 50);
        buttons_panel.add(replay);
        replay.addActionListener(this);
        
        exit = new JButton("Exit");
        exit.setBounds(0, CONST.SCREEN_HEIGHT - 88, 200, 50);
        buttons_panel.add(exit);
        exit.addActionListener(this);
        
        menuframe.add(buttons_panel);
        menuframe.setVisible(true);
	}
	
	public void closeMenu() {
		this.menuframe.setVisible(false);
	}

	
	public void openMenu() {
		this.menuframe.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == startgame) {
	    	gr.main();
	    	this.closeMenu();
	    	gr.start();
		}
		
		if(e.getSource() == selectmaze) 
		{
			JFileChooser mazefile = new JFileChooser();
			int response = mazefile.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) 
			{
				File file = new File(mazefile.getSelectedFile().getAbsolutePath());
				gr.readMaze(file);
			}
			
		}
		
		if(e.getSource() == replay) {
	    	MazeRecorder mr = new MazeRecorder(gr);
	    	mr.rerun();
	    	this.closeMenu();
		}
		
		if(e.getSource() == exit) 
		{
			System.exit(0);
		}
		
	}

	public void showCount(int count) {
		score.setText("Your score is: " + Integer.toString(count));
		
	}
}
