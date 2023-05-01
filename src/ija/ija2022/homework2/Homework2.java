/*
 * IJA 2022/23: Úloha 2
 * Spuštění presentéru (vizualizace) implementace modelu bludiště.
 */
package ija.ija2022.homework2;

import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

//--- Importy z implementovaneho reseni ukolu
import ija.ija2022.homework2.game.MazeConfigure;
import ija.ija2022.homework2.game.WallField;
import ija.ija2022.homework2.game.PathField;
//--- 

//--- Importy z baliku dodaneho nastroje
import ija.ija2022.homework2.tool.MazePresenter;
import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
//--- 

/**
 * Třída spustí vizualizaci implementace modelu bludiště. 
 * Prezentér je implementován třídou {@link MazePresenter}, dále využívá prostředky definované 
 * v balíku ija.ija2022.homework2.common, který je součástí dodaného nástroje.
 * @author Radek Kočí
 */
public class Homework2 {
    
    public static void main(String... args) {
        MazeConfigure cfg = new MazeConfigure();
        cfg.startReading(10, 10);
        cfg.processLine("..G.......");
        cfg.processLine(".XXXXXXXX.");
        cfg.processLine(".X........");
        cfg.processLine(".X.XXX.XXX");
        cfg.processLine(".X...X.X..");
        cfg.processLine(".XXX.X.X..");
        cfg.processLine(".X...X.X..");
        cfg.processLine("....X..X..");
        cfg.processLine(".XX.X.X...");
        cfg.processLine(".XX.....X.");
        cfg.processLine(".S..XXX.X.");
        cfg.stopReading();
        
        CommonMaze maze = cfg.createMaze();
        
        ImageIcon wallimage = new ImageIcon("src/wall.png");
        ImageIcon pathimage = new ImageIcon("src/path.png");
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(maze.numCols()*50, maze.numRows()*50);
        frame.setLayout(new GridLayout(maze.numRows(), maze.numCols()));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
           
        for(int row = 0; row < maze.numRows(); row++) 
        {
        	for(int col = 0; col < maze.numCols(); col++) 
        	{
        		if(maze.getField(row, col) instanceof WallField) 
        		{
        			frame.add(new JLabel(wallimage));
        		}
        		else if(maze.getField(row, col) instanceof PathField) 
        		{
        			frame.add(new JLabel(pathimage));
        		}
        	}
        	
        }
        
        frame.setVisible(true);
    }
        /*
        MazePresenter presenter = new MazePresenter(maze);
        presenter.open();

        sleep(2000);

        CommonMazeObject obj = maze.ghosts().get(0);
        
        obj.move(CommonField.Direction.L);
        sleep(2000);
        obj.move(CommonField.Direction.L);
        sleep(1000);
        obj.move(CommonField.Direction.D);
        sleep(1000);
        obj.move(CommonField.Direction.D);
        sleep(1000);
        obj.move(CommonField.Direction.D);
        sleep(1000);
        obj.move(CommonField.Direction.D);
        sleep(1000);
        obj.move(CommonField.Direction.R);
        sleep(1000);
        obj.move(CommonField.Direction.L);
        sleep(1000);
        obj.move(CommonField.Direction.U);
    }

    /**
     * Uspani vlakna na zadany pocet ms.
     * @param ms Pocet ms pro uspani vlakna.
     */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(Homework2.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
