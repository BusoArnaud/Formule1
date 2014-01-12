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

	public void setKey(String newKey) {
		this.keyUse = newKey;
	}

	public void move() {
		if (keyUse == "T") {
			if (aX > 0) {
				aX = aX - 1;
			} else {
				aX = aX + 0;
			}
			aY = aY + 1;
		} else if (keyUse == "Y") {
			aX = aX + 0;
			aY = aY + 1;
		} else if (keyUse == "U") {
			aX = aX + 1;
			aY = aY + 1;
		} else if (keyUse == "G") {
			if (aX > 0) {
				aX = aX - 1;
			} else {
				aX = aX + 0;
			}
			aY = aY + 0;
		} else if (keyUse == "H") {
			aX = aX + 0;
			aY = aY + 0;
		} else if (keyUse == "J") {
			aX = aX + 1;
			aY = aY + 0;
		} else if (keyUse == "V") {
			if (aX > 0) {
				aX = aX - 1;
			} else {
				aX = aX + 0;
			}
			if (aY > 0) {
				aY = aY - 1;
			} else {
				aY = aY + 0;
			}
		} else if (keyUse == "B") {
			aX = aX + 0;
			if (aY > 0) {
				aY = aY - 1;
			} else {
				aY = aY + 0;
			}
		} else if (keyUse == "N") {
			aX = aX + 1;
			if (aY > 0) {
				aY = aY - 1;
			} else {
				aY = aY + 0;
			}
		}

	}
}
