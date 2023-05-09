/**
 * @Author xsnieh00 , xstang03
 * Main class of this program , runs program and starts the logging of a game
 */

package pacman;

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
    }
}
