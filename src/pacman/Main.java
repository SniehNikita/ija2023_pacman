/*
 * IJA 2022/23: Úloha 2
 * Spuštění presentéru (vizualizace) implementace modelu bludiště.
 */
package pacman;

import pacman.game.MazeRecorder;

public class Main {
	public static final int sprite_size = 64;
	
    public static void main(String... args) {
    	GameRunner gr = new GameRunner();
    	MazeRecorder mr = new MazeRecorder(gr);
    	mr.rerun("filename.txt");
//    	gr.main();
//    	gr.start();
    }
}
