/*
 * IJA 2022/23: Úloha 2
 * Spuštění presentéru (vizualizace) implementace modelu bludiště.
 */
package pacman;

import pacman.game.MazeRecorder;
import pacman.io.Menu;
import pacman.tools.CONST;

public class Main {
	public static final int sprite_size = 64;
	
    public static void main(String... args) {
    	CONST.readImg();
    	GameRunner gr = new GameRunner();
    	Menu menu = new Menu(gr);
    	gr.createDefaultCFG();
    	gr.setMenu(menu);
    	menu.StartMenu();
//    	gr.main();
//    	gr.start();
//    	MazeRecorder mr = new MazeRecorder(gr);
//    	mr.rerun("filename.txt");
//    	gr.main();
//    	gr.start();
    }
}
