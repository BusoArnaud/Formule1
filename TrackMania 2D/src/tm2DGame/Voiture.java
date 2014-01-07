package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Voiture {

	int x, y;
	int Vx, Vy;
	int Ax, Ay;
	Image Voiture;
	ImageIcon iVoiture = new ImageIcon("ImagesCircuit/Voiture.gif");

	public Voiture(int Startx, int Starty) {
		x = Startx;
		y = Starty;
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
		return Voiture;
	}

}
