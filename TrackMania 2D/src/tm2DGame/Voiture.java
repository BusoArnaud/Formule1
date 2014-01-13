package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Voiture {

	int aX = 0;
	int aY = 0;

	int vX = 0;
	int vY = 0;

	int pX = 45;
	int pY = 545;

	Image Voiture;
	String keyUse = null;

	public Voiture(int Startx, int Starty) {
		pX = Startx;
		pY = Starty;

		ImageIcon iVoiture = new ImageIcon("ImagesCircuit/Voiture10.png");
		Voiture = iVoiture.getImage();
	}

	public Rectangle getBounds() {
		Rectangle Box = new Rectangle(pX, pY, 10, 20);
		return Box;
	}

	public int getX() {
		return pX;
	}

	public int getY() {
		return pY;
	}

	public void setX(int newpX) {
		this.pX = newpX;
	}

	public void setY(int newpY) {
		this.pY = newpY;
	}

	public Image getImage() {
		return Voiture;
	}

	public void setKey(String newKey) {
		this.keyUse = newKey;
	}

	public void move() {
		if (keyUse == "T") {
			aX = -1;
			aY = -1;
		} else if (keyUse == "Y") {
			aX = +0;
			aY = -1;
		} else if (keyUse == "U") {
			aX = +1;
			aY = -1;
		} else if (keyUse == "G") {
			aX = -1;
			aY = +0;
		} else if (keyUse == "H") {
			aX = +0;
			aY = +0;
		} else if (keyUse == "J") {
			aX = +1;
			aY = +0;
		} else if (keyUse == "V") {
			aX = -1;
			aY = +1;
		} else if (keyUse == "B") {
			aX = 0;
			aY = +1;
		} else if (keyUse == "N") {
			aX = +1;
			aY = +1;
		}

		vX = vX + aX;
		vY = vY + aY;

		pX = pX + vX;
		pY = pY + vY;

	}
}
