package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Voiture {

	int aX = 0;
	int aY = 0;

	int vX = 0;
	int vY = 0;

	int pX= 45;
	int pY= 545;

	Image Voiture;

	public Voiture(int Startx, int Starty) {
		pX = Startx;
		pY = Starty;

		ImageIcon iVoiture = new ImageIcon("ImagesCircuit/Voiture10.png");
		Voiture = iVoiture.getImage();
	}

	public Rectangle getBounds() {
		Rectangle Box = new Rectangle(pX, pY, 10, 10);
		return Box;
	}

	public int getX() {
		return pX;
	}

	public int getY() {
		return pY;
	}

	public Image getImage() {
		return Voiture;
	}

	public void setX(int newpX) {
		this.pX = newpX;
	}

	public void setY(int newpY) {
		this.pY = newpY;
	}

	public void Move() {

	}

}
