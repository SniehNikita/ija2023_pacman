package pacman.tools;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class CONST {
	public static final int SPRITE_SIZE = 64;
	public static final int SCALE = 1;
	public static final int FPS = 30;
	public static final int PACMAN_SPEED = 2; // tiles per second
	public static final java.net.URL PACMAN_PATH = CONST.class.getResource("/sprites/pacman_0.png");
	public static final java.net.URL GHOST_PATH = CONST.class.getResource("/sprites/ghost_rl0.png");
	public static final java.net.URL PATHFIELD_PATH = CONST.class.getResource("/sprites/pathfield_0.png");
	public static final java.net.URL WALLFIELD_PATH = CONST.class.getResource("/sprites/wallfield_0.png");

	public static Image PATHFIELD_IMG = null;
	public static Image PACMAN_IMG = null;
	public static Image WALLFIELD_IMG = null;
	public static Image GHOST_IMG = null;
	
	private CONST() {}
	
	public static void readImg() {
		try {
			PACMAN_IMG = ImageIO.read(CONST.PACMAN_PATH);
			PATHFIELD_IMG = ImageIO.read(CONST.PATHFIELD_PATH);
			WALLFIELD_IMG = ImageIO.read(CONST.WALLFIELD_PATH);
			GHOST_IMG = ImageIO.read(CONST.GHOST_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
