package tm2DGame.terrain;

import java.awt.Image;
import java.awt.Rectangle;

import tm2D.Constants;

public abstract class Terrain implements Constants {

	int x, y;
	Image image;

	public Terrain(int startX, int startY) {
			x = startX;
			y = startY;
		}

	public Rectangle getBounds() {
		Rectangle Box = new Rectangle(x, y, 10, 10);
		return Box;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}
	
}
