package pacman.tools;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class CONST {
	public static final int FPS = 30;
	public static final int PACMAN_SPEED = 2; // tiles per second
	public static final int GHOST_SPEED = 2; // tiles per second
	public static final int MAX_GHOST_NUM = 512;
	
	public static char UP_KEY = 'W';
	public static char DOWN_KEY = 'S';
	public static char LEFT_KEY = 'A';
	public static char RIGHT_KEY = 'D';

	public static int MENU_WIDTH = 200;
	public static int SCREEN_WIDTH = 800 + CONST.MENU_WIDTH;
	public static int SCREEN_HEIGHT = 600;
	public static int SPRITE_SIZE = 64;
	public static int GAME_FRAME_START_X = 0;
	public static int GAME_FRAME_START_Y = 0;
	
	public static final java.net.URL PACMAN_PATH = CONST.class.getResource("/sprites/pacman_0.png");
	public static final java.net.URL GHOST_PATH = CONST.class.getResource("/sprites/ghost_rl0.png");
	public static final java.net.URL PATHFIELD_PATH = CONST.class.getResource("/sprites/pathfield_0.png");
	public static final java.net.URL WALLFIELD_PATH = CONST.class.getResource("/sprites/wallfield_0.png");
	public static final java.net.URL KEY_PATH = CONST.class.getResource("/sprites/key_0.png");
	public static final java.net.URL DOOR_PATH = CONST.class.getResource("/sprites/door_0.png");
	public static final java.net.URL HEART_PATH = CONST.class.getResource("/sprites/heart_0.png");
	
	public static final String DEFAULT_MAZE_PATH = "lib/mazes/default.txt";

	public static Image PATHFIELD_IMG = null;
	public static Image PACMAN_IMG = null;
	public static Image WALLFIELD_IMG = null;
	public static Image GHOST_IMG = null;
	public static Image KEY_IMG = null;
	public static Image DOOR_IMG = null;
	public static Image HEART_IMG = null;
	
	private CONST() {}
	
	public static void readImg() {
		try {
			PACMAN_IMG = ImageIO.read(CONST.PACMAN_PATH);
			PATHFIELD_IMG = ImageIO.read(CONST.PATHFIELD_PATH);
			WALLFIELD_IMG = ImageIO.read(CONST.WALLFIELD_PATH);
			GHOST_IMG = ImageIO.read(CONST.GHOST_PATH);
			KEY_IMG = ImageIO.read(CONST.KEY_PATH);
			DOOR_IMG = ImageIO.read(CONST.DOOR_PATH);
			HEART_IMG = ImageIO.read(CONST.HEART_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setSizes(int rows, int cols) {
		if (cols < rows) {
			CONST.SPRITE_SIZE = CONST.SCREEN_WIDTH / rows;
		} else {
			CONST.SPRITE_SIZE = CONST.SCREEN_HEIGHT / cols;
		}		
		CONST.SCREEN_HEIGHT = CONST.SPRITE_SIZE * rows;
		CONST.SCREEN_WIDTH = CONST.SPRITE_SIZE * cols;
	}
	
	public static void setWidth(int width) {
		CONST.SCREEN_WIDTH = width;
	}
	
	public static void setHeight(int height) {
		CONST.SCREEN_HEIGHT = height;
	}
}
